package com.modernjava.generictest;

public class House extends Building {

  public House() {}

  public House(String name) {
    super(name);
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    return super.equals(obj);
  }

  @Override
  public void paint() {
    System.out.println("Painting the house " + getName());
  }

  @Override
  public String toString() {
    return String.format("House {name=%s", getName());
  }
  
  
}
