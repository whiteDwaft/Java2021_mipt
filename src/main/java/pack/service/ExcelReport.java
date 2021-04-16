package pack.service;

import org.apache.poi.ss.usermodel.Workbook;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExcelReport {
    private final Workbook workbook;

    public ExcelReport(Workbook workbook) {
        this.workbook = workbook;
    }


    public byte[] asBytes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.workbook.write(baos);
        return baos.toByteArray();
    }


    public void writeTo(OutputStream os) {
        try {
            os.write(asBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
