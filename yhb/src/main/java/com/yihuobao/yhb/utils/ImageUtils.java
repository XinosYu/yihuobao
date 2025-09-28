package com.yihuobao.yhb.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 * 图片工具，上传/获取
 * </p>
 * @author zzg
 * @since 2025-09-19
 */
public class ImageUtils {
    // 允许上传的图片格式
    private static final String[] ALLOWED_FILE_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};

    // 日期格式化对象，用于生成目录
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    // 存储路径 - 改为通过外部设置，避免硬编码
    private static String baseUploadPath;

    // 图片访问基础URL - 新增，统一管理URL前缀
    private static String baseAccessUrl;

    /**
     * 初始化基础路径（建议在项目启动时调用）
     * @param uploadPath 上传根目录
     * @param accessUrl 访问基础URL
     */
    public static void init(String uploadPath, String accessUrl) {
        if (!StringUtils.hasText(uploadPath)) {
            throw new IllegalArgumentException("初始化失败：上传路径不能为空");
        }
        // 处理路径结尾的分隔符，确保一致性
        baseUploadPath = uploadPath.endsWith(File.separator) ? uploadPath : uploadPath + File.separator;
        baseAccessUrl = accessUrl;
    }

    /**
     * 上传图片并返回存储路径
     * @param file 上传的图片文件
     * @return 图片存储的相对路径
     * @throws IOException 当文件操作失败时抛出
     * @throws IllegalArgumentException 当文件不符合要求时抛出
     */
    public static String uploadImage(MultipartFile file) throws IOException {
        // 新增：检查基础路径是否初始化
        checkInitStatus();

        // 校验文件是否为空
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传的图片文件不能为空");
        }

        // 校验文件大小，限制10MB
        long maxSize = 10 * 1024 * 1024; // 提取为变量，便于修改
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("上传的图片文件不能超过" + (maxSize / 1024 / 1024) + "MB");
        }

        // 校验文件格式
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !isAllowedFileExtension(originalFilename)) {
            throw new IllegalArgumentException("不支持的图片格式，允许的格式：jpg, jpeg, png, gif, bmp");
        }

        // 生成保存目录（按日期分目录存储）
        String dateDir = DATE_FORMAT.format(new Date());
        File uploadDir = new File(baseUploadPath, dateDir); // 优化：使用File的多参数构造器

        // 目录不存在则创建
        if (!uploadDir.exists()) {
            // 新增：设置目录权限，避免权限问题
            boolean mkdirs = uploadDir.mkdirs();
            if (!mkdirs) {
                throw new IOException("创建上传目录失败：" + uploadDir.getAbsolutePath());
            }
            uploadDir.setWritable(true);
        }

        // 生成唯一文件名
        String fileName = generateUniqueFileName(originalFilename);

        // 保存文件
        File destFile = new File(uploadDir, fileName);
        // 新增：检查目标文件是否已存在（理论上不会，但以防万一）
        if (destFile.exists()) {
            throw new IOException("文件已存在：" + destFile.getAbsolutePath());
        }

        try {
            try (InputStream in = file.getInputStream();
                 OutputStream out = new FileOutputStream(destFile)) {
                byte[] buffer = new byte[1024];
                int len;
                // 手动读取输入流，写入输出流
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush(); // 强制刷新缓冲区
                System.out.println("手动写入成功，文件大小：" + destFile.length() + "字节");
            } catch (IOException e) {
                throw new IOException("手动写入失败：" + destFile.getAbsolutePath(), e);
            }
        } catch (IOException e) {
            // 增强：捕获异常时添加更多上下文信息
            throw new IOException("保存文件失败：" + destFile.getAbsolutePath(), e);
        }
        return dateDir + File.separator + fileName;
    }

    /**
     * 根据相对路径获取图片文件资源
     * @param relativePath 图片相对路径
     * @return 图片文件资源
     * @throws IOException 当文件不存在或无法访问时抛出
     */
    public static Resource getImageResource(String relativePath) throws IOException {
        // 新增：检查基础路径是否初始化
        checkInitStatus();

        if (relativePath == null || relativePath.trim().isEmpty()) {
            throw new IllegalArgumentException("图片相对路径不能为空");
        }

        // 构建完整文件路径 - 优化：使用File的多参数构造器
        File imageFile = new File(baseUploadPath, relativePath);

        // 验证文件是否存在且是一个文件
        if (!imageFile.exists() || !imageFile.isFile()) {
            throw new IOException("图片文件不存在：" + imageFile.getAbsolutePath());
        }

        // 验证文件是否是图片
        if (!isImageFile(imageFile)) {
            throw new IOException("指定路径不是有效的图片文件：" + imageFile.getAbsolutePath());
        }

        return new FileSystemResource(imageFile);
    }

    /**
     * 构建图片访问URL（使用预设的baseAccessUrl）
     * @param relativePath 图片相对路径
     * @return 完整的图片访问URL
     */
    public static String buildImageUrl(String relativePath) {
        if (baseAccessUrl == null || baseAccessUrl.isEmpty()) {
            throw new IllegalArgumentException("基础URL未初始化，请先调用init方法");
        }
        return buildImageUrl(baseAccessUrl, relativePath);
    }

    /**
     * 构建图片访问URL（支持自定义baseUrl）
     * @param baseUrl 基础URL
     * @param relativePath 图片相对路径
     * @return 完整的图片访问URL
     */
    public static String buildImageUrl(String baseUrl, String relativePath) {
        if (!StringUtils.hasText(baseUrl)) {
            throw new IllegalArgumentException("基础URL不能为空");
        }

        if (!StringUtils.hasText(relativePath)) {
            throw new IllegalArgumentException("图片相对路径不能为空");
        }

        // 处理URL中的分隔符，统一使用"/"
        String url = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        // 优化：使用replaceAll处理所有系统分隔符
        url += relativePath.replaceAll(File.separator, "/");

        return url;
    }

    //-----------------------------------私有函数-----------------------------------------

    /**
     * 检查是否已初始化
     */
    private static void checkInitStatus() {
        if (baseUploadPath == null || baseUploadPath.isEmpty()) {
            throw new IllegalStateException("图片工具未初始化，请先调用init方法设置基础路径");
        }
    }

    /**
     * 生成唯一文件名，避免文件名冲突
     * @param originalFilename 原始文件名
     * @return 唯一文件名
     */
    private static String generateUniqueFileName(String originalFilename) {
        // 获取文件扩展名
        String extension = getFileExtension(originalFilename);
        if (extension.isEmpty()) {
            throw new IllegalArgumentException("文件名没有扩展名：" + originalFilename);
        }

        // 生成唯一标识：时间戳 + 随机数（优化随机数范围）
        long timestamp = System.currentTimeMillis();
        int randomNum = new Random().nextInt(9000) + 1000; // 生成1000-9999的随机数，确保4位

        // 组合新文件名
        return timestamp + "_" + randomNum + "." + extension;
    }

    /**
     * 获取文件扩展名
     * @param filename 文件名
     * @return 文件扩展名（小写）
     */
    private static String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return ""; // 处理无扩展名或以.结尾的情况
        }
        return filename.substring(lastDotIndex + 1).toLowerCase();
    }

    /**
     * 检查文件扩展名是否被允许
     * @param filename 文件名
     * @return 如果允许返回true，否则返回false
     */
    private static boolean isAllowedFileExtension(String filename) {
        String extension = getFileExtension(filename);
        if (extension.isEmpty()) {
            return false;
        }

        for (String allowedExtension : ALLOWED_FILE_EXTENSIONS) {
            if (allowedExtension.equalsIgnoreCase("." + extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查文件是否为图片
     * @param file 待检查的文件
     * @return 如果是图片返回true，否则返回false
     */
    private static boolean isImageFile(File file) {
        try {
            // 尝试读取文件为图片，如果能成功读取则是图片文件
            BufferedImage image = ImageIO.read(file);
            return image != null;
        } catch (IOException e) {
            return false;
        }
    }

    // 新增：getter方法，便于外部获取配置
    public static String getBaseUploadPath() {
        return baseUploadPath;
    }

    public static String getBaseAccessUrl() {
        return baseAccessUrl;
    }
}
