package com.modernjava.chapter6;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class CollectorHarness {
  
  public static void main(String[] args) {
    System.out.println("Standard partitioning done in : " + execute(Partitioning::partitionPrimes) + " ms");
    System.out.println("Custom partitioning done in : " + execute(Partitioning::partitionPrimes2) + " ms");
  }

  private static long execute(IntConsumer primePartitioner) {
    long fastest = Long.MAX_VALUE;
    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      primePartitioner.accept(1_000_000);
      long duration = ((System.nanoTime() - start) / 1_000_000);
      if (duration < fastest) {
        fastest = duration;
      }
    }
    System.out.println("done in " + fastest);
    return fastest;
  }
}
