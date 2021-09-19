package com.javalab1;

public class Pair<K, V> {
  private final K mFirst;
  private final V mSecond;

  public static <K, V> Pair<K, V> createPair(K first, V second) {
    return new Pair<>(first, second);
  }

  public Pair(K first, V second) {
    mFirst = first;
    mSecond = second;
  }

  public K getFirst() {
    return mFirst;
  }

  public V getSecond() {
    return mSecond;
  }
}
