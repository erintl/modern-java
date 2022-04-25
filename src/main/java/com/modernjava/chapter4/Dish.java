package com.modernjava.chapter4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Dish(String name, boolean isVegetarian, int calories, Type type) {

  public enum Type {
    MEAT,
    FISH,
    OTHER
  }

  public static final List<Dish> menu = Arrays.asList(
    new Dish("pork", false, 800, Type.MEAT),
    new Dish("beef", false, 700, Type.MEAT),
    new Dish("chicken",false, 400, Type.MEAT),
    new Dish("french fries", true, 530, Type.OTHER),
    new Dish("rice", true, 350, Type.OTHER),
    new Dish("season fruit", true, 120, Type.OTHER),
    new Dish("pizza", true, 550, Type.OTHER),
    new Dish("prawns", false, 400, Type.FISH),
    new Dish("salmon", false, 450, Type.FISH)
  );

  public static final Map<String, List<String>> dishTags = new HashMap<>();
  static {
    dishTags.put("pork", Arrays.asList("greasy", "salty"));
    dishTags.put("beef", Arrays.asList("salty", "roasted"));
    dishTags.put("chicken", Arrays.asList("fried", "crisp"));
    dishTags.put("french fries", Arrays.asList("greasy", "fried"));
    dishTags.put("rice", Arrays.asList("light", "natural"));
    dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
    dishTags.put("pizza", Arrays.asList("tasty", "salty"));
    dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
    dishTags.put("salmon", Arrays.asList("delicious", "fresh"));
  }
}
