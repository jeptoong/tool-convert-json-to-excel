package org.tool.convert.jsontoexcel.readjson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTemplate {

  private String sequenceNo;

  private Object data;

  private String responseDescription;

  private int actRow;

  private int noOfRecords;

  private String[] responseParams;

  private String responseCode;

  public String getSequenceNo() {
    return sequenceNo;
  }

  public void setSequenceNo(String sequenceNo) {
    this.sequenceNo = sequenceNo;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getResponseDescription() {
    return responseDescription;
  }

  public void setResponseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
  }

  public int getActRow() {
    return actRow;
  }

  public void setActRow(int actRow) {
    this.actRow = actRow;
  }

  public int getNoOfRecords() {
    return noOfRecords;
  }

  public void setNoOfRecords(int noOfRecords) {
    this.noOfRecords = noOfRecords;
  }

  public String[] getResponseParams() {
    return responseParams;
  }

  public void setResponseParams(String[] responseParams) {
    this.responseParams = responseParams;
  }

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

}
