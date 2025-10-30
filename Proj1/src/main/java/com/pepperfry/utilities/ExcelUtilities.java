package com.pepperfry.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

    public static Object[][] getdata(String excelpath, String sheetname) throws InvalidFormatException, IOException {

        File file1 = new File(excelpath);
        XSSFWorkbook workbook = new XSSFWorkbook(file1);
        XSSFSheet worksheet = workbook.getSheet(sheetname);

        int rowcount = worksheet.getPhysicalNumberOfRows();
        int colcount = worksheet.getRow(0).getPhysicalNumberOfCells();

        System.out.println("Excel sheet: " + sheetname + " | Rows: " + rowcount + " | Cols: " + colcount);

        // Prepare temporary storage with maximum size
        Object[][] tempData = new Object[rowcount][colcount];
        int actualRowCount = 0;

        for (int i = 0; i < rowcount; i++) {
            XSSFRow row = worksheet.getRow(i);
            if (row == null || row.getCell(0) == null) {
                continue; // skip blank rows
            }

            boolean isEmpty = true;
            for (int j = 0; j < colcount; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell != null && !cell.toString().trim().isEmpty()) {
                    isEmpty = false;
                }
            }

            if (isEmpty) continue;

            for (int j = 0; j < colcount; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null)
                    tempData[actualRowCount][j] = "";
                else
                    tempData[actualRowCount][j] = cell.toString().trim();
            }
            actualRowCount++;
        }

        // Copy only non-empty rows to final array
        Object[][] data = new Object[actualRowCount][colcount];
        for (int i = 0; i < actualRowCount; i++) {
            System.arraycopy(tempData[i], 0, data[i], 0, colcount);
        }

        workbook.close();
        System.out.println("Valid data rows found: " + actualRowCount);
        return data;
    }
}
