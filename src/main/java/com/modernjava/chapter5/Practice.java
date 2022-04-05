package com.modernjava.chapter5;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Practice {
  
  public static void main(String[] args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader brian = new Trader("Brian", "Cambridge");
    Trader alan = new Trader("Alan", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
      new Transaction(brian, 2011, 300),
      new Transaction(raoul, 2012, 1000),
      new Transaction(raoul, 2011, 400),
      new Transaction(mario, 2012, 710),
      new Transaction(mario, 2012, 700),
      new Transaction(alan, 2012, 950)
    );

    query1(transactions);
    query2(transactions);
    query3(transactions);
    query4(transactions);
    query5(transactions);
    query6(transactions);
    query7(transactions);
    query8(transactions);
  }

  private static void query1(List<Transaction> transactions) {
    System.out.println("Query 1:");
    List<Transaction> tr2011 = transactions.stream()
      .filter(t -> t.year() == 2011)
      .sorted(comparing(Transaction::value))
      .collect(toList());
    tr2011.forEach(System.out::println);
  }

  private static void query2(List<Transaction> transactions) {
    System.out.println("\nQuery 2:");
    List<String> cities = transactions.stream()
      .map(t -> t.trader().city())
      .distinct()
      .collect(toList());
    cities.forEach(System.out::println);
  }

  private static void query3(List<Transaction> transactions) {
    System.out.println("\nQuery 3:");
    List<Trader> traders = transactions.stream()
      .map(Transaction::trader)
      .filter(t -> t.city().equals("Cambridge"))
      .distinct()
      .sorted(comparing(Trader::name))
      .collect(toList());
    traders.forEach(System.out::println);
  }

  private static void query4(List<Transaction> transactions) {
    System.out.println("\nQuery 4:");
    String traders = transactions.stream()
      .map(t -> t.trader().name())
      .distinct()
      .sorted()
      .reduce("", (n1, n2) -> n1 + n2);
    System.out.println(traders);
  }

  private static void query5(List<Transaction> transactions) {
    System.out.println("\nQuery 5:");
    boolean isMilanBased = transactions.stream()
      .anyMatch(t -> t.trader().city().equals("Milan"));
    System.out.println(isMilanBased);
  }

  private static void query6(List<Transaction> transactions) {
    System.out.println("\nQuery 6:");
    transactions.stream()
      .filter(t -> t.trader().city().equals("Cambridge"))
      .map(Transaction::value)
      .forEach(System.out::println);
  }

  private static void query7(List<Transaction> transactions) {
    System.out.println("\nQuery 7:");
    Optional<Integer> maxValue = transactions.stream()
      .map(Transaction::value)
      .reduce(Integer::max);
    maxValue.ifPresent(System.out::println);
  }

  private static void query8(List<Transaction> transactions) {
    System.out.println("\nQuery 8:");
    Optional<Transaction> transaction = transactions.stream()
      .reduce((t1, t2) -> t1.value() < t2.value() ? t1 : t2);
    transaction.ifPresent(System.out::println);
  }
}
