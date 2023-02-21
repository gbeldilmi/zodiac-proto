package com.gbeldilmi.zodiac.base.service;

/**
 * This enumeration is used to define the status of the execution of a service.
 * @author gbeldilmi
 */
public enum Status {
  /**
   * The service is ready to be started.
   */
  READY,

  /**
   * The service is running starting hook.
   */
  STARTING,

  /**
   * The service is running.
   */
  RUNNING,
  
  /**
   * The service is running stopping hook.
   */
  STOPPING,
  
  /**
   * The service is stopped.
   */
  STOPPED,

  /**
   * The service is running exiting hook.
   */
  EXITING,
  
  /**
   * The service has exited.
   */
  EXITED,

  /**
   * An error occurred during the service execution.
   */
  ERROR;
}
