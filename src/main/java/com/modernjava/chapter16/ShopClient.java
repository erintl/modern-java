package com.modernjava.chapter16;

import java.util.concurrent.Future;

public class ShopClient {
  
  public static void main(String[] args) {
    System.out.println("------ Shop Simulator ------");

    Shop shop = new Shop("First Shop");
    long startTime = System.nanoTime();
    Future<Double> futurePrice = shop.getPriceAsync2("Eye Phone");
    long invocationTime = (System.nanoTime() - startTime) / 1_000_000;
    System.out.printf("Invocation returned after %d ms %n", invocationTime);
    doOtherWork();
    try {
      double price = futurePrice.get();
      System.out.printf("The retrieved price is: %.2f%n", price);
    } catch (Exception e) {
      e.printStackTrace();
    }
    long retrievalTime = (System.nanoTime() - startTime) / 1_000_000;
    System.out.printf("The price was retrieved after %d ms %n", retrievalTime);
  }

  public static void doOtherWork() {
    System.out.println("Doing other work");
  }
}
