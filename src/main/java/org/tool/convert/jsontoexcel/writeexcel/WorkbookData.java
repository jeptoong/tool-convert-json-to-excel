package org.tool.convert.jsontoexcel.writeexcel;

import java.util.List;

public class WorkbookData<T> {

  private List<String> header;

  private List<String> sheetName;

  private List<T> data;

  public List<String> getHeader() {
    return header;
  }

  public void setHeader(List<String> header) {
    this.header = header;
  }

  public List<String> getSheetName() {
    return sheetName;
  }

  public void setSheetName(List<String> sheetName) {
    this.sheetName = sheetName;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

}
