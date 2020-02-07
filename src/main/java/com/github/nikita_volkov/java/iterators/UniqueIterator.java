package com.github.nikita_volkov.java.iterators;

import java.util.*;

/**
 * This implementation is optimized for the standard use of Iterators, i.e.,
 * when the @hasNext@ method is called once before @next@ always.
 */
public class UniqueIterator<a> implements Iterator<a> {

  private final Iterator<a> baseIterator;
  private final Set<a> seenSet;
  private a next;

  public UniqueIterator(Iterator<a> baseIterator) {
    this.baseIterator = baseIterator;
    this.seenSet = new HashSet<>();
    advance();
  }

  private void advance() {
    while (baseIterator.hasNext()) {
      next = baseIterator.next();
      if (!seenSet.contains(next)) {
        seenSet.add(next);
        return;
      }
    }
    next = null;
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
