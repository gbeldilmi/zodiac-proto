package com.gbeldilmi.zodiac.base.log;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.gbeldilmi.zodiac.util.DateUtil;

/**
 * This class is used to write log messages in a file.
 * @see com.gbeldilmi.zodiac.base.log.Log
 * @see com.gbeldilmi.zodiac.base.log.LogManager
 * @author gbeldilmi
 */
class LogFile implements Log {
  /**
   * The file used to write log messages.
   */
  private File logFile;

  /**
   * The PrintWriter used to write log messages.
   */
  private PrintWriter writer;

  /**
   * Construct a new LogFile object.
   * @param logDirectory The directory where the log file will be created
   * @throws IOException If an error occurs while creating the log file
   */
  public LogFile(String logDirectory) throws IOException {
    logFile = new File(logDirectory + "/log-" + DateUtil.getDateString("YYYY-MM-DD-HH-mm-ss") + ".txt");
    open();
  }

  /**
   * Close the log file.
   */
  public void close() {
    writer.close();
  }

  /**
   * Open the log file.
   * @throws IOException If an error occurs while creating the log file
   */
  private void open() throws IOException {
    if (!logFile.getParentFile().exists()) {
      logFile.getParentFile().mkdirs();
    }
    logFile.createNewFile();
    writer = new PrintWriter(logFile);
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
    msg.append(DateUtil.getDateString("YYYY-MM-DD HH:mm:ss") + " : " + text);
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
