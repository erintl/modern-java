package com.modernjava.chapter9.observer;

public interface Subject {
  
  void registerObserver(Observer observer);
  void notifyObservers(String s);
}
