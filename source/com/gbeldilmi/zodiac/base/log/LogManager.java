package com.gbeldilmi.zodiac.base.log;

import java.io.IOException;

import com.gbeldilmi.zodiac.Zodiac;

/**
 * This singleton class is used to manage the log file. It's a singleton class, and it's used to write log messages.
 * @see com.gbeldilmi.zodiac.base.log.Log
 * @see com.gbeldilmi.zodiac.base.log.LogFile
 * @see com.gbeldilmi.zodiac.base.log.ConsoleLog
 * @author gbeldilmi
 */
public final class LogManager {
  /**
   * The instance of the LogManager class.
   */
  private static LogManager instance;

  /**
   * The Log object used to write log messages.
   */
  private Log log;
  
  /**
   * Innitialize the LogManager class.
   */
  static {
    instance = new LogManager();
  }

  /**
   * Construct a new LogManager object.
   */
  private LogManager() {
    newLog();
  }

  /**
   * Close log.
   */
  public void closeLog() {
    log.close();
  }

  /**
   * Get the instance of the LogManager class.
   * @return The instance of the LogManager class
   */
  public static LogManager getInstance() {
    return instance;
  }

  /**
   * Open a new log.
   */
  private void newLog() {
    if (log != null) {
      log.close();
    }
    try {
      log = new LogFile(Zodiac.config.get("log_dir"));
    } catch (IOException e) {
      log = new ConsoleLog();
      Zodiac.log.writeLog("Unable to create log file", e);
    }
  }

  /**
   * Write a log message.
   * @param message The message to write
   */
  public void writeLog(String message) {
    writeLog(message, null);
  }

  /**
   * Write a log message.
   * @param message The message to write
   * @param exception The exception to write details about
   */
  public void writeLog(String message, Exception exception) {
    log.writeLog(message, exception);
  }
}
