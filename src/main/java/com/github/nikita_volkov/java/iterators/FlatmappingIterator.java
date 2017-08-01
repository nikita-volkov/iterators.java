package com.github.nikita_volkov.java.iterators;

import java.util.*;
import java.util.function.Function;

public final class FlatmappingIterator<a, b> implements Iterator<b> {

  private final Iterator<a> initialIterator;
  private final Function<a, Iterator<b>> projection;

  private Iterator<b> nextIterator;
  private b next;

  public FlatmappingIterator(Iterator<a> initialIterator, Function<a, Iterator<b>> projection) {
    this.initialIterator = initialIterator;
    this.projection = projection;
    advance();
  }

  private void advance() {
    while ((nextIterator == null || !nextIterator.hasNext()) && initialIterator.hasNext()) {
      nextIterator = projection.apply(initialIterator.next());
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

  public b next() {
    if (next == null) throw new NoSuchElementException();
    b result = next;
    advance();
    return result;
  }

}
