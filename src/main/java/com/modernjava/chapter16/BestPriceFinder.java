package com.modernjava.chapter16;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import com.modernjava.chapter16.ExchangeService.Money;

public class BestPriceFinder {
  
  private final List<Shop> shops = List.of(
    new Shop("Best_Price"),
    new Shop("Lets_Save_Big"),
    new Shop("My_favorite_Shop"),
    new Shop("Buy_it_All"),
    new Shop("Shop_Easy")
  );

  private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
  });

  public List<String> findPrices(String product) {
    return shops.stream()
      .map(shop -> shop.getPriceWithDiscount(product))
      .map(Quote::parse)
      .map(Discount::applyDiscount)
      .toList();
  }

  public List<String> findPricesParallel(String product) {
    return shops.parallelStream()
      .map(shop -> shop.getPriceWithDiscount(product))
      .map(Quote::parse)
      .map(Discount::applyDiscount)
      .toList();
  }

  public List<String> findPricesFuture(String product) {
    List<CompletableFuture<String>> priceFutures = findPricesStream(product).toList();
    return priceFutures.stream()
      .map(CompletableFuture::join)
      .toList();
  }

  public List<String> findPricesInUsd1(String product) {
    List<CompletableFuture<String>> priceFutures = shops.stream()
      .map(shop -> 
          CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor)
        .thenCombine(CompletableFuture.supplyAsync(
          () -> ExchangeService.getRate(Money.EUR, Money.USD))
            .completeOnTimeout(ExchangeService.DEFAULT_RATE, 2, TimeUnit.SECONDS),
          (price, rate) -> price * rate)
        .thenApply(price -> String.format("%s price is $%.2f", shop.getName(), price))
        .orTimeout(3, TimeUnit.SECONDS))
      .toList();
    return priceFutures.stream()
      .map(CompletableFuture::join)
      .toList();
  }

  public void printPricesStream(String product) {
    long startTime = System.nanoTime();
    CompletableFuture[] priceFutures = findPricesStream(product)
      .map(future -> future.thenAccept(s ->
        System.out.printf("%s (done in %d ms) %n", s, (System.nanoTime() - startTime) / 1_000_000)))
      .toArray(CompletableFuture[]::new);
    CompletableFuture.allOf(priceFutures).join();
    System.out.printf("All shops have responding in %d ms %n",
      (System.nanoTime() - startTime) / 1_000_000);
  }

  private Stream<CompletableFuture<String>> findPricesStream(String product) {
    return shops.stream()
      .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount(product), executor))
      .map(future -> future.thenApply(Quote::parse))
      .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() ->
        Discount.applyDiscount(quote), executor)));
  }
}
