package com.modernjava.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class ParallelStreamHarness {
  
  public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

  public static void main(String[] args) {
    System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
    System.out.println("\n");
    System.out.println("Iterative Sum done in: " +
      measurePerformance(ParallelStreams::iterativeSum, 10_000_000L) + " ns");
    System.out.println("Sequential Sum done in: " +
      measurePerformance(ParallelStreams::sequentialSum, 10_000_000L) + " ns");
    System.out.println("Parallel Sum done in: " +
      measurePerformance(ParallelStreams::parallelSum, 10_000_000L) + " ns");
    System.out.println("Ranged Sum done in: " +
      measurePerformance(ParallelStreams::rangedSum, 10_000_000L) + " ns");
    System.out.println("Parallel Ranged Sum done in: " +
      measurePerformance(ParallelStreams::parallelRangedSum, 10_000_000L) + " ns");
    System.out.println("Side Effect Sum done in: " +
      measurePerformance(ParallelStreams::sideEffectSum, 10_000_000L) + " ns");
    System.out.println("Parallel Side Effect Sum done in: " +
      measurePerformance(ParallelStreams::parallelSideEffectSum, 10_000_000L) + " ns");
    System.out.println("Bad Side Effect Sum done in: " +
      measurePerformance(ParallelStreams::badSideEffectSum, 10_000_000L) + " ns");
    System.out.println("Bad Parallel Side Effect Sum done in: " +
      measurePerformance(ParallelStreams::badParallelSideEffectSum, 10_000_000L) + " ns");
    System.out.println("Fork Join Sum done in: " +
      measurePerformance(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " ns");
  }

  public static <T, R> long measurePerformance(Function<T, R> f, T input) {
    long fastest = Long.MAX_VALUE;

    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      R result = f.apply(input);
      long duration = (System.nanoTime() - start);
      System.out.println("Result: " + result);
      if (duration < fastest) {
        fastest = duration;
      }
    }
    return fastest;
  }
}
