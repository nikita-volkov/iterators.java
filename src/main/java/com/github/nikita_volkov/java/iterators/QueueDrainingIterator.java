package com.github.nikita_volkov.java.iterators;

import java.util.*;

public class QueueDrainingIterator<a> implements Iterator<a> {

  private final Queue<a> queue;

  public QueueDrainingIterator(Queue<a> queue) {
    this.queue = queue;
  }

  public boolean hasNext() {
    return !queue.isEmpty();
  }

  public a next() {
    return queue.remove();
  }

}
