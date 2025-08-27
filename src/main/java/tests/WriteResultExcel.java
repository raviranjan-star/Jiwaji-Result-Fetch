package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteResultExcel {

	static int start=0;
    private static String filePath = "testData/AutomationResults.xlsx";

    public static void writeResult(String rollNo, String name, String marks, String result) throws Exception {
        Workbook workbook;
        Sheet sheet;

        File file = new File(filePath);
        if (file.exists()) {
            // Open existing file
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } else {
            // Create new workbook
            workbook = new XSSFWorkbook();
        }

        // Get or create sheet
        sheet = workbook.getSheet("Results");
       
        if (sheet == null) {
            sheet = workbook.createSheet("Results");
        }
        if(start==0) {
        	// Clear old rows
        	int lastRow = sheet.getLastRowNum();
        	for (int i = lastRow; i >= 0; i--) {
        	    Row row = sheet.getRow(i);
        	    if (row != null) {
        	        sheet.removeRow(row);
        	    }
        	}
        	start++;

        }
        // Create header row if empty
        if (sheet.getPhysicalNumberOfRows() == 0) {
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Roll No");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Marks");
            header.createCell(3).setCellValue("Result");
        }

        // Find next empty row
        int rowIndex = sheet.getLastRowNum() + 1;

        // Create data row
        Row row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue(rollNo);
        row.createCell(1).setCellValue(name);
        String obtainedMarks = marks.contains("/") ? marks.split("/")[0] : marks;
        int obtainedMark = Integer.parseInt(obtainedMarks);
        row.createCell(2).setCellValue(obtainedMark);
        row.createCell(3).setCellValue(result);

        // Save changes
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}
