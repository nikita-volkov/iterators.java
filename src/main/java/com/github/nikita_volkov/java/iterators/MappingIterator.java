package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;
import java.util.function.Function;

public class MappingIterator<a, b> implements Iterator<b> {

  private final Iterator<a> initialIterator;
  private final Function<a, b> projection;

  public MappingIterator(Iterator<a> initialIterator, Function<a, b> projection) {
    this.initialIterator = initialIterator;
    this.projection = projection;
  }

  public boolean hasNext() {
    return initialIterator.hasNext();
  }

  public b next() {
    return projection.apply(initialIterator.next());
  }
}
