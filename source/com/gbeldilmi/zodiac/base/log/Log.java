package com.gbeldilmi.zodiac.base.log;

interface Log {
  public void close();
  public void writeLog(String text, Exception exception);
}
