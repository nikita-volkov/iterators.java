package com.github.nikita_volkov.java.iterators;

import java.util.Iterator;

public final class ArrayIterator<a> implements Iterator<a> {

  private a[] array;
  private int index;

  public ArrayIterator(a... array) {
    this.array = array;
    index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < array.length;
  }

  @Override
  public a next() {
    return array[index++];
  }

}
