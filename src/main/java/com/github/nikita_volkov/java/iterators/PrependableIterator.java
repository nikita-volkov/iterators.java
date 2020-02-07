package com.github.nikita_volkov.java.iterators;

import java.util.*;

public class PrependableIterator<element> implements Iterator<element> {

  private final Iterator<element> originalIterator;
  private final Deque<element> priorQueue = new LinkedList<>();

  public PrependableIterator(Iterator<element> originalIterator) {
    this.originalIterator = originalIterator;
  }

  public boolean hasNext() {
    return !priorQueue.isEmpty() || originalIterator.hasNext();
  }

  public element next() {
    element bufferedElement = priorQueue.pollFirst();
    if (bufferedElement == null) {
      return originalIterator.next();
    } else {
      return bufferedElement;
    }
  }

  public void prepend(element element) {
    priorQueue.addFirst(element);
  }

}
