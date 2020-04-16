package org.tool.convert.jsontoexcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.tool.convert.jsontoexcel.readjson.ReadJson;
import org.tool.convert.jsontoexcel.writeexcel.WorkbookData;
import org.tool.convert.jsontoexcel.writeexcel.WriteExcel;

public class ConvertJsonToExcel {

  private final ReadJson readJson;
  private final WriteExcel writeExcel;

  public ConvertJsonToExcel() {
    readJson = new ReadJson();
    writeExcel = new WriteExcel();
  }

  private void convert() {
    String fileNew = "C:/Users/PhamHung/Desktop/trx-history-new.json";
    String fileOld = "C:/Users/PhamHung/Desktop/trx-history-old.json";

    List<Map<String, String>> dataNew = readJson.readListFromFile(fileNew);
    List<Map<String, String>> dataOld = readJson.readListFromFile(fileOld);

    WorkbookData<List<Map<String, String>>> workbookData = new WorkbookData<List<Map<String, String>>>();
    if(null != dataNew && dataNew.size() > 0) {
      // Set header
      List<String> header = new ArrayList<String>(dataNew.get(0).keySet());
      workbookData.setHeader(header);
      // Set sheet name
      workbookData.setSheetName(Arrays.asList("Result_Java", "Result_SQL"));
      // Set data
      workbookData.setData(Arrays.asList(dataNew, dataOld));
      writeExcel.writeExcel("C:/Users/PhamHung/Desktop/result_trx_history_2/n. result_user_id_.xlsx", workbookData);
    }
    System.out.println("Finish!");
  }

  public static void run() {
    new ConvertJsonToExcel().convert();
  }
}
