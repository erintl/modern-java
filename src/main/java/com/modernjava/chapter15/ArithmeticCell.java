package com.modernjava.chapter15;

public class ArithmeticCell extends SimpleCell {

  private int left = 0;
  private int right = 0;

  public ArithmeticCell(String name) {
    super(name);
  }

  public void setLeft(int left) {
    this.left = left;
    onNext(left + right);
  }

  public void setRight(int right) {
    this.right = right;
    onNext(right + left);
  }

  public static void main(String[] args) {
    test1();
    test2();
  }

  private static void test1() {
    System.out.println("------ Test 1 ------");

    // C3 = C1 + C2;
    SimpleCell c1 = new SimpleCell("C1");
    SimpleCell c2 = new SimpleCell("C2");
    ArithmeticCell c3 = new ArithmeticCell("C3");

    c1.subscribe(c3::setLeft);
    c2.subscribe(c3::setRight);
    c1.onNext(10);
    c2.onNext(20);
    c1.onNext(15);
  }

  private static void test2() {
    System.out.println("------ Test 2 ------");

    // C3 = C1 + C2 and C5 = C3 + C4
    SimpleCell c1 = new SimpleCell("C1");
    SimpleCell c2 = new SimpleCell("C2");
    ArithmeticCell c3 = new ArithmeticCell("C3");
    SimpleCell c4 = new SimpleCell("C4");
    ArithmeticCell c5 = new ArithmeticCell("C5");

    c1.subscribe(c3::setLeft);
    c2.subscribe(c3::setRight);
    c3.subscribe(c5::setLeft);
    c4.subscribe(c5::setRight);
    c1.onNext(10);
    c2.onNext(20);
    c1.onNext(15);
    c4.onNext(1);
    c4.onNext(3);
  }
}
