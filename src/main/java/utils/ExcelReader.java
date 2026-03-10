package utils;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    public static Object[][] getExcelData() throws Exception {

        FileInputStream file =
        new FileInputStream("src/test/resources/testdata.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rows-1][cols];

        for(int i=1;i<rows;i++) {

            String name = sheet.getRow(i).getCell(0).toString();
            String city = sheet.getRow(i).getCell(1).toString();
            String card = sheet.getRow(i).getCell(2).getStringCellValue();

            System.out.println("DATA SOURCE : EXCEL");
            System.out.println("Name: "+name+" City: "+city+" Card: "+card);

            data[i-1][0] = name;
            data[i-1][1] = city;
            data[i-1][2] = card;
        }

        workbook.close();
        return data;
    }
}