package com.gbeldilmi.zodiac;

import com.gbeldilmi.zodiac.base.config.ConfigManager;
import com.gbeldilmi.zodiac.base.log.LogManager;
import com.gbeldilmi.zodiac.base.service.ServiceManager;

public class Zodiac {
  public static final ConfigManager config = new ConfigManager();
  public static final LogManager log = new LogManager();
  public static final ServiceManager services = new ServiceManager();

  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new ShutdownHook());
    Zodiac.log.writeLog("Starting up...");
    //service.addService(new Service());
    Zodiac.services.startAll();
  }
}
