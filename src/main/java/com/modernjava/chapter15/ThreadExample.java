package com.modernjava.chapter15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadExample {
  
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    threads();
    executorService();
    futures();
    callbacks();
    cfComplete();
    cfCombine();
    executorServiceScheduled();
  }

  public static void threads() throws InterruptedException {
    System.out.println("------ Thread Example ------");
    int x = 1337;
    Result result = new Result();

    Thread t1 = new Thread(() -> result.left = Functions.f(x));
    Thread t2 = new Thread(() -> result.right = Functions.g(x));
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.printf("The result is: %s%n", result.left + result.right);
  }

  public static void executorService() throws ExecutionException, InterruptedException {
    System.out.println("------ Executor Service Example ------");
    int x = 1337;

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<Integer> y = executorService.submit(() -> Functions.fo(x));
    Future<Integer> z = executorService.submit(() -> Functions.go(x));

    System.out.printf("The result is: %s%n", y.get() + z.get());

    executorService.shutdown();
  }

  public static void futures() throws InterruptedException, ExecutionException {
    System.out.println("------ Future Example ------");

    int x = 1337;

    Future<Integer> y = Functions.ff(x);
    Future<Integer> z = Functions.gf(x);

    System.out.printf("The result is: %s%n", y.get() + z.get());
  }

  public static void callbacks() {
    System.out.println("------ Callback example ------");

    int x = 1337;
    Result result = new Result();

    Functions.fCallback(x, (int y) -> {
      result.left = y;
      System.out.printf("The first result is: %d%n", result.left + result.right);
    });

    Functions.gCallback(x, (int z) -> {
      result.right = z;
      System.out.printf("The second result is: %d%n", result.left + result.right);
    });
  }

  public static void executorServiceScheduled() {
    System.out.println("------ Scheduled Executor Service Example ------");

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    System.out.println("Doing work 1");
    scheduledExecutorService.schedule(() -> System.out.println("Doing work 2") , 3, TimeUnit.SECONDS);
    System.out.println("Work 2 has been scheduled");

    scheduledExecutorService.shutdown();
  }

  public static void cfComplete() throws InterruptedException, ExecutionException {
    System.out.println("------ CF Complete Example ------");

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    int x = 1337;

    CompletableFuture<Integer> a = new CompletableFuture<>();
    executorService.submit(() -> a.complete(Functions.f(x)));
    int b = Functions.g(x);
    System.out.printf("The result is %d%n", a.get() + b);

    executorService.shutdown();
  }

  public static void cfCombine() throws InterruptedException, ExecutionException {
    System.out.println("------ CF Combine Example ------");

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    int x = 1337;
    CompletableFuture<Integer> a = new CompletableFuture<>();
    CompletableFuture<Integer> b = new CompletableFuture<>();
    CompletableFuture<Integer> c = a.thenCombine(b, (y, z) -> y + z);
    executorService.submit(() -> a.complete(Functions.f(x)));
    executorService.submit(() -> b.complete(Functions.g(x)));
    System.out.printf("The final result is: %d%n", c.get());

    executorService.shutdown();
  }
}
