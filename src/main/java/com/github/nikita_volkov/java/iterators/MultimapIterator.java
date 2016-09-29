package com.github.nikita_volkov.java.iterators;

import java.util.*;

public final class MultimapIterator<key, value> implements Iterator<MultimapIterator.Entry> {

  private final Iterator<Map.Entry<key, Iterable<value>>> iterator;
  private key currentKey;
  private Iterator<value> currentDeeperIterator;

  public MultimapIterator(Map<key, Iterable<value>> map) {
    iterator = map.entrySet().iterator();
    fetchNextEntry();
  }

  private void fetchNextEntry() {
    if (iterator.hasNext()) {
      Map.Entry<key, Iterable<value>> entry = iterator.next();
      currentDeeperIterator = entry.getValue().iterator();
      if (!currentDeeperIterator.hasNext()) {
        fetchNextEntry();
        return;
      }
      currentKey = entry.getKey();
    }
  }

  @Override
  public boolean hasNext() {
    return currentDeeperIterator.hasNext();
  }

  @Override
  public Entry next() {
    key key = currentKey;
    value value = currentDeeperIterator.next();
    fetchNextEntry();
    return new Entry(key, value);
  }

  public final class Entry {
    public final key key;
    public final value value;

    private Entry(key key, value value) {
      this.key = key;
      this.value = value;
    }
  }

}
