package com.gbeldilmi.zodiac.base.service;

import com.gbeldilmi.zodiac.Zodiac;

/**
 * This class is used to manage the execution of a service.
 * @author gbeldilmi
 */

public class ServiceEntity extends Thread {
  /**
   * The service to execute.
   */
  private Service service;

  /**
   * The status of the execution of the service.
   */
  private Status status;

  /**
   * Construct a new ServiceEntity object.
   * @param service The service to execute
   * @throws NullPointerException If the service is null
   */
  public ServiceEntity(Service service) throws NullPointerException {
    if (service == null) {
      throw new NullPointerException("The service cannot be null.");
    }
    this.service = service;
    setStatus(Status.READY);
  }

  /**
   * Get the service.
   * @return The service
   */
  public Service getService() {
    return service;
  }

  /**
   * Get the status of the execution of the service.
   * @return The status of the service
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Check if the service is active.
   * @return True if the service is active, false otherwise
   */
  public boolean isActive() {
    return status == Status.STARTING || status == Status.RUNNING || status == Status.STOPPING;
  }

  /**
   * Check if the service is ready to be executed.
   * @return True if the service is ready to be executed, false otherwise
   */
  public boolean isReady() {
    return status == Status.READY;
  }

  /**
   * Run the service.
   */
  public void run() {
    try {
      setStatus(Status.STARTING);
      service.start();
      setStatus(Status.RUNNING);
      service.run();
      setStatus(Status.EXITING);
      service.exit();
      setStatus(Status.EXITED);
    } catch (Exception e) {
      Zodiac.log.writeLog("An error occurred while executing the service " + service.getServiceName() + " (status: " +
        getStatus() + ").", e);
      setStatus(Status.ERROR);
    }
  }

  /**
   * Set the service status.
   * @param status The status of the service
   * @see com.gbeldilmi.zodiac.base.service.Status
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * Stop the service.
   */
  public void shutdown() {
    setStatus(Status.STOPPING);
    service.stop();
    setStatus(Status.STOPPED);
    //stop();
  }
}
