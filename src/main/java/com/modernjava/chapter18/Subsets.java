package com.modernjava.chapter18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subsets {
  
  public static void main(String[] args) {
    List<List<Integer>> subs = subsets(List.of(1, 4, 9));
    subs.forEach(System.out::println);
  }

  public static List<List<Integer>> subsets(List<Integer> list) {
    if (list.isEmpty()) {
      List<List<Integer>> result = new ArrayList<>();
      result.add(Collections.emptyList());
      return result;
    }
      Integer first = list.get(0);
      List<Integer> rest = list.subList(1, list.size());
      List<List<Integer>> subAns1 = subsets(rest);
      List<List<Integer>> subAns2 = insertAll(first, subAns1);
      return concat(subAns1, subAns2);
  }

  public static List<List<Integer>> insertAll(Integer item, List<List<Integer>> lists) {
    List<List<Integer>> result = new ArrayList<>();
    lists.forEach(list -> {
      List<Integer> copyList = new ArrayList<>();
      copyList.add(item);
      copyList.addAll(list);
      result.add(copyList);
    });
    return result;
  }

  public static List<List<Integer>> concat(List<List<Integer>> list1, List<List<Integer>> list2) {
    List<List<Integer>> newList = new ArrayList<>(list1);
    newList.addAll(list2);
    return newList;
  }
}
