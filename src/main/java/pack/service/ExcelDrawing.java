package pack.service;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelDrawing {

    public static XSSFWorkbook draw(XSSFWorkbook book, int count){

        XSSFSheet sheet = book.getSheet("Data");
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, count+1, 20, 35);

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("BarChart");
        chart.setTitleOverlay(false);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(org.apache.poi.xddf.usermodel.chart.AxisPosition.BOTTOM);
        bottomAxis.setTitle("x");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("f(x)");
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, count, 0, 0));
        XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, count, 1, 1));

        XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        XDDFChartData.Series series1 = data.addSeries(xs, ys1);
        series1.setTitle("2x", null);
        chart.plot(data);

        return book;
    }
}
