package com.javaSE.generator.mysql;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author danan.fu
 * @date 2022/3/4
 */
public class Generator {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String modulesName = scanner("模块名");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = "D:/dev/interface-ekp-mdm-sync";
        String projectPath = "D:/e/code/working";
//        gc.setOutputDir("D:/dev/interface-ekp-mdm-sync/src/main/java/");
        gc.setOutputDir("D:/e/code/working/src/main/java/");
        gc.setAuthor("fdn");
        //生成后是否打开资源管理器
        gc.setOpen(false);
        gc.setBaseColumnList(true);
        //定义生成的实体类中日期类型
        gc.setDateType(DateType.ONLY_DATE);
        //重新生成时文件是否覆盖
        gc.setFileOverride(false);
        //采用注解式@TableField 不需要resultMap
        gc.setBaseResultMap(false);
        mpg.setGlobalConfig(gc);
        gc.setSwagger2(true);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:oracle:thin:@10.1.1.88:1521:orcl");
        dsc.setUrl("jdbc:mysql://localhost:3306/fudn_study_mp");
//        dsc.setDbType(DbType.ORACLE);
        dsc.setDbType(DbType.MYSQL);
        //dsc.setSchemaName("public");
//        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");//需要导入对应的jar包
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");//需要导入对应的jar包
//        dsc.setUsername("ekpt3");
        dsc.setUsername("root");
//        dsc.setPassword("ekpt2");
        dsc.setPassword("fu1112111");
        mpg.setDataSource(dsc);

        // 包配置，决定了类的包名，需要填写准确
        PackageConfig pc = new PackageConfig();
        //modulesName可以理解为Controller的上一级目录
        pc.setModuleName(modulesName);
//        pc.setParent("com.tcl.ekp.sync.modules");
        pc.setParent("com.fudn.mybatisplusdemo.module");
        pc.setEntity("entity");

//        pc.setController("controller");
//        pc.setService("service");
//        pc.setMapper("mapper");
/**
 * 模块名shop，表名SHOP_TOUR_PLAN的时候，controller等类的名称会去掉shop
 * 自定义的mapper.xml则会忽略掉shop包名；
 *
 * 解决：模块名不要用shop，可以使用app.shop，这样就不会忽略掉表的前缀了
 * 要注意修改controller里面的地址
 *
 */
//        // controller 命名方式，注意 %s 会自动填充表实体属性
//        gc.setControllerName("%sController");
//        // service 命名方式; mp生成service层代码，默认接口名称第一个字母有 I
//        //去掉Service接口的首字母I
//        gc.setServiceName("%sService");
//        // serviceImpl 命名方式
//        gc.setServiceImplName("%sServiceImpl");
//        // mapper 命名方式
//        gc.setMapperName("%sMapper");
//        // xml 命名方式
//        gc.setXmlName("%sMapper");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
//                return projectPath + "/src/main/resources/mapper/modules/mdm/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return projectPath + "/src/module/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // templateConfig.setEntity();
        // templateConfig.setService();
        // templateConfig.setController();
        //templateConfig.setServiceImpl(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //实体没有公共的父类
//        strategy.setSuperEntityClass();
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        //3.0.6,3.0.7 驼峰策略bug(驼峰策略不会生成@TableField)，暂时强制生成@TableField
        strategy.setEntityTableFieldAnnotationEnable(true);
//        strategy.setSuperControllerClass("com.bee.core.base.BaseController");
        //可以一次生成多张表，直接写入表名就可以
//        strategy.setInclude(scanner("表名"));
        strategy.setInclude("t_user");
        //strategy.setInclude("sap_receiver_delivery_hd_log");
        strategy.setControllerMappingHyphenStyle(false);
        // 模块名和表的前缀相同，需要注释下行代码，不然会丢失表前缀，例如：cpms 下需要逆向生成 CPMS_ORDER 表
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

