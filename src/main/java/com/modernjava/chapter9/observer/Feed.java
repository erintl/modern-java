package com.modernjava.chapter9.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {
  
  private final List<Observer> observers = new ArrayList<>();

  @Override
  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void notifyObservers(String s) {
    observers.forEach(o -> o.notify(s));
  }
}
