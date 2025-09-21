package com.yihuobao.yhb;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Main {
    public static  void main(String[] args) {
        // 创建对象
        AutoGenerator autogenerator = new AutoGenerator();

        // 数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://106.53.46.231:3306/yihuobaodb");
        dsc.setUsername("bc666");
        dsc.setPassword("124578");
        autogenerator.setDataSource(dsc);

        // 全局配置  
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取相对路径
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("admin");//设置创始人
        gc.setOpen(false); //生成后是否打开资源管理器

        //去掉Service里的I
        gc.setServiceName("%sService");
        autogenerator.setGlobalConfig(gc);

        //包配置 
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.yihuobao.yhb");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        autogenerator.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //全部的表
        strategy.setInclude("dish","production","Storage","Transport","Supplier","Company","Recall","Handover","User","Merchant","Employee","Adminer","Audit","WechatUser");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        autogenerator.setStrategy(strategy);
        autogenerator.execute();
    }
}