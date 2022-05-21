package com.modernjava.chapter7;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
  
  public static long iterativeSum(long n) {
    long result = 0;
    for (long i = 0; i <= n; i++) {
      result += i;
    }
    return result;
  }

  public static long sequentialSum(long n) {
    return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).orElse(0L);
  }

  public static long parallelSum(long n) {
    return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).orElse(0L);
  }

  public static long rangedSum(long n) {
    return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
  }

  public static long parallelRangedSum(long n) {
    return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
  }

  public static long sideEffectSum(long n) {
    LongAccumulator acc = new LongAccumulator(Long::sum, 0L);
    LongStream.rangeClosed(1, n).forEach(acc::accumulate);
    return acc.get();
    }

  public static long parallelSideEffectSum(long n) {
    LongAccumulator acc = new LongAccumulator(Long::sum, 0L);
    LongStream.rangeClosed(1, n).parallel().forEach(acc::accumulate);
    return acc.get();
  }

  public static long badSideEffectSum(long n) {
    Accumulator acc = new Accumulator();
    LongStream.rangeClosed(1, n).forEach(acc::add);
    return acc.total;
    }

  public static long badParallelSideEffectSum(long n) {
    Accumulator acc = new Accumulator();
    LongStream.rangeClosed(1, n).parallel().forEach(acc::add);
    return acc.total;
  }
}
