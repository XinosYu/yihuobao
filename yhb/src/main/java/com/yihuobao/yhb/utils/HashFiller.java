package com.yihuobao.yhb.utils;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class HashFiller {
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final String driverClass;
    private final int batchSize; // 每批处理的记录数

    public HashFiller(String jdbcUrl, String username, String password, String driverClass, int batchSize) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.driverClass = driverClass;
        this.batchSize = batchSize;
    }

    // 填充哈希值主方法
    public void fillHashValues(String tableName, String hashColumn, List<String> dataColumns) throws Exception {
        // 加载驱动
        Class.forName(driverClass);

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            conn.setAutoCommit(false); // 手动提交事务

            int totalProcessed = 0;
            int offset = 0;

            while (true) {
                // 查询未计算哈希的记录
                List<Record> records = queryEmptyHashRecords(conn, tableName, hashColumn, dataColumns, offset, batchSize);

                if (records.isEmpty()) {
                    break; // 没有更多记录需要处理
                }

                // 计算哈希值
                calculateHashes(records, dataColumns);

                // 批量更新数据库
                updateHashValues(conn, tableName, hashColumn, records);

                totalProcessed += records.size();
                offset += batchSize;

                System.out.printf("已处理 %d 条记录%n", totalProcessed);
                conn.commit(); // 提交事务
            }

            System.out.printf("全部完成！共处理 %d 条记录%n", totalProcessed);
        }
    }

    // 查询空哈希记录
    private List<Record> queryEmptyHashRecords(Connection conn, String tableName, String hashColumn,
                                               List<String> dataColumns, int offset, int limit) throws SQLException {
        List<Record> records = new ArrayList<>();

        // 构建查询SQL
        StringBuilder selectSql = new StringBuilder("SELECT id, "); // 假设表中有id列作为主键
        selectSql.append(String.join(", ", dataColumns));
        selectSql.append(" FROM ").append(tableName);
        selectSql.append(" WHERE ").append(hashColumn).append(" IS NULL OR ").append(hashColumn).append(" = ''");
        selectSql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql.toString())) {

            while (rs.next()) {
                Record record = new Record();
                record.id = rs.getLong("id");

                // 获取用于计算哈希的字段值
                for (String column : dataColumns) {
                    record.data.put(column, rs.getString(column));
                }

                records.add(record);
            }
        }

        return records;
    }

    // 计算哈希值
    private void calculateHashes(List<Record> records, List<String> dataColumns) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            for (Record record : records) {
                // 拼接需要计算哈希的字段值
                StringBuilder dataToHash = new StringBuilder();
                for (String column : dataColumns) {
                    dataToHash.append(record.data.getOrDefault(column, "")).append("|");
                }

                // 计算哈希
                byte[] hashBytes = md.digest(dataToHash.toString().getBytes());
                record.hash = bytesToHex(hashBytes);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("不支持SHA-256哈希算法", e);
        }
    }

    // 批量更新哈希值
    private void updateHashValues(Connection conn, String tableName, String hashColumn, List<Record> records) throws SQLException {
        String updateSql = "UPDATE " + tableName + " SET " + hashColumn + " = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            for (Record record : records) {
                pstmt.setString(1, record.hash);
                pstmt.setLong(2, record.id);
                pstmt.addBatch();
            }

            pstmt.executeBatch();
        }
    }

    // 字节转十六进制
    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    // 内部类表示一条记录
    private static class Record {
        long id;
        String hash;
        final java.util.Map<String, String> data = new java.util.HashMap<>();
    }

    public static void main(String[] args) {
        try {
            // 配置数据库连接信息
            HashFiller filler = new HashFiller(
                    "jdbc:mysql://localhost:3306/yihuobaodb",
                    "root",
                    "124578",
                    "com.mysql.cj.jdbc.Driver",
                    1000 // 每批处理500条
            );

            // 配置需要计算哈希的表和字段
            List<String> dataColumns = List.of("id", "phoneNumber", "userEmail" , "password" , "createdAt"); // 替换为实际需要参与哈希计算的字段

            // 执行填充
            filler.fillHashValues("user", "userHash", dataColumns);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}