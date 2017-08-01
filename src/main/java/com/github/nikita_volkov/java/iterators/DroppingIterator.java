package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class DroppingIterator<input> implements Iterator<input> {
  private final Iterator<input> iterator;

  public DroppingIterator(Iterator<input> iterator, long amount) {
    this.iterator = iterator;
    long counter = amount;
    while (counter > 0) {
      if (iterator.hasNext()) {
        iterator.next();
      }
      counter--;
    }
  }

  public boolean hasNext() {
    return iterator.hasNext();
  }

  public input next() {
    return iterator.next();
  }
}
