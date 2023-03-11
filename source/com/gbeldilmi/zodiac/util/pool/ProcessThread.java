package com.gbeldilmi.zodiac.util.pool;

import java.util.Queue;

import com.gbeldilmi.zodiac.Zodiac;

/**
 * This class is used to execute a process on each item of a queue in a separate thread.
 * @author gbeldilmi
 * @param <E> The type of the data to process
 */
public class ProcessThread<E> extends Thread {
  /**
   * The queue of data objects waiting to be processed.
   */
  private Queue<E> queue;

  /**
   * The process to execute on each item of the queue.
   */
  private Process<E> process;

  /**
   * The status of the execution of the thread.
   */
  private boolean running;

  /**
   * Construct a new ProcessThread object.
   * @param queue The queue of data objects waiting to be processed
   * @param process The process to execute on each item of the queue
   */
  public ProcessThread(Queue<E> queue, Process<E> process) {
    this.queue = queue;
    this.process = process;
  }

  /**
   * Run the thread.
   */
  public void run() {
    E item;
    this.running = true;
    while (this.running) {
      // Wait until an item is available in the queue
      while ((item = this.queue.poll()) == null) {
        try {
          synchronized (this.queue) {
            this.queue.wait();
          }
        } catch (InterruptedException e) {
          Zodiac.log.writeLog("Error while waiting for item in pool: ", e);
        }
      }
      // Process the item
      try {
        this.process.process(item);
      } catch (Exception e) {
        Zodiac.log.writeLog("Error while processing in pool: ", e);
      }
    }
  }

  /**
   * Shutdown the thread.
   */
  void shutdown() {
    this.running = false;
  }
}
