package com.github.nikita_volkov.java.iterators;

import java.util.*;

/**
 * This implementation is optimized for the standard use of Iterators, i.e.,
 * when the @hasNext@ method is called once before @next@ always.
 */
public final class UniquifyingIterator<a> implements Iterator<a> {

  private final Iterator<a> baseIterator;
  private final Set<a> seenSet;
  private a next;

  public UniquifyingIterator(Iterator<a> baseIterator) {
    this.baseIterator = baseIterator;
    this.seenSet = new HashSet<>();
  }

  @Override
  public boolean hasNext() {
    do {
      if (baseIterator.hasNext()) {
        next = baseIterator.next();
      } else {
        next = null;
        return false;
      }
    } while (seenSet.contains(next));
    seenSet.add(next);
    return true;
  }

  @Override
  public a next() {
    return next;
  }

}
