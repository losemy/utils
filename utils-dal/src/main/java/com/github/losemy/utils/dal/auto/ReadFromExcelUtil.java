package com.github.losemy.utils.dal.auto;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: utils
 * Created by lose on 19-4-5 下午3:36
 */
public class ReadFromExcelUtil {

    public static List<TableInfo> readFile(String name){
        List<TableInfo> tables = new ArrayList<TableInfo>();

        ExcelReader reader = ExcelUtil.getReader(name);


        List<String> sheetNames = reader.getSheetNames();
        if(sheetNames != null){

            for(String sheet : sheetNames){
                TableInfo table = new TableInfo();
                //内容 test2(12) 获取 test2 跟 12
                String tableName = StrUtil.sub(sheet,0,sheet.indexOf('(') == -1 ? sheet.indexOf('（'): sheet.indexOf('('));
                String tableComment = StrUtil.sub(sheet,sheet.indexOf('(') == -1 ? sheet.indexOf('（'): sheet.indexOf('('),sheet.length()-1);
                table.setName(tableName);
                table.setComment(tableComment);
                ExcelReader sheetReader = ExcelUtil.getReader(FileUtil.file(name),sheet);
                addHeaderAlias(sheetReader);
                List<DataModel> datas = sheetReader.readAll(DataModel.class);
                List<ColumnInfo> columnInfos = new ArrayList<>();
                List<IndexInfo> indexInfos = new ArrayList<>();
                table.setColumnInfos(columnInfos);
                table.setIndexInfos(indexInfos);
                for(DataModel data : datas){
                    //不进行后续操作
                    if("index".equalsIgnoreCase(data.getType())){
                        // 索引数据添加
                        IndexInfo indexInfo = new IndexInfo();
                        indexInfo.setIndexColumns(data.getIndexColumns());
                        indexInfo.setName(data.getName());
                        indexInfo.setType(data.getType());
                        indexInfos.add(indexInfo);
                        continue;
                    }

                    //TODO copyBean 的方式快速复制
                    ColumnInfo columnInfo = new ColumnInfo();
                    columnInfo.setAllowNull("Y".equalsIgnoreCase(data.getAllowNull())? true:false);
                    columnInfo.setComment(data.getComment());
                    columnInfo.setName(data.getName());
                    columnInfo.setType(data.getType());
                    columnInfos.add(columnInfo);

                }
                tables.add(table);
            }

        }

        return tables;

    }

    private static void addHeaderAlias(ExcelReader reader) {
        //name(名称) | type（类型(数据库字段类型，INDEX)） | comment(描述)  | allowNull(是否为空(Y/N))|indexColumns(索引列，逗号隔开)
//        reader.addHeaderAlias("name","名称");
//        reader.addHeaderAlias("type","类型");
//        reader.addHeaderAlias("comment","描述");
//        reader.addHeaderAlias("allowNull","是否为空(Y/N)");
////        reader.addHeaderAlias("unique","唯一(Y/N)");
//        reader.addHeaderAlias("indexColumns","索引列");

        reader.addHeaderAlias("名称","name");
        reader.addHeaderAlias("类型","type");
        reader.addHeaderAlias("描述","comment");
        reader.addHeaderAlias("是否为空(Y/N)","allowNull");
        reader.addHeaderAlias("索引列","indexColumns");
    }

    public static void main(String[] args){
        String name = "/home/lose/tests/test.xlsx";
        System.out.println(readFile(name));

    }
}
