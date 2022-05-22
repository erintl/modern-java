package com.modernjava.chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

  private final String string;
  private int currentPos = 0;

  public WordCounterSpliterator(String string) {
    this.string = string;
  }

  @Override
  public int characteristics() {
    return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
  }

  @Override
  public long estimateSize() {
    return string.length() - (long)currentPos;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Character> action) {
    action.accept(string.charAt(currentPos++));
    return currentPos < string.length();
  }

  @Override
  public Spliterator<Character> trySplit() {
    int currentSize = string.length() - currentPos;
    if (currentPos < 10) {
      return null;
    }
    for (int splitPos = currentSize / 2 + currentPos; splitPos < string.length(); splitPos++) {
      if (Character.isWhitespace(string.charAt(splitPos))) {
        Spliterator<Character> spliterator =
          new WordCounterSpliterator(string.substring(currentPos, splitPos));
        currentPos = splitPos;
        return spliterator;
      }
    }
    return null;
  }
  
}