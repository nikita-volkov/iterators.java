package com.github.nikita_volkov.java.iterators;

import java.util.*;

public class JoiningIterator<a> implements Iterator<a> {

  private final Iterator<Iterator<a>> iteratorIterator;

  private Iterator<a> nextIterator;
  private a next;

  public JoiningIterator(Iterator<Iterator<a>> iteratorIterator) {
    this.iteratorIterator = iteratorIterator;
    advance();
  }

  private void advance() {
    while ((nextIterator == null || !nextIterator.hasNext()) && iteratorIterator.hasNext()) {
      nextIterator = iteratorIterator.next();
      if (!nextIterator.hasNext()) {
        nextIterator = null;
      }
    }
    if (nextIterator != null && nextIterator.hasNext()) {
      next = nextIterator.next();
    } else {
      next = null;
    }
  }

  public boolean hasNext() {
    return next != null;
  }

  public a next() {
    if (next == null) throw new NoSuchElementException();
    a result = next;
    advance();
    return result;
  }

}
