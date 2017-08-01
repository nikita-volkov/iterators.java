package com.github.nikita_volkov.java.iterators;

import java.util.*;

public final class TakingIterator<input> implements Iterator<input> {
  private final Iterator<input> iterator;
  private long counter;

  public TakingIterator(Iterator<input> iterator, long amount) {
    this.iterator = iterator;
    counter = amount;
  }

  public boolean hasNext() {
    return iterator.hasNext() && counter > 0;
  }

  public input next() {
    if (counter > 0) {
      counter--;
      return iterator.next();
    } else {
      throw new NoSuchElementException();
    }
  }
}
