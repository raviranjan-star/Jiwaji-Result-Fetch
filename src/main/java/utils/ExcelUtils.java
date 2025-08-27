package utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {
    private Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);
        workbook.close();
    }

    public String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) return "";
        Cell cell = row.getCell(colIndex);
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    public String[][] getRangeData(int startRow, int endRow, int startCol, int endCol) {
        int totalRows = (endRow - startRow) + 1;
        int totalCols = (endCol - startCol) + 1;

        String[][] data = new String[totalRows][totalCols];

        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i);
            for (int j = startCol; j <= endCol; j++) {
                if (row != null) {
                    Cell cell = row.getCell(j);
                    data[i - startRow][j - startCol] =
                        (cell == null) ? "" : getCellData(i, j);
                } else {
                    data[i - startRow][j - startCol] = "";
                }
            }
        }
        return data;
    }
}
