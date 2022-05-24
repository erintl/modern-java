package com.modernjava.chapter9.strategy;

public class StrategyRunner {
  
  public static void main(String[] args) {
    System.out.println("------ Strategy Pattern ------");
    System.out.println("--> Classic implementation");
    Validator v1 = new Validator(new IsNumeric());
    Validator v2 = new Validator(new IsAllLowerCase());
    System.out.println("aaaa (is numeric): " + v1.validate("aaaa"));
    System.out.println("bbbb (is all lower case): " + v2.validate("bbbb"));

    System.out.println("--> Using Lambdas");
    Validator v3 = new Validator(s -> s.matches("[a-z]+"));
    Validator v4 = new Validator(s -> s.matches("\\d+"));
    System.out.println("aaaa (is numeric): " + v3.validate("aaaa"));
    System.out.println("bbbb (is all lower case): " + v4.validate("bbbb"));
  }
}
