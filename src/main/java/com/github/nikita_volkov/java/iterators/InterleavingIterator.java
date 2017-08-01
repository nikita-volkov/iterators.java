package com.github.nikita_volkov.java.iterators;

import java.util.*;

public final class InterleavingIterator<element> implements Iterator<element> {

  private final PrependableIterator<element> leftIterator;
  private final PrependableIterator<element> rightIterator;
  private final Comparator<element> comparator;

  public InterleavingIterator(Iterator<element> leftIterator, Iterator<element> rightIterator, Comparator<element> comparator) {
    this.leftIterator = new PrependableIterator<>(leftIterator);
    this.rightIterator = new PrependableIterator<>(rightIterator);
    this.comparator = comparator;
  }

  public boolean hasNext() {
    return leftIterator.hasNext() || rightIterator.hasNext();
  }

  public element next() {
    if (leftIterator.hasNext()) {
      element leftElement = leftIterator.next();
      if (rightIterator.hasNext()) {
        element rightElement = rightIterator.next();
        if (comparator.compare(leftElement, rightElement) <= 0) {
          leftIterator.prepend(leftElement);
          return rightElement;
        } else {
          rightIterator.prepend(rightElement);
          return leftElement;
        }
      } else {
        return leftElement;
      }
    } else if (rightIterator.hasNext()) {
      return rightIterator.next();
    } else {
      throw new NoSuchElementException();
    }
  }

}
