package com.modernjava.chapter9.observer;

public class ObserverRunner {
  
  public static void main(String[] args) {
    System.out.println("------ Observer Pattern ------");

    System.out.println("--> Classic Implementation");
    Feed feed1 = new Feed();
    feed1.registerObserver(new NyTimes());
    feed1.registerObserver(new Guardian());
    feed1.registerObserver(new LeMonde());
    feed1.notifyObservers("Money, money, money!");
    feed1.notifyObservers("God save the queen");
    feed1.notifyObservers("We like wine");

    System.out.println("--> Lambda Implementation");
    Feed feed2 = new Feed();
    feed2.registerObserver((s) -> {
      if (s != null && s.contains("money")) {
        System.out.println("Breaking news in New York!");
      }
    });
    feed2.registerObserver(s -> {
      if (s != null && s.contains("queen")) {
        System.out.println("Yet another news story in London...");
      }
    });
    feed2.registerObserver(s -> {
      if (s != null && s.contains("wine")) {
        System.out.println("Today cheese, wine and news!");
      }
    });
    feed2.notifyObservers("Money, money, money!");
    feed2.notifyObservers("God save the queen");
    feed2.notifyObservers("We like wine");
  }

}
