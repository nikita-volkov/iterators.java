package com.github.nikita_volkov.java.iterators;

import java.util.*;
import java.util.function.*;

/**
 * A wrapper API for any Iterator, which extends it with helper methods.
 * <p>
 * This class is extendable, so that you could enrich it further in your inheriting APIs.
 */
public class RichIterator<element> implements Iterator<element> {

  public static <element> RichIterator<element> empty() {
    return new RichIterator<>(new EmptyIterator<>());
  }

  public static <element> RichIterator<element> singleton(element element) {
    return new RichIterator<>(new SingletonIterator<>(element));
  }

  public static <element> RichIterator<element> array(element... array) {
    return new RichIterator<>(new ArrayIterator<>(array));
  }

  public static <element> RichIterator<element> join(Iterator<Iterator<element>> iteratorIterator) {
    return new RichIterator<>(new JoiningIterator<>(iteratorIterator));
  }

  protected final Iterator<element> originalIterator;

  public RichIterator(Iterator<element> originalIterator) {
    this.originalIterator = originalIterator;
  }

  public final boolean hasNext() {
    return originalIterator.hasNext();
  }

  public final element next() {
    return originalIterator.next();
  }

  public final RichIterator<element> take(long amount) {
    return new RichIterator<>(new TakingIterator<>(originalIterator, amount));
  }

  public final RichIterator<element> drop(long amount) {
    return new RichIterator<>(new DroppingIterator<>(originalIterator, amount));
  }

  public final <newElement> RichIterator<newElement> map(Function<element, newElement> fn) {
    return new RichIterator<>(new MappingIterator<>(originalIterator, fn));
  }

  public final <newElement> RichIterator<newElement> flatMap(Function<element, Iterator<newElement>> fn) {
    return new RichIterator<>(new FlatmappingIterator<>(originalIterator, fn));
  }

  public final RichIterator<element> filter(Predicate<element> predicate) {
    return new RichIterator<>(new FilteringIterator<>(originalIterator, predicate));
  }

  public final RichIterator<element> unique() {
    return new RichIterator<>(new UniqueIterator<>(originalIterator));
  }

  public final RichIterator<element> append(Iterator<element> iterator) {
    return new RichIterator<>(new AppendingIterator<>(originalIterator, iterator));
  }

  public final RichIterator<element> prepend(Iterator<element> iterator) {
    return new RichIterator<>(new AppendingIterator<>(iterator, originalIterator));
  }

  public final RichIterator<element> interleave(Iterator<element> iterator, Comparator<element> comparator) {
    return new RichIterator<>(new InterleavingIterator<>(originalIterator, iterator, comparator));
  }

}
