package com.gbeldilmi.zodiac.util.pool;

/**
 * This interface is used to define a process to execute on a specified 
 * @author gbeldilmi
 * @param <E> The type of the data object to process
 */
public interface Process<E> {
  /**
   * Process an item.
   * @param item The item to process
   * @throws RuntimeException If an error occurs while processing the item
   */
  public void process(E item) throws RuntimeException;
}
