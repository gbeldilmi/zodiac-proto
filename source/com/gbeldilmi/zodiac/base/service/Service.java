package com.gbeldilmi.zodiac.base.service;

public interface Service {
  public String getDescription();
  public String getName();
  public Status getStatus();
  public boolean isRunning();
  public void start();
  public void stop();
}


