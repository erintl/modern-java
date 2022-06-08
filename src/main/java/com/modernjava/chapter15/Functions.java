package com.modernjava.chapter15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.IntConsumer;

public class Functions {

  private Functions() {}
  
  public static int f(int x) {
    return x * 2;
  }

  public static int g(int x) {
    return x + 1;
  }

  public static Integer fo(int x) {
    return Integer.valueOf(x * 2);
  }

  public static Integer go(int x) {
    return Integer.valueOf(x + 1);
  }

  public static Future<Integer> ff(int x) {
    return new CompletableFuture<Integer>().completeAsync(() -> Integer.valueOf(x * 2));
  }

  public static Future<Integer> gf(int x) {
    return new CompletableFuture<Integer>().completeAsync(() -> Integer.valueOf(x + 1));
  }

  public static void fCallback(int x, IntConsumer dealWithResult) {
    dealWithResult.accept(f(x));
  }

  public static void gCallback(int x, IntConsumer dealWithResult) {
    dealWithResult.accept(g(x));
  }
}
