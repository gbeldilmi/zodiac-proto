package com.gbeldilmi.zodiac;

class ShutdownHook extends Thread {
  public void run() {
    Zodiac.log.writeLog("Shutting down...");
    Zodiac.services.stopAll();
    Zodiac.log.closeLog();
  }
}
