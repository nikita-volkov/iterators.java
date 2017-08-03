package com.github.nikita_volkov.java.iterators;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.*;

public class RichTest extends TestCase {

  public void testJoiningIterator() {
    assertListEquals(Arrays.asList(1, 2, 3),
      RichIterator.join(RichIterator.array(RichIterator.singleton(1), RichIterator.array(2, 3), RichIterator.empty()))
    );
  }

  public void testAppendingIterator() {
    assertListEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8),
      RichIterator.array(1, 2, 3, 4).append(RichIterator.array(5, 6, 7, 8))
    );
  }

  public void testMappingIterator() {
    assertListEquals(Arrays.asList(2, 4, 6, 8),
      RichIterator.array(1, 2, 3, 4).map(n -> n * 2)
    );
  }

  public void testFilter() {
    assertListEquals(Arrays.asList(2, 4),
      RichIterator.array(1, 2, 3, 4).filter(n -> n % 2 == 0)
    );
  }

  public void testUnique() {
    assertListEquals(Arrays.asList(1, 4, 2, 3),
      RichIterator.array(1, 4, 2, 2, 3, 4).unique()
    );
  }

  public void testInterleave() {
    assertListEquals(Arrays.asList(1, 1, 2, 3, 4, 7, 8, 8, 9, 12, 13, 16, 17, 19),
      RichIterator.array(1, 2, 7, 8, 12, 17)
        .interleave(RichIterator.array(1, 3, 4, 8, 9, 13, 16, 19), (left, right) -> left < right ? 1 : left == right ? 0 : -1)
    );
  }

  private <element> void assertListEquals(List<element> expected, Iterator<element> iterator) {
    Assert.assertEquals(expected, Reducers.arrayList(iterator));
  }

  private void assertSumEquals(int expected, Iterator<Integer> iterator) {
    Assert.assertEquals(expected, Reducers.sum(iterator));
  }

}