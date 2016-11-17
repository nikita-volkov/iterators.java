package com.github.nikita_volkov.java.iterators;

import java.time.*;
import java.time.temporal.*;
import java.util.Iterator;

/**
 * Iterate the instants from the specified start
 * incrementing by the specified step value.
 * Once the current moment in time is passed,
 * sleep until the next instant.
 * <p>
 * Useful for implementing the periodic daemon jobs.
 */
public final class InfinitePeriodicInstantIterator implements Iterator<Instant> {

  private final Duration step;

  private Instant next;

  public InfinitePeriodicInstantIterator(Duration step, Instant start) {
    this.step = step;
    next = start;
  }

  /**
   * Use now as the start instant.
   */
  public InfinitePeriodicInstantIterator(Duration step) {
    this(step, Instant.now());
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Instant next() {
    Instant now = Instant.now();
    if (now.isAfter(next)) {
      Instant result = next;
      next = next.plus(step);
      return result;
    } else {
      try {
        Thread.sleep(now.until(next, ChronoUnit.MILLIS));
      } catch (InterruptedException e) {}
      next = next.plus(step);
      return Instant.now();
    }
  }

}
