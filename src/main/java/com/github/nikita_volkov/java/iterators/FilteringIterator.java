package com.github.nikita_volkov.java.iterators;

import java.util.*;
import java.util.function.Predicate;

public class FilteringIterator<a> implements Iterator<a> {

  private final Iterator<a> initialIterator;
  private final Predicate<a> predicate;

  private a next;

  public FilteringIterator(Iterator<a> initialIterator, Predicate<a> predicate) {
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

  public boolean hasNext() {
    return next != null;
  }

  public a next() {
    if (next == null) throw new NoSuchElementException();
    a result = next;
    preiterate();
    return result;
  }

}
