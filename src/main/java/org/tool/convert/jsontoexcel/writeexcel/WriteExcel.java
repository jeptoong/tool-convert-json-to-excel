package org.tool.convert.jsontoexcel.writeexcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

  public void writeExcel(String path, WorkbookData<List<Map<String, String>>> data) {
    Workbook workbook = new XSSFWorkbook();

    // Sheet sheet = workbook.createSheet("Persons");
    // sheet.setColumnWidth(0, 6000);
    // sheet.setColumnWidth(1, 4000);

    CellStyle headerStyle = createStyle(workbook, 48, 1);

    XSSFFont fontHeader = createFont(workbook, 10, true);
    headerStyle.setFont(fontHeader);

    // Get header
    List<String> headerData = data.getHeader();
    // Get sheet name
    List<String> sheetNames = data.getSheetName();
    // Get data for sheet
    List<List<Map<String, String>>> sheetDatas = data.getData();

    if(null != sheetDatas && null != sheetNames && sheetDatas.size() > 0 && sheetDatas.size() == sheetNames.size()) {
      for (int indexSheet = 0; indexSheet < sheetDatas.size(); indexSheet++) {
        int indexRow = 0;
        String sheetName = sheetNames.get(indexSheet);
        List<Map<String, String>> sheetData = sheetDatas.get(indexSheet);

        Sheet sheet = workbook.createSheet(sheetName);

        if(null != headerData && headerData.size() > 0) {
          // Create row
          Row header = sheet.createRow(indexRow++);
          Cell headerCell = null;

          for (int indexHeader = 0; indexHeader < headerData.size(); indexHeader++) {
            String headerValue = headerData.get(indexHeader);
            headerCell = header.createCell(indexHeader);
            headerCell.setCellValue(headerValue);
            headerCell.setCellStyle(headerStyle);
          }
        }

        // Write data for sheet
        if(null != sheetData && sheetData.size() > 0) {
          CellStyle cellStyle = createStyle(workbook, 48, 1);

          for (int indexData = 0; indexData < sheetData.size(); indexData++) {
            Map<String, String> dataRow = sheetData.get(indexData);

            Row row = sheet.createRow(indexRow++);
            if(null != dataRow && dataRow.size() == headerData.size()) {
              for (int indexCell = 0; indexCell < headerData.size(); indexCell++) {
                String rowData = String.valueOf(dataRow.get(headerData.get(indexCell)));
                Cell cell = row.createCell(indexCell);
                cell.setCellValue(rowData);
                cell.setCellStyle(cellStyle);
              }
            }
          }

          // Check diff
          /* Access conditional formatting facet layer */
          SheetConditionalFormatting sheetConditionalFormatting = sheet.getSheetConditionalFormatting();
          String anotherSheet = sheetNames.stream().filter(sName -> !sName.equals(sheetName)).findFirst().orElse("");
          ConditionalFormattingRule rule = sheetConditionalFormatting.createConditionalFormattingRule("=A1:Y1000<>" + anotherSheet + "!A1:Y1000");
          PatternFormatting fill = rule.createPatternFormatting();
          fill.setFillBackgroundColor(IndexedColors.YELLOW.index);
          fill.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

          ConditionalFormattingRule[] cfRules = new ConditionalFormattingRule[] { rule };

          CellRangeAddress[] regions = new CellRangeAddress[] { CellRangeAddress.valueOf("A1:Y1000") };

          sheetConditionalFormatting.addConditionalFormatting(regions, cfRules);
        }
      }

      saveFile(workbook, path);
    }

  }

  private CellStyle createStyle(Workbook workbook, int color, int foreGround) {
    CellStyle headerStyle = workbook.createCellStyle();
    // headerStyle.setFillForegroundColor(IndexedColors.fromInt(color).getIndex());
    // headerStyle.setFillPattern(FillPatternType.forInt(foreGround));

    return headerStyle;
  }

  private XSSFFont createFont(Workbook workbook, int fontSize, boolean bold) {
    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
    font.setFontName("Arial");
    font.setFontHeightInPoints((short) fontSize);
    font.setBold(bold);

    return font;
  }

  private void saveFile(Workbook workbook, String path) {
    FileOutputStream outputStream;
    try {
      outputStream = new FileOutputStream(path);

      workbook.write(outputStream);
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
