package com.modernjava.chapter15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {

  private int value = 0;
  private String name;
  private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println("------ Simple Cell Demo ------");

    SimpleCell c1 = new SimpleCell("C1");
    SimpleCell c2 = new SimpleCell("C2");
    SimpleCell c3 = new SimpleCell("C3");
    c1.subscribe(c3);
    c1.onNext(10);
    c2.onNext(20);
  }

  public SimpleCell(String name) {
    this.name = name;
  }

  @Override
  public void subscribe(Subscriber<? super Integer> subscriber) {
    subscribers.add(subscriber);
  }

  public void subscribe(Consumer<? super Integer> onNext) {
    subscribers.add(new Subscriber<>() {

      @Override
      public void onComplete() {}

      @Override
      public void onError(Throwable t) {
        t.printStackTrace();
      }

      @Override
      public void onNext(Integer val) {
        onNext.accept(val);
      }

      @Override
      public void onSubscribe(Subscription s) {}
      
    });
  }


  private void notifyAllSubscribers() {
    subscribers.forEach(subscriber -> subscriber.onNext(value));
  }

  @Override
  public void onComplete() {
    // TODO Auto-generated method stub
  }

  @Override
  public void onError(Throwable t) {
    t.printStackTrace();
  }

  @Override
  public void onNext(Integer value) {
    this.value = value;
    System.out.printf("%s:%d%n", name, value);
    notifyAllSubscribers();
  }

  @Override
  public void onSubscribe(Subscription arg0) {
    // TODO Auto-generated method stub
  }
}
