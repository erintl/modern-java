package com.modernjava.chapter7;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
  
  public static final long THRESHOLD = 10_000L;
  private final long[] numbers;
  private final int start;
  private final int end;

  public ForkJoinSumCalculator(long[] numbers) {
    this(numbers, 0, numbers.length);
  }

  private ForkJoinSumCalculator(long[] numbers, int start, int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }
  
  public static long forkJoinSum(long n) {
    long[] numbers = LongStream.rangeClosed(0, n).toArray();
    ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
    return ParallelStreamHarness.FORK_JOIN_POOL.invoke(task);
  }

  @Override
  protected Long compute() {
    int length = end - start;
    if (length <= THRESHOLD) {
      return computeSequentially();
    }
    ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
    leftTask.fork();
    ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
    Long rightResult = rightTask.compute();
    Long leftResult = leftTask.join();
    return rightResult + leftResult;
  }

  private long computeSequentially() {
    long sum = 0;
    for (int i = start; i < end ; i++) {
      sum += numbers[i];
    }
    return sum;
  }
}
