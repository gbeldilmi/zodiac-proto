package com.gbeldilmi.zodiac.base.log;

import java.io.PrintWriter;

class ConsoleLog implements Log {
  private PrintWriter writer;
  public ConsoleLog() {
    writer = new PrintWriter(System.err);
  }

  public void close() {
    writer.close();
  }

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
