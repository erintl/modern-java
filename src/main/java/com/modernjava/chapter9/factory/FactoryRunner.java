package com.modernjava.chapter9.factory;

public class FactoryRunner {
  
  public static void main(String[] args) {
    System.out.println("------ Factory Examples ------");

    System.out.println("--> Classic Factory");
    Product p1 = ProductFactory.createProduct("loan");
    System.out.printf("p1: %s%n", p1.getClass().getSimpleName());
    Product p2 = ProductFactory.createProduct("stock");
    System.out.printf("p2: %s%n:", p2.getClass().getSimpleName());
    Product p3 = ProductFactory.createProduct("bond");
    System.out.printf("p3: %s%n:", p3.getClass().getSimpleName());

    System.out.println("--> Lambda Factory");
    Product p4 = ProductFactory.createProduct2("loan");
    System.out.printf("p4: %s%n", p4.getClass().getSimpleName());
    Product p5 = ProductFactory.createProduct2("stock");
    System.out.printf("p5: %s%n:", p5.getClass().getSimpleName());
    Product p6 = ProductFactory.createProduct2("bond");
    System.out.printf("p6: %s%n:", p6.getClass().getSimpleName());
  }
}