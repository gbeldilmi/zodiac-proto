package com.gbeldilmi.zodiac;

import com.gbeldilmi.zodiac.base.config.ConfigManager;
import com.gbeldilmi.zodiac.base.log.LogManager;
import com.gbeldilmi.zodiac.base.service.ServiceManager;

/**
 * The main class for the Zodiac server.
 * @author gbeldilmi
 */
public class Zodiac {
  /**
   * Instance of the ConfigManager class to access it from anywhere.
   * @see com.gbeldilmi.zodiac.base.config.ConfigManager
   */
  public static final ConfigManager config = ConfigManager.getInstance();

  /**
   * Instance of the LogManager class to access it from anywhere.
   * @see com.gbeldilmi.zodiac.base.log.LogManager
   */
  public static final LogManager log = LogManager.getInstance();

  /**
   * Instance of the ServiceManager class to access it from anywhere.
   * @see com.gbeldilmi.zodiac.base.service.ServiceManager
   */
  public static final ServiceManager services = ServiceManager.getInstance();

  /**
   * The main method for the Zodiac server. Innitialize the shutdown hook and start up the server.
   * @param args The command line arguments (not used)
   */
  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    startUp();
    try {
      Thread.currentThread().join();
    } catch (InterruptedException e) {
      log.writeLog("Interrupted while waiting for shutdown.", e);
    }
  }

  /**
   * Start up the server and start all services.
   */
  private static void startUp() {
    Zodiac.log.writeLog("Starting up...");
    //service.addService(new Service());
    Zodiac.services.startAll();
  }
}
