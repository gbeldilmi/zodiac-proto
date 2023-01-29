package com.gbeldilmi.zodiac.base.log;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.gbeldilmi.zodiac.util.DateUtil;

class LogFile implements Log {
  private File logFile;
  private PrintWriter writer;

  public LogFile(String logDirectory) throws IOException {
    logFile = new File(logDirectory + "/log-" + DateUtil.getDateString("YYYY-MM-DD-HH-mm-ss") + ".txt");
    open();
  }

  public void close() {
    writer.close();
  }

  private void open() throws IOException {
    if (!logFile.getParentFile().exists()) {
      logFile.getParentFile().mkdirs();
    }
    logFile.createNewFile();
    writer = new PrintWriter(logFile);
  }

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
