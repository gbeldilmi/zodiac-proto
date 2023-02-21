package com.gbeldilmi.zodiac;

/**
 * This class implements a shutdown hook for the Zodiac server.
 * @author gbeldilmi
 */
class ShutdownHook extends Thread {
  /**
   * Stop all services and close the log file.
   * @see com.gbeldilmi.zodiac.Zodiac
   */
  public void run() {
    Zodiac.log.writeLog("Shutting down...");
    Zodiac.services.stopAll();
    Zodiac.log.closeLog();
  }
}
