package com.modernjava.chapter16;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BestPriceFinderMain {

  private static BestPriceFinder bestPriceFinder = new BestPriceFinder();
  private static String EYE_PHONE = "Eye Phone 45s";
  
  public static void main(String[] args) {
    System.out.println("------ Best Price Finder Simulation ------");

    // execute("sequential", () -> bestPriceFinder.findPrices(EYE_PHONE));
    // execute("parallel", () -> bestPriceFinder.findPricesParallel(EYE_PHONE));
    // execute("future", () -> bestPriceFinder.findPricesFuture(EYE_PHONE));
    // execute("Future in USD 1", () -> bestPriceFinder.findPricesInUsd1(EYE_PHONE));
    bestPriceFinder.printPricesStream(EYE_PHONE);
  }

  private static void execute(String message, Supplier<List<String>> s) {
    long startTime = System.nanoTime();
    System.out.printf("%nStarting %s%n", message);
    String productPrices = s.get()
      .stream()
      .collect(Collectors.joining("\n", "------\n", "\n------"));
    System.out.println(productPrices);
    long duration = (System.nanoTime() - startTime) / 1_000_000;
    System.out.printf("%s execution time: %d ms %n", message, duration);
  }
}
