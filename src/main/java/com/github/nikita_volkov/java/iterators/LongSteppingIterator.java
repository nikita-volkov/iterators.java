package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class LongSteppingIterator implements Iterator<Long> {

  private final long step;
  private final long max;
  private long state;

  public LongSteppingIterator(long start, long step, long max) {
    this.state = start;
    this.step = step;
    this.max = max;
  }

  public LongSteppingIterator(long step, long max) {
    this(0, step, max);
  }

  public boolean hasNext() {
    return state <= max;
  }

  public Long next() {
    long result = state;
    state += step;
    return result;
  }

}
