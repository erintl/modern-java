package com.modernjava.chapter3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.modernjava.chapter2.Apple;
import com.modernjava.chapter2.Fruit;
import com.modernjava.chapter2.Orange;

public class FruitExample {
  private static final Map<String, Function<Integer, Fruit>> fruitFactory = new HashMap<>();
  static {
    fruitFactory.put("apple", Apple::new);
    fruitFactory.put("orange", Orange::new);
  }

  public static void main(String[] args) {
    
    // Constructor map example
    Fruit apple = giveMeFruit("apple", 12);
    System.out.println(apple);
    Fruit orange = giveMeFruit("orange", 35);
    System.out.println(orange);

    BiFunction<String, Integer, Fruit> bf1 = FruitExample::giveMeFruit;
    Fruit apple2 = bf1.apply("apple", 15);
    System.out.println(apple2);
  }

  public static Fruit giveMeFruit(String fruit, Integer weight) {
    return fruitFactory.get(fruit.toLowerCase()).apply(weight);
  }
}
