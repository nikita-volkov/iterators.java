package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class AppendingIterator<a> implements Iterator<a> {

  private final Iterator<? extends a> leftIterator;
  private final Iterator<? extends a> rightIterator;


  public AppendingIterator(Iterator<? extends a> leftIterator, Iterator<? extends a> rightIterator) {
    this.leftIterator = leftIterator;
    this.rightIterator = rightIterator;
  }

  public boolean hasNext() {
    return leftIterator.hasNext() || rightIterator.hasNext();
  }

  public a next() {
    if (leftIterator.hasNext()) {
      return leftIterator.next();
    } else {
      return rightIterator.next();
    }
  }
}
