package com.framework.demo.demos.service;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MybatisPlusGeneratorTest {
    public static void main(String[] args) {
        hello();
    }

    public static void hello() {
        // 使用元数据查询的方式生成代码,默认已经根据jdbcType来适配java类型,支持使用typeConvertHandler来转换需要映射的类型映射
        FastAutoGenerator.create(new DataSourceConfig.Builder("jdbc:mysql://121.40.156.55:3306/test_db?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull", "suyouliang", "%M5sCQXNUq")
                        .schema("mybatis-plus")
                        .keyWordsHandler(new MySqlKeyWordsHandler()))
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        //禁止打开输出目录
                        .disableOpenDir()
                        //开启 swagger 模式
                        .enableSwagger()
                        .outputDir(".\\"))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build())
                .templateConfig((a) -> new TemplateConfig.Builder()
                        .disable(TemplateType.ENTITY)
                        .entity("/entity/entity.java")
                        .service("/service/service.java")
                        .serviceImpl("/service/impl/serviceImpl.java")
                        .mapper("/mappers/mapper.java")
                        .xml("/mappers/mapper.xml")
                        .controller("/web/controller.java")
                        .build())
                .templateEngine(new FreemarkerTemplateEngine())

                .execute();
    }


    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}