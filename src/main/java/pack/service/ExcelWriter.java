package pack.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class ExcelWriter {

    public static <T> void writeReport(List<T> objectList) throws IllegalAccessException, FileNotFoundException {
        ExcelReportGenerator<T> excelReportGenerator = new ExcelReportGenerator<>();
        ExcelReport excelReport = excelReportGenerator.generate(objectList);
        OutputStream os1 = new FileOutputStream("file.xlsx");
        excelReport.writeTo(os1);
    }
}
