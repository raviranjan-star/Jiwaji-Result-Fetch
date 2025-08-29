package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteResultExcel {

	static int start=0;
    private static String filePath = "testData/AutomationResults.xlsx";

    public static void writeResult(String rollNo, String name, String marks, String result, String college) throws Exception {
        Workbook workbook;
        Sheet sheet;

        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
        }
        sheet = workbook.getSheet("Results");
       
        if (sheet == null) {
            sheet = workbook.createSheet("Results");
        }
        if(start==0) {
        	int lastRow = sheet.getLastRowNum();
        	for (int i = lastRow; i >= 0; i--) {
        	    Row row = sheet.getRow(i);
        	    if (row != null) {
        	        sheet.removeRow(row);
        	    }
        	}
        	start++;

        }
        if (sheet.getPhysicalNumberOfRows() == 0) {
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Roll No");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Marks");
            header.createCell(3).setCellValue("Result");
            header.createCell(4).setCellValue("College");
        }

        int rowIndex = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue(rollNo);
        row.createCell(1).setCellValue(name);
        String obtainedMarks = marks.contains("/") ? marks.split("/")[0] : marks;
        int obtainedMark = Integer.parseInt(obtainedMarks);
        row.createCell(2).setCellValue(obtainedMark);
        row.createCell(3).setCellValue(result);
        row.createCell(4).setCellValue(college);
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}
