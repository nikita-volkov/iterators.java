package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class SingletonIterator<a> implements Iterator<a> {

  private a a;
  private boolean hasNext;

  public SingletonIterator(a a) {
    this.a = a;
    hasNext = true;
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public a next() {
    if (hasNext) {
      hasNext = false;
      return a;
    } else {
      return null;
    }
  }

}
