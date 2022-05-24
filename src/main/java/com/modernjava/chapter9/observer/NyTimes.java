package com.modernjava.chapter9.observer;

public class NyTimes implements Observer {

  @Override
  public void notify(String s) {
    if (s != null && s.contains("money")) {
      System.out.println("Breaking news in New York!");
    }
  }
}
