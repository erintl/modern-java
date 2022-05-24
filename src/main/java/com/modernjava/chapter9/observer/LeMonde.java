package com.modernjava.chapter9.observer;

public class LeMonde implements Observer {
  
  @Override
  public void notify(String s) {
    if (s != null && s.contains("wine")) {
      System.out.println("Today cheese, wine and news!");
    }
  }
}
