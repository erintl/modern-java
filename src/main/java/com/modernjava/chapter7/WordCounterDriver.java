package com.modernjava.chapter7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterDriver {

  public static final String SENTENCE =
      " Nel   mezzo del cammin  di nostra  vita "
      + "mi  ritrovai in una  selva oscura"
      + " che la  dritta via era   smarrita ";

  public static void main(String[] args) {
    System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
    System.out.println("Found " + countWords(SENTENCE) + " words");
    System.out.println("Found " + countWordsParallel(SENTENCE) + " words");
  }

  public static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;
    for (char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      } else  {
        if (lastSpace) {
          counter++;
        }
        lastSpace = false;
      }
    }
    return counter;
  }

  public static int countWords(String s) {
    Stream<Character> stream = IntStream.range(0, s.length()).mapToObj(s::charAt);
    WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
      WordCounter::accumulate, WordCounter::combine);
    return wordCounter.getCounter();
  }

  public static int countWords(Stream<Character> s) {
    WordCounter wordCounter = s.reduce(new WordCounter(0, true),
      WordCounter::accumulate, WordCounter::combine);
    return wordCounter.getCounter();
  }

  public static int countWordsParallel(String s) {
    Spliterator<Character> spliterator = new WordCounterSpliterator(s);
    Stream<Character> stream = StreamSupport.stream(spliterator, true);
    return countWords(stream);
  }
}
