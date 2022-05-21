package com.modernjava.chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements 
  Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

  @Override
  public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
    return (acc, candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate);
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
  }

  @Override
  public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
    return (map1, map2) -> {
      map1.get(true).addAll(map2.get(true));
      map2.get(false).addAll(map2.get(false));
      return map1;
    };
  }

  @Override
  public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
    return Function.identity();
  }

  @Override
  public Supplier<Map<Boolean, List<Integer>>> supplier() {
    return () -> {
      Map<Boolean, List<Integer>> s = new HashMap<>();
      s.put(true, new ArrayList<>());
      s.put(false, new ArrayList<>());
      return s;
    };
  }
    
  public static boolean isPrime(List<Integer> primes, int candidate) {
    int candidateRoot = (int)Math.sqrt((double)candidate);
    return primes.stream().takeWhile(i -> i <= candidateRoot)
      .noneMatch(i -> candidate % i == 0);
  }
}
