package pack.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    static int counter = 1;

    public static <T> void writeReport(List<T> objectList, boolean isDrawing) throws IllegalAccessException, IOException {
        ExcelReportGenerator<T> excelReportGenerator = new ExcelReportGenerator<>();
        XSSFWorkbook book = excelReportGenerator.generate(objectList);
        if (isDrawing) {
            book = ExcelDrawing.draw(book, objectList.size());
        }
        FileOutputStream os1 = new FileOutputStream("file_" + counter++ +".xlsx");
        book.write(os1);
    }
}
