package pack.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import pack.ColumnName;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelReportGenerator<T>  {

    public ExcelReport generate(List<T> entities) throws IllegalAccessException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Data");
        Field[] fields = entities.get(0).getClass().getDeclaredFields();
        Row row = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            Cell name = row.createCell(i);
            if(fields[i].isAnnotationPresent(ColumnName.class)){
                name.setCellValue(fields[i].getAnnotation(ColumnName.class).name());
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            T entity = entities.get(i);
            Row row1 = sheet.createRow(i+1);
            Field[] fields2 = entity.getClass().getDeclaredFields();
            for (int j = 0; j < fields2.length; j++) {
                Field field = fields2[j];
                field.setAccessible(true);
                Cell name = row1.createCell(j);
                name.setCellValue(field.get(entity).toString());
            }
        }
        return new ExcelReport(book);
    }
}
