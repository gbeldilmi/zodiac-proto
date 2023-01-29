package com.gbeldilmi.zodiac.base.log;

import java.io.IOException;

import com.gbeldilmi.zodiac.Zodiac;

public class LogManager {
  private Log log;
  
  public LogManager() {
    newLog();
  }

  public void closeLog() {
    log.close();
  }

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

  public void writeLog(String message) {
    writeLog(message, null);
  }

  public void writeLog(String message, Exception exception) {
    log.writeLog(message, exception);
  }
}
