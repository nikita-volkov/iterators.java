package com.github.nikita_volkov.java.iterators;

import java.util.*;

public class MultimapIterator<key, value, valueIterable extends Iterable<value>> implements Iterator<MultimapIterator.Entry<key, value>> {

  private final Iterator<Map.Entry<key, valueIterable>> iterator;
  private key currentKey;
  private Iterator<value> currentDeeperIterator;

  public MultimapIterator(Map<key, valueIterable> map) {
    iterator = map.entrySet().iterator();
    fetchNextEntry();
  }

  private void fetchNextEntry() {
    if (iterator.hasNext()) {
      Map.Entry<key, valueIterable> entry = iterator.next();
      currentDeeperIterator = entry.getValue().iterator();
      if (!currentDeeperIterator.hasNext()) {
        fetchNextEntry();
        return;
      }
      currentKey = entry.getKey();
    }
  }

  public boolean hasNext() {
    return currentDeeperIterator.hasNext();
  }

  public Entry<key, value> next() {
    key key = currentKey;
    value value = currentDeeperIterator.next();
    fetchNextEntry();
    return new Entry<>(key, value);
  }

  public final static class Entry<key, value> {
    public final key key;
    public final value value;

    private Entry(key key, value value) {
      this.key = key;
      this.value = value;
    }
  }

}
