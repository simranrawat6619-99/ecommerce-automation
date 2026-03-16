package com.ecommerce.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<String[]> getTestData(String sheetName) throws Exception {
        String path = "src/test/resources/testdata/testdata.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<String[]> data = new ArrayList<>();
        int lastRow = sheet.getLastRowNum();

        for (int i = 1; i <= lastRow; i++) {   // skip header row
            Row row = sheet.getRow(i);
            if (row == null) continue;

            int cellCount = row.getLastCellNum();
            String[] rowData = new String[cellCount];
            for (int j = 0; j < cellCount; j++) {
                Cell cell = row.getCell(j);
                rowData[j] = cell != null ? cell.toString() : "";
            }
            data.add(rowData);
        }

        workbook.close();
        return data;
    }
}
