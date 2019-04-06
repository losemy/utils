package com.github.losemy.utils.dal.auto;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: utils
 * Created by lose on 19-4-5 下午3:39
 */
public class CreateTable {

    public static void main(String[] args){


        String name = "/home/lose/tests/test.xlsx";
        List<TableInfo> tables = ReadFromExcelUtil.readFile(name);

        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("mysql.btl");

        Map<String,Object> context = new HashMap<>();
        context.put("tables",tables);
        context.put("db","test");
        String result = template.render(context);

        System.out.println(result);

    }
}
