package com.gbeldilmi.zodiac.base.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used to store the configuration of the server as couples of key/value and loads the configuration from
 * the configuration file defined in the default configuration.
 * @see com.gbeldilmi.zodiac.base.config.Config
 * @author gbeldilmi
 */
class FileConfig extends Config {
  /**
   * Construct a new FileConfig object. First, load the default configuration. Then, load the configuration from the
   * configuration file.
   * @see com.gbeldilmi.zodiac.base.config.Config
   */
  FileConfig() {
    super();
    load(get("conf_file"));
  }

  /**
   * Load the configuration from a file.
   * @param filePath The path to the configuration file
   */
  private void load(String filePath) {
    File file = new File(filePath);
    BufferedReader reader;
    String line, key, entries;
    int i;
    // Create the file if it does not exist
    if (!file.exists()) {
      try {
        if (!file.getParentFile().exists()) {
          file.getParentFile().mkdirs();
        }
        file.createNewFile();
      } catch (IOException e) {
        System.err.println("Could not create config file " + filePath);
        System.err.println(e.getMessage());
      }
    } else {
      try {
        reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
          line = line.trim();
          // Ignore comments
          if (line.startsWith("#")) {
            continue;
          }
          // "key entry1 entry2 entry3 ..."
          i = line.indexOf(" ");
          if (i == -1) {
            continue;
          }
          // Get the key and the entries and set them in the config
          key = line.substring(0, i);
          entries = line.substring(i + 1);
          set(key, entries);
        }
        reader.close();
      } catch (IOException e) {
        System.err.println("Could not read config file " + filePath);
        System.err.println(e.getMessage());
      }
    }
  }
}
