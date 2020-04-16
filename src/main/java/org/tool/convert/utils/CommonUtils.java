package org.tool.convert.utils;

public class CommonUtils {

  private CommonUtils() {
  }

  public static boolean isEmpty(String value) {
    return null == value || value.isEmpty();
  }

  public static String trim(String value) {
    return !isEmpty(value) ? value.trim() : "";
  }

}
