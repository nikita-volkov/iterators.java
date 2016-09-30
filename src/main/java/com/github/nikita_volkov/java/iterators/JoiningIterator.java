package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class JoiningIterator<a> implements Iterator<a> {

  private final Iterator<Iterator<a>> initialIterator;

  private Iterator<a> nextIterator;
  private a next;

  public JoiningIterator(Iterator<Iterator<a>> initialIterator) {
    this.initialIterator = initialIterator;
    preiterate();
  }

  private void preiterate() {
    if (nextIterator != null && nextIterator.hasNext()) {
      next = nextIterator.next();
    } else if (initialIterator.hasNext()) {
      nextIterator = initialIterator.next();
      preiterate();
    } else {
      next = null;
    }
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public a next() {
    a result = next;
    preiterate();
    return result;
  }

}
