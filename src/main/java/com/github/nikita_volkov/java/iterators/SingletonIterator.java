package com.github.nikita_volkov.java.iterators;

import java.util.*;

public class SingletonIterator<element> implements Iterator<element> {

  private element next;

  public SingletonIterator(element next) {
    this.next = next;
  }

  public boolean hasNext() {
    return next != null;
  }

  public element next() {
    if (next != null) {
      element element = next;
      next = null;
      return element;
    } else {
      throw new NoSuchElementException();
    }
  }

}
