package com.gbeldilmi.zodiac.base.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.gbeldilmi.zodiac.Zodiac;

public class ConfigManager {
  private Config config;

  public ConfigManager() {
    config = new FileConfig();
  }

  public String get(String key) {
    return config.get(key);
  }

  public void set(String key, String value) {
    config.set(key, value);
  }

  public void save() {
    String filePath = get("config_file");
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
