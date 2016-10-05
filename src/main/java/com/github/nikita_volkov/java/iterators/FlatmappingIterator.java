package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;
import java.util.function.Function;

public final class FlatmappingIterator<a, b> implements Iterator<b> {

  private final Iterator<a> initialIterator;
  private final Function<a, Iterator<b>> projection;

  private Iterator<b> nextIterator;
  private b next;

  public FlatmappingIterator(Iterator<a> initialIterator, Function<a, Iterator<b>> projection) {
    this.initialIterator = initialIterator;
    this.projection = projection;
    preiterate();
  }

  private void preiterate() {
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

  @Override
  public boolean hasNext() {
    return next != null;
  }

  @Override
  public b next() {
    b result = next;
    preiterate();
    return result;
  }

}
