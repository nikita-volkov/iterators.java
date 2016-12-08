package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class IntegerSteppingIterator implements Iterator<Integer> {

  private final int step;
  private final int max;
  private int state;

  public IntegerSteppingIterator(int start, int step, int max) {
    this.state = start;
    this.step = step;
    this.max = max;
  }

  public IntegerSteppingIterator(int step, int max) {
    this(0, step, max);
  }

  @Override
  public boolean hasNext() {
    return state <= max;
  }

  @Override
  public Integer next() {
    int result = state;
    state += step;
    return result;
  }

}
