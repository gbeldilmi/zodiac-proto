package com.gbeldilmi.zodiac.base.service;

/**
 * This interface is used to define a service able to be hosted by the Zodiac server.
 * @author gbeldilmi
 */
public interface Service extends Runnable {
  /**
   * Action to perform when the service exits. This method must free every resource used by the service.
   */
  public void exit();

  /**
   * Get the name of the service.
   * @return The name of the service
   */
  public String getServiceName();

  /**
   * The main method of the service to execute.
   */
  public void run();

  /**
   * Action to perform when the service is started. This method can be used to initialize every resource needed by the
   * service before the execution of the run() method.
   */
  public void start();

  /**
   * Action to perform when the service is stopped. This method must terminate the execution of the run() method
   * properly.
   */
  public void stop();
}


