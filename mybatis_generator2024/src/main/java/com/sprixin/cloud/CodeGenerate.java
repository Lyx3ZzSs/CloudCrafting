package com.sprixin.cloud;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.sprixin.cloud.convert.MysqlTypeConvertCustom;

import java.util.Collections;
import java.util.List;

/**
 * MyBatis-Plus 代码生成
 *
 * @author yuanxin.li
 * @Date 2024/12/27 13:54:00
 */

public class CodeGenerate {
    public static void main(String[] args) {

        //数据源地址
        String baseUrl = "jdbc:mysql://localhost:3306/";
        //数据库名
        String dbName = "cloud_crafting";
        //数据源配置
        String config = "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
        String jdbcUrl = baseUrl + dbName + config;

        //数据库用户名和密码
        String username = "root";
        String password = "root";

        //数据库表的设置
        //设置需要自动代码生成的表名
        List<String> listTable = List.of("t_pay");
        //设置 过滤 表的前缀
        List<String> listTablePrefix = List.of("t_");
        //设置 过滤 表的后缀
        List<String> listTableSuffix = List.of("");

        //基本配置
        //作者
        String author = "yuanxin.li";
        // 父包名
        String parent = "com.sprixin.cloud";
        //模块名
        String module = "cloud-provider-payment8001";

        //1、配置数据源
        FastAutoGenerator.create(new DataSourceConfig.Builder(jdbcUrl,username,password).typeConvert(new MysqlTypeConvertCustom()))
                //2.全局配置
                .globalConfig(builder -> {
                    builder.author(author) //设置作者
                            .outputDir(System.getProperty("user.dir") + "/" + module + "/src/main/java")  //设置输出路径：项目的 java 目录下【System.getProperty("user.dir")意思是获取到项目所在的绝对路径】
                            .commentDate("yyyy-MM-dd hh:mm:ss") //注释日期
                            .dateType(DateType.ONLY_DATE) //定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            //.enableSwagger() //开启swagger模式
                            .disableOpenDir(); //禁止打开输出目录，默认为打开
                })
                //3.包配置
                .packageConfig(builder -> {
                    builder.parent(parent) //设置父包名
                            //.moduleName(module) //设置模块报名
                            .entity("entity") //实体报名
                            .service("service") //service报名
                            .serviceImpl("service.impl") //***ServiceImpl 报名
                            .mapper("mapper") //Mapper 包名
                            .xml("mapper.xml") // Mapper XML 包名
                            .controller("controller") //controller 包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/" + module + "/src/main/resources/mapper"));    //配置 mapper.xml 路径信息：项目的 resources 目录下
                })
                //4.策略配置
                .strategyConfig(builder -> {
                    builder.enableCapitalMode() //开启大写命名
                            .enableSkipView() //创建实体类的时候跳过视图
                            .addInclude(listTable) //设置需要生成的数据表明
                            .addTablePrefix(listTablePrefix) //设置过滤表的前缀
                            .addTableSuffix(listTableSuffix) //设置过滤表的后缀

                            //4.1 实体类策略配置
                            .entityBuilder()
                            .enableFileOverride()
                            .disableSerialVersionUID()  //默认是开启实体类序列化，可以手动disable使它不序列化。
                            .enableTableFieldAnnotation() //开启生成实体类时生成字段注解
                            .enableLombok() //开启Lombok
                            .javaTemplate("/templates/entity.java")
                            .versionColumnName("version") //乐观锁字段名称（数据库）
                            .versionPropertyName("version") //乐观锁属性名（实体）
                            .logicDeleteColumnName("deleted") //逻辑删除字段名（数据库）
                            .logicDeletePropertyName("deleteFlag") //逻辑删除掉属性名（实体）
                            .naming(NamingStrategy.underline_to_camel) //数据库表映射到实体的命名策略：默认是下划线转驼峰命。这里可以不设置
                            .columnNaming(NamingStrategy.underline_to_camel) //数据库表字段映射到实体的命名策略：下划线转驼峰命。（默认是和naming一致，所以也可以不设置）
                            /* 添加表字段填充，“create_time"字段自动填充为插入时间，“update_time”字段自动填充为插入修改时间,这里交由数据库处理
                             .addTableFills(
                                     new Column("create_time", FieldFill.INSERT),
                                     new Column("update_time",FieldFill.INSERT_UPDATE)
                             ) */

                            //4.2 Controller 策略配置
                            .controllerBuilder()
                            .enableFileOverride()
                            .enableHyphenStyle() //开启驼峰连转字符
                            .formatFileName("%sController") //格式化 Controller类文件名称，%s进行匹配表名，如UserController
                            .enableRestStyle() //开启生成 @RestController 控制器

                            //4.3 service 策略配置
                            .serviceBuilder()
                            .enableFileOverride()
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

                            //4.4、Mapper策略配置
                            .mapperBuilder()
                            .enableFileOverride()
                            .superClass(BaseMapper.class)   //设置父类
                            .enableBaseResultMap()  //启用 BaseResultMap 生成
                            .enableBaseColumnList() //启用 BaseColumnList
                            .formatMapperFileName("%sMapper")   //格式化 mapper 文件名称
                            .formatXmlFileName("%sXml") //格式化Xml文件名称
                            .formatMapperFileName("%sMapper");   //格式化Mapper文件名称

                })
                /*//5、模板
                .templateEngine(new VelocityTemplateEngine())
                模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker(以下两个引擎用哪个就保留哪个)
                .templateEngine(new BeetlTemplateEngine())*/
                //Freemarker
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();


    }
}

