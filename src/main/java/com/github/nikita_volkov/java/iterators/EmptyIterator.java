package com.github.nikita_volkov.java.iterators;

import java.util.*;

public final class EmptyIterator<a> implements Iterator<a> {
  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public a next() {
    throw new NoSuchElementException();
  }
}
