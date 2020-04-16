package org.tool.convert.exception;

public class ResponseFailureException extends RuntimeException {
  
  public ResponseFailureException(String message) {
    super(message);
  }
  
  public ResponseFailureException(String message, Throwable thr) {
    super(message, thr);
  }

}
