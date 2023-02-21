package com.gbeldilmi.zodiac.base.service;

import java.util.ArrayList;

/**
 * This singleton class is used to manage the services hosted by the Zodiac server.
 * @see com.gbeldilmi.zodiac.base.service.Service
 * @see com.gbeldilmi.zodiac.base.service.ServiceEntity
 * @author gbeldilmi
 */
public final class ServiceManager {
  /**
   * The instance of the ServiceManager class.
   */
  private static ServiceManager instance;

  /**
   * The list of service entities.
   */
  private ArrayList<ServiceEntity> servicesEntities;

  /**
   * Innitialize the ServiceManager class.
   */
  static {
    instance = new ServiceManager();
  }

  /**
   * Construct a new ServiceManager object.
   */
  private ServiceManager() {
    servicesEntities = new ArrayList<ServiceEntity>();
  }

  /**
   * Add a service to the list of services.
   * @param service The service to add
   * @return The id of the service
   * @throws NullPointerException If the service is null
   */
  public int addService(Service service) throws NullPointerException {
    int id = servicesEntities.size();
    if (servicesEntities.add(new ServiceEntity(service))) {
      return id;
    }
    return -1;
  }

  /**
   * Get the instance of the ServiceManager.
   * @return The instance of the ServiceManager.
   */
  public static ServiceManager getInstance() {
    return instance;
  }

  /**
   * Get the service entity at the specified index.
   * @param id The index of the service entity
   * @return The service entity
   * @throws IndexOutOfBoundsException If the id is out of bounds of the list of services
   */
  private ServiceEntity getServiceEntity(int id) throws IndexOutOfBoundsException {
    if (id >= 0 && id < servicesEntities.size()) {
      return servicesEntities.get(id);
    }
    throw new IndexOutOfBoundsException();
  }

  /**
   * Get the number of services.
   * @return The number of services
   */
  public int getNumberOfServices() {
    return servicesEntities.size();
  }

  /**
   * Get the status of a service.
   * @param id The id of the service
   * @return The status of the service
   * @throws IndexOutOfBoundsException If the id is out of bounds of the list of services
   */
  public Status getServiceStatus(int id) throws IndexOutOfBoundsException {
    return getServiceEntity(id).getStatus();
  }

  /**
   * Remove a service from the list of services. The service will be stopped if it is running.
   * @param id The id of the service to remove
   * @return The service that was removed
   * @throws IndexOutOfBoundsException If the id is out of bounds of the list of services
   */
  public Service removeService(int id) throws IndexOutOfBoundsException {
    ServiceEntity se = getServiceEntity(id);
    if (se.getStatus() == Status.RUNNING) {
      se.shutdown();
    }
    servicesEntities.remove(se);
    return se.getService();
  }

  /**
   * Restart a service. The service will be stopped if it is running.
   * @param id The id of the service to restart
   * @throws IndexOutOfBoundsException If the id is out of bounds of the list of services
   */
  public void restartService(int id) throws IndexOutOfBoundsException {
    ServiceEntity se = getServiceEntity(id);
    if (se.getStatus() == Status.RUNNING) {
      se.shutdown();
    }
    se.start();
  }

  /**
   * Start a service. The service will only be started if it is ready to be executed.
   * @param id The id of the service to start
   * @throws IndexOutOfBoundsException If the id is out of bounds of the list of services
   */
  public void startService(int id) throws IndexOutOfBoundsException {
    ServiceEntity se = getServiceEntity(id);
    if (se.isReady()) {
      se.start();
    }
  }

  /**
   * Start all services. Only services that are ready to be executed will be started.
   */
  public void startAll() {
    for (ServiceEntity se : servicesEntities) {
      if (se.isReady()) {
        se.start();
      }
    }
  }

  /**
   * Stop all active services.
   */
  public void stopAll() {
    for (ServiceEntity se : servicesEntities) {
      if (se.isActive()) {
        se.shutdown();
      }
    }
  }
}
