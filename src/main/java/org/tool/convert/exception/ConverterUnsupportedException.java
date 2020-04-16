package org.tool.convert.exception;

public class ConverterUnsupportedException extends RuntimeException {
  
  public ConverterUnsupportedException(String message) {
    super(message);
  }
  
  public ConverterUnsupportedException(String message, Throwable thr) {
    super(message, thr);
  }

}
