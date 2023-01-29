package com.gbeldilmi.zodiac.base.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class FileConfig extends Config {
  FileConfig() {
    super();
    load(get("config_file"));
  }

  private void load(String filePath) {
    File file = new File(filePath);
    BufferedReader reader;
    String line, key, entries;
    int i;
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
