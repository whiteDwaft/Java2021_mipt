package pack.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import pack.ColumnName;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelReportGenerator<T> {

    public XSSFWorkbook generate(List<T> entities) throws IllegalAccessException, IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Data");
        sheet.createFreezePane(0, 1);
        Field[] fields = entities.get(0).getClass().getDeclaredFields();
        Row row = sheet.createRow(0);
        Font font = book.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        CellStyle cellStyle = book.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.ALT_BARS);
        for (int i = 0; i < fields.length; i++) {
            Cell name = row.createCell(i);
            if (fields[i].isAnnotationPresent(ColumnName.class)) {
                name.setCellValue(fields[i].getAnnotation(ColumnName.class).name());
                name.setCellStyle(cellStyle);
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            T entity = entities.get(i);
            Row row1 = sheet.createRow(i + 1);
            Field[] fields2 = entity.getClass().getDeclaredFields();
            for (int j = 0; j < fields2.length; j++) {
                Field field = fields2[j];
                field.setAccessible(true);
                Cell name = row1.createCell(j);
                if (field.get(entity) instanceof Integer) {
                    name.setCellValue(Integer.parseInt(field.get(entity).toString()));
                } else {
                    name.setCellValue(field.get(entity).toString());
                }
            }
        }

        return book;
    }
}
