package com.modernjava.chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import com.modernjava.chapter4.Dish;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>>{

  public static void main(String[] args) {
    List<Dish> dishes1 = Dish.menu.stream().collect(new ToListCollector<>());
    List<Dish> dishes2 = Dish.menu.stream().collect(ArrayList::new, List::add, List::addAll);
    
    System.out.println("Custom to list collector result: " + dishes1);
  
  }

  @Override
  public Supplier<List<T>> supplier() {
    return ArrayList::new;
  }

  @Override
  public BiConsumer<List<T>, T> accumulator() {
    return List::add;
  }

  @Override
  public Function<List<T>, List<T>> finisher() {
    return Function.identity();
  }

  @Override
  public BinaryOperator<List<T>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,
      Characteristics.CONCURRENT));
  }
}
