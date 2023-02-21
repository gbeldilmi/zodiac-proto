package com.gbeldilmi.zodiac.base.log;

/**
 * This interface is used to describe a class able to log the events that occur in the server.
 * @author gbeldilmi
 */
interface Log {
  /**
   * This method is used to close the log (used to close file, streams, etc.)
   */
  public void close();

  /**
   * This method is used to write a log message.
   * @param text The text to write
   * @param exception The exception to write details about (ignored if null)
   */
  public void writeLog(String text, Exception exception);
}
