package com.modernjava.chapter3;

public class Letter {
  public static String addHeader(String text) {
    return "From Raoul, Mario and Alan:\n" + text;
  }

  public static String addFooter(String text) {
    return text + "\nKind regards";
  }

  public static String checkSpelling(String text) {
    return text.replaceAll("labda", "lambda");
  }
}
