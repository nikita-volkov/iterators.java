package com.github.nikita_volkov.java.iterators;

import java.util.*;

public final class InterleavingIterator<element> implements Iterator<element> {

  private final Iterator<element> leftIterator;
  private final Iterator<element> rightIterator;
  private final Comparator<element> comparator;
  private final LinkedList<element> leftBuffer = new LinkedList<>();
  private final LinkedList<element> rightBuffer = new LinkedList<>();

  public InterleavingIterator(Iterator<element> leftIterator, Iterator<element> rightIterator, Comparator<element> comparator) {
    this.leftIterator = leftIterator;
    this.rightIterator = rightIterator;
    this.comparator = comparator;
  }

  @Override
  public boolean hasNext() {
    return !leftBuffer.isEmpty() || !rightBuffer.isEmpty() || leftIterator.hasNext() || rightIterator.hasNext();
  }

  @Override
  public element next() {
    element leftBufferedElement = leftBuffer.poll();
    element rightBufferedElement = rightBuffer.poll();
    if (leftBufferedElement != null) {
      if (rightBufferedElement != null) {
        if (comparator.compare(leftBufferedElement, rightBufferedElement) <= 0) {
          leftBuffer.addFirst(leftBufferedElement);
          return rightBufferedElement;
        } else {
          rightBuffer.addFirst(rightBufferedElement);
          return leftBufferedElement;
        }
      } else if (rightIterator.hasNext()) {
        element nextRightElement = rightIterator.next();
        if (comparator.compare(leftBufferedElement, nextRightElement) <= 0) {
          leftBuffer.addFirst(leftBufferedElement);
          return nextRightElement;
        } else {
          rightBuffer.addLast(nextRightElement);
          return leftBufferedElement;
        }
      } else {
        return leftBufferedElement;
      }
    } else if (rightBufferedElement != null) {
      if (leftIterator.hasNext()) {
        element nextLeftElement = leftIterator.next();
        if (comparator.compare(nextLeftElement, rightBufferedElement) <= 0) {
          leftBuffer.addLast(nextLeftElement);
          return rightBufferedElement;
        } else {
          rightBuffer.addFirst(rightBufferedElement);
          return nextLeftElement;
        }
      } else {
        return rightBufferedElement;
      }
    } else {
      if (leftIterator.hasNext()) {
        element nextLeftElement = leftIterator.next();
        if (rightIterator.hasNext()) {
          element nextRightElement = rightIterator.next();
          if (comparator.compare(nextLeftElement, nextRightElement) <= 0) {
            leftBuffer.addLast(nextLeftElement);
            return nextRightElement;
          } else {
            rightBuffer.addLast(nextRightElement);
            return nextLeftElement;
          }
        } else {
          return nextLeftElement;
        }
      } else if (rightIterator.hasNext()) {
        return rightIterator.next();
      } else {
        throw new NoSuchElementException();
      }
    }
  }

}
