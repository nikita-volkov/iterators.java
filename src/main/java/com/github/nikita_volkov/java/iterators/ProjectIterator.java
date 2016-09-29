package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;
import java.util.function.Function;

public final class ProjectIterator<a, b> implements Iterator<b> {

  private final Iterator<a> initialIterator;
  private final Function<a, b> projection;

  public ProjectIterator(Iterator<a> initialIterator, Function<a, b> projection) {
    this.initialIterator = initialIterator;
    this.projection = projection;
  }

  @Override
  public boolean hasNext() {
    return initialIterator.hasNext();
  }

  @Override
  public b next() {
    return projection.apply(initialIterator.next());
  }
}
