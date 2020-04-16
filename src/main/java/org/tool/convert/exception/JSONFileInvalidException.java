package org.tool.convert.exception;

public class JSONFileInvalidException extends RuntimeException {
  
  public JSONFileInvalidException(String message) {
    super(message);
  }
  
  public JSONFileInvalidException(String message, Throwable thr) {
    super(message, thr);
  }

}
