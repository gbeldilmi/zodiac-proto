package com.gbeldilmi.zodiac.base.config;

import java.util.Hashtable;
import java.util.Map;

import com.gbeldilmi.zodiac.util.DateUtil;

/**
 * This class is used to store the configuration of the server as couples of key/value.
 * The default configuration is as follows:
 * - path : The path to the Zodiac directory (default: /home/user/zodiac)
 * - conf_dir : The path to the configuration directory (default: /etc/zodiac)
 * - log_dir : The path to the log directory (default: /var/log/zodiac)
 * - temp_dir : The path to the temporary directory (default: /tmp/zodiac)
 * - conf_file : The path to the configuration file (default: /etc/zodiac/server.conf)
 * - web_port : The port used by the web server (default: 7767)
 * - api_port : The port used by the API server (default: 7768)
 * @author gbeldilmi
 */
class Config {
  /**
   * The configuration stored as a hashtable. The key is the name of the configuration and the value is the value of
   * the configuration.
   * @see java.util.Hashtable
   */
  protected Hashtable<String, String> config;

  /**
   * Construct a new Config object.
   */
  public Config() {
    config = new Hashtable<String, String>();
    load();
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
   * Load the default configuration.
   */
  private void load() {
    // paths
    config.put("path", System.getProperty("user.home") + "/zodiac");
    // directories
    config.put("conf_dir", "/tmp/zodiac"); //config.put("conf_dir", "/etc/zodiac");
    config.put("log_dir", "/tmp/zodiac"); //config.put("log_dir", "/var/log/zodiac");
    config.put("temp_dir", "/tmp/zodiac");
    // files
    config.put("conf_file", get("conf_dir") + "/server.conf");
    // ports
    config.put("web_port", "7767");
    config.put("api_port", "7768");
  }

  /**
   * Set a value in the configuration.
   * @param key The key to access the value
   * @param value The value to set
   */
  public void set(String key, String value) {
    config.put(key, value);
  }
  
  /**
   * Convert the configuration to a string.
   */
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("# Configuration for the Zodiac server\n");
    builder.append("# Generated on " + DateUtil.getDateString("YYYY-MM-DD HH:mm:ss") + "\n");
    for (Map.Entry<String, String> entry : config.entrySet()) {
      builder.append(entry.getKey() + " " + entry.getValue() + "\n");
    }
    return builder.toString();
  }
}
