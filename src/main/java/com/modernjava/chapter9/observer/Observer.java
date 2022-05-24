package com.modernjava.chapter9.observer;

@FunctionalInterface
public interface Observer {
  
  void notify(String s);
}
