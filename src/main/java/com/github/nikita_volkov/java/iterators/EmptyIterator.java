package com.github.nikita_volkov.java.iterators;

import java.util.*;

public final class EmptyIterator<element> implements Iterator<element> {
  public boolean hasNext() {
    return false;
  }

  public element next() {
    throw new NoSuchElementException();
  }
}
