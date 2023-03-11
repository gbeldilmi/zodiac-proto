package com.gbeldilmi.zodiac.util.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.gbeldilmi.zodiac.Zodiac;

/**
 * This class is used to manage a pool of threads that execute a process on each item of a queue.
 * @author gbeldilmi
 * @param <E> The type of data objects to process
 */
public class ProcessPool<E> {
  /**
   * The waiting queue of data objects to be processed.
   */
  private Queue<E> queue;

  /**
   * The list of threads that execute the process.
   */
  private List<ProcessThread<E>> threads;

  /**
   * Construct a new ProcessPool object.
   * @param size The size of the pool (number of threads to create)
   * @param process The process to execute
   */
  public ProcessPool(int size, Process<E> process) {
    this.queue = new ConcurrentLinkedQueue<E>();
    this.threads = new ArrayList<ProcessThread<E>>();
    // Create threads and add them to the list
    while (size-- > 0) {
      this.threads.add(new ProcessThread<E>(this.queue, process));
    }
  }

  /**
   * Add an item to the queue.
   * @param item The item to add
   */
  public void addToQueue(E item) {
    // Wait until the item is added to the queue
    while (!this.queue.offer(item)) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        Zodiac.log.writeLog("Error while adding item to pool: ", e);
      }
    }
    // Notify the threads that an item is available
    synchronized (this.queue) {
      this.queue.notify();
    }
  }

  /**
   * Shutdown the pool.
   */
  public void shutdown() {
    // Shutdown each thread
    for (ProcessThread<E> thread : this.threads) {
      thread.shutdown();
    }
  }

  /**
   * Start the pool.
   */
  public void start() {
    // Start each thread
    for (ProcessThread<E> thread : this.threads) {
      thread.start();
    }
  }
}
