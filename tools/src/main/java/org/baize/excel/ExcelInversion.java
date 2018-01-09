package org.baize.excel;

import org.apache.poi.hssf.usermodel.*;
import org.baize.LoggerUtils;
import org.baize.StringUtils;
import org.baize.assemblybean.annon.ExcelValue;
import org.baize.assemblybean.service.CodeModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/11.
 * 描述：
 */
public class ExcelInversion {
    public static Set<CodeModel> err = new HashSet<>();
    private static List<ExcelErrorCodeModule> modules = new ArrayList<>();
    private static void inversion(Object o, List<ExcelErrorCodeModule> modules) {
        //创建HSSFWorkbook
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步创建sheet
        HSSFSheet sheet = wb.createSheet(o.getClass().getSimpleName());
        //第三步创建行row:添加表头0行
        HSSFRow row = null;
        for(int i = 0;i<3;i++) {
            row = sheet.createRow(i);
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中
            //第四步创建单元格
            HSSFCell cell = row.createCell(0);         //第一个单元格
            cell.setCellValue("id");                  //设定值
            cell.setCellStyle(style);                   //内容居中

            cell = row.createCell(1);                   //第二个单元格
            cell.setCellValue("name");
            cell.setCellStyle(style);

            cell = row.createCell(2);                   //第三个单元格
            cell.setCellValue("value");
            cell.setCellStyle(style);
        }
        //第五步插入数据
        List<ExcelErrorCodeModule> list = modules;
        for (int i = 0; i < list.size(); i++) {
            ExcelErrorCodeModule errorCondition = list.get(i);
            //创建行
            row = sheet.createRow(i + 3);
            //创建单元格并且添加数据
            row.createCell(0).setCellValue(errorCondition.getId());
            row.createCell(1).setCellValue(errorCondition.getName());
            row.createCell(2).setCellValue(errorCondition.getValue());
        }

        //第六步将生成excel文件保存到指定路径下
        try {
            FileOutputStream fout = new FileOutputStream("E:\\cs\\"+ StringUtils.uncapitalize(o.getClass().getSimpleName())+"_"+o.getClass().getName()+".xlsx");
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            LoggerUtils.getPlatformLog().info("将生成excel文件保存到指定路径下时出现异常",e);
        }

    }
    public static void reflectBean() {
        if (err.size() <= 0) return;
        Iterator<CodeModel> iterator = err.iterator();
        while (iterator.hasNext()) {
            Object o = null;
            try {
                CodeModel model = iterator.next();
                o = model.getClazz().newInstance();
                reflectField(o);
            } catch (Exception e) {
                LoggerUtils.getPlatformLog().info("反射对象是出现异常",e);
            }
        }
    }

    private static void reflectField(Object o) {
        List<ExcelErrorCodeModule> modules = new ArrayList<>();
        Field[] fields = o.getClass().getDeclaredFields();
        if (fields.length<=0)
            return;
        for (Field f:fields) {
            Annotation ann = f.getAnnotation(ExcelValue.class);
            String name = f.getName();
            String value = "";
            String id = "";
            try {
                id = f.get(o).toString();
            } catch (IllegalAccessException e) {
                LoggerUtils.getPlatformLog().info(" 获取id是出现异常",e);
            }
            if (ann instanceof ExcelValue) {
                ExcelValue ev = (ExcelValue) ann;
                value =  ev.value();
            }
            modules.add(new ExcelErrorCodeModule(Integer.parseInt(id),name,value));
        }
        inversion(o,modules);
    }
}
