package com.gbeldilmi.zodiac.base.service;

import java.util.ArrayList;

public class ServiceManager {
  private ArrayList<Service> services;

  public ServiceManager() {
    services = new ArrayList<Service>();
  }

  public void addService(Service service) throws IllegalArgumentException {
    if (getService(service.getName()) != null) {
      throw new IllegalArgumentException("Service with name " + service.getName() + " already exists");
    }
    services.add(service);
  }

  public Service getService(String name) {
    for (Service service : services) {
      if (service.getName().equals(name)) {
        return service;
      }
    }
    return null;
  }

  public Status getServiceStatus(String name) throws IllegalArgumentException {
    Service service = getService(name);
    if (service == null) {
      throw new IllegalArgumentException("Service with name " + name + " does not exist");
    }
    return service.getStatus();
  }

  public Service removeService(String name) {
    Service service = getService(name);
    services.remove(service);
    if (service.isRunning()) {
      service.stop();
    }
    return service;
  }

  public void startAll() {
    for (Service service : services) {
      if (!service.isRunning()) {
        service.start();
      }
    }
  }

  public void stopAll() {
    for (Service service : services) {
      if (service.isRunning()) {
        service.stop();
      }
    }
  }
}
