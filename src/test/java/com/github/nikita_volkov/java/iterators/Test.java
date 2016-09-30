package com.github.nikita_volkov.java.iterators;

import junit.framework.TestCase;

import java.util.*;

public class Test extends TestCase {

  public void testAppendIterator() {
    List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
    List<Integer> list2 = Arrays.asList(5, 6, 7, 8);
    Iterator<Integer> iterator =
      new AppendingIterator<>(list1.iterator(), list2.iterator());

    assertEquals(36, sum(iterator));
  }

  public void testProjectIterator() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    Iterator<Integer> iterator =
      new MappingIterator<>(
        list.iterator(),
        n -> n * 2
      );

    assertEquals(20, sum(iterator));
  }

  public void testFilterIterator() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    Iterator<Integer> iterator =
      new FilteringIterator<>(
        list.iterator(),
        n -> n % 2 == 0
      );

    assertEquals(6, sum(iterator));
  }

  public void testUniqueIterator() {
    List<Integer> list = Arrays.asList(1, 2, 2, 3, 4);
    Iterator<Integer> iterator =
      new UniquifyingIterator<>(list.iterator());

    assertEquals(10, sum(iterator));
  }

  private static int sum(Iterator<Integer> iterator) {
    int sum = 0;
    while (iterator.hasNext()) {
      sum += iterator.next();
    }
    return sum;
  }

}