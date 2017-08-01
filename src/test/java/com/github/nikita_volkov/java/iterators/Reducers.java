package com.github.nikita_volkov.java.iterators;

import java.util.*;

final class Reducers {

  static int sum(Iterator<Integer> iterator) {
    int sum = 0;
    while (iterator.hasNext()) {
      sum += iterator.next();
    }
    return sum;
  }

  static <element> ArrayList<element> arrayList(Iterator<element> iterator) {
    ArrayList<element> arrayList = new ArrayList<>();
    while (iterator.hasNext()) {
      arrayList.add(iterator.next());
    }
    return arrayList;
  }

}
