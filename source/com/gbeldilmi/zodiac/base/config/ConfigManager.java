package com.gbeldilmi.zodiac.base.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.gbeldilmi.zodiac.Zodiac;

/**
 * This singleton class manages the configuration of the application. It uses a Config object to store the
 * configuration.
 * @see com.gbeldilmi.zodiac.base.config.Config
 * @see com.gbeldilmi.zodiac.base.config.FileConfig
 * @author gbeldilmi
 */
public final class ConfigManager {
  /**
   * The instance of the ConfigManager class.
   */
  private static ConfigManager instance;
  
  /**
   * The Config object used to store the configuration.
   */
  private Config config;

  /**
   * Innitialize the ConfigManager class.
   */
  static {
    instance = new ConfigManager();
  }

  /**
   * Construct a new ConfigManager object.
   */
  private ConfigManager() {
    config = new FileConfig();
  }

  /**
   * Get a value from the configuration.
   * @param key The key to access the value
   * @return The value associated with the key
   */
  public String get(String key) {
    return config.get(key);
  }

  /**
   * Get the instance of the ConfigManager class.
   * @return The instance of the ConfigManager class
   */
  public static ConfigManager getInstance() {
    return instance;
  }

  /**
   * Set a value in the configuration.
   * @param key The key to access the value
   * @param value The value to set
   */
  public void set(String key, String value) {
    config.set(key, value);
  }

  /**
   * Save the configuration to the configuration file.
   */
  public void save() {
    String filePath = get("conf_file");
    File file = new File(filePath);
    try {
      file.createNewFile();
      FileWriter writer = new FileWriter(file);
      writer.write(config.toString());
      writer.close();
    } catch (IOException e) {
      Zodiac.log.writeLog("Could not save config file " + filePath, e);
    }
  }
}
