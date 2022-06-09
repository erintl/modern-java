package com.modernjava.chapter16;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
  
  private final String name;
  private final Random random;

  public Shop(String name) {
    this.name = name;
    random = new Random(name.charAt(0) * name.charAt(1) * (long)name.charAt(2));
  }

  public String getPriceWithDiscount(String product) {
    double price = calculatePrice(product);
    Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
    return String.format("%s:%.2f:%s", name, price, code);
  }

  public double getPrice(String product) {
    return calculatePrice(product);
  }

  public String getName() {
    return name;
  }

  public Future<Double> getPriceAsync1(String product) {
    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    new Thread(() -> {
      try {
        double price = calculatePrice(product);
        // throw new RuntimeException("Something bad happened...");
        futurePrice.complete(price);
      } catch (Exception ex) {
        futurePrice.completeExceptionally(ex);
      }
    }).start();
    return futurePrice;
  }

  public Future<Double> getPriceAsync2(String product) {
    return CompletableFuture.supplyAsync(() -> calculatePrice(product));
  }

  private double calculatePrice(String product) {
    Util.delay();
    return random.nextDouble() * product.charAt(0) * product.charAt(1);
  }
}
