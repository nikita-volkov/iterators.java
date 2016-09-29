package com.github.nikita_volkov.java.iterators;

import java.util.*;

public final class UniqueIterator<a> implements Iterator<a> {

  private final Iterator<a> baseIterator;
  private final Set<a> seenSet;

  private boolean hasNext;
  private a next;

  public UniqueIterator(Iterator<a> baseIterator) {
    this.baseIterator = baseIterator;
    this.seenSet = new HashSet<a>();
    this.hasNext = true;
    preiterate();
  }

  private void preiterate() {
    if (baseIterator.hasNext()) {
      next = baseIterator.next();
      if (seenSet.contains(next)) {
        preiterate();
      } else {
        seenSet.add(next);
      }
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
