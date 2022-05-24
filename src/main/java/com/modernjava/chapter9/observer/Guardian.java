package com.modernjava.chapter9.observer;

public class Guardian implements Observer {
  
  @Override
  public void notify(String s) {
    if (s != null && s.contains("queen")) {
      System.out.println("Yet another news story in London...");
    }
  }
}
