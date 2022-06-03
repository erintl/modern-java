package com.modernjava.chapter11;

import java.util.Optional;

public class Person {
  
  private Optional<Car> car;
  private int age;

  public Optional<Car> getCar() {
    return car;
  }

  public int getAge() {
    return age;
  }

  public void setCar(Optional<Car> car) {
    this.car = car;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
