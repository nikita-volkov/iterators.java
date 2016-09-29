package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class AppendingIterator<a> implements Iterator<a> {

  private final Iterator<a> iterator1;
  private final Iterator<a> iterator2;

  private boolean hasNext;
  private a next;

  public AppendingIterator(Iterator<a> iterator1, Iterator<a> iterator2) {
    this.iterator1 = iterator1;
    this.iterator2 = iterator2;
    this.hasNext = true;
    preiterate();
  }

  private void preiterate() {
    if (iterator1.hasNext()) {
      next = iterator1.next();
    } else if (iterator2.hasNext()) {
      next = iterator2.next();
    } else {
      hasNext = false;
      next = null;
    }
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public a next() {
    a result = next;
    preiterate();
    return result;
  }
}
