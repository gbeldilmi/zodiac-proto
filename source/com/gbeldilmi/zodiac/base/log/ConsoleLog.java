package com.gbeldilmi.zodiac.base.log;

import java.io.PrintWriter;

/**
 * This class is used to write log messages to the console.
 * @see com.gbeldilmi.zodiac.base.log.Log
 * @see com.gbeldilmi.zodiac.base.log.LogManager
 * @author gbeldilmi
 */
class ConsoleLog implements Log {
  /**
   * The PrintWriter used to write log messages.
   */
  private PrintWriter writer;

  /**
   * Construct a new ConsoleLog object.
   */
  public ConsoleLog() {
    writer = new PrintWriter(System.err);
  }

  /**
   * Close the log file.
   */
  public void close() {
    writer.close();
  }

  /**
   * Write a log message.
   * @param text The text to write
   * @param exception The exception to write details about (ignored if null)
   */
  public void writeLog(String text, Exception exception) {
    StringBuilder msg = new StringBuilder();
    StackTraceElement[] stackTrace;
    int i;
    msg.append(text);
    if (exception != null) {
      msg.append("\n " + exception.getMessage());
      stackTrace = exception.getStackTrace();
      for (i = 0; i < stackTrace.length; i++) {
        msg.append("\n" + stackTrace[i].toString());
      }
    }
    writer.println(msg.toString());
  }
}
