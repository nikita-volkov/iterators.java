package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;
import java.util.function.Predicate;

public final class FilterIterator<a> implements Iterator<a> {

  private final Iterator<a> initialIterator;
  private final Predicate<a> predicate;

  private a next;

  public FilterIterator(Iterator<a> initialIterator, Predicate<a> predicate) {
    this.initialIterator = initialIterator;
    this.predicate = predicate;
    preiterate();
  }

  private void preiterate() {
    if (initialIterator.hasNext()) {
      next = initialIterator.next();
      if (!predicate.test(next)) {
        preiterate();
        return;
      }
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
