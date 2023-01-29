package com.gbeldilmi.zodiac.base.config;

import java.util.Hashtable;
import java.util.Map;

import com.gbeldilmi.zodiac.util.DateUtil;

class Config {
  protected Hashtable<String, String> config;

  public Config() {
    config = new Hashtable<String, String>();
    load();
  }

  public String get(String key) {
    return config.get(key);
  }

  private void load() {
    config.put("path", System.getProperty("user.home") + "/zodiac");
    config.put("config_file", get("path") + "/config.zodiac");
    config.put("log_dir", get("path") + "/logs");
    config.put("web_port", "7767");
    config.put("api_port", "7768");
  }

  public void set(String key, String value) {
    config.put(key, value);
  }
  
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("# Configuration file for the Zodiac server\n");
    builder.append("# Generated on " + DateUtil.getDateString("YYYY-MM-DD HH:mm:ss") + "\n");
    for (Map.Entry<String, String> entry : config.entrySet()) {
      builder.append(entry.getKey() + " " + entry.getValue() + "\n");
    }
    return builder.toString();
  }
}
