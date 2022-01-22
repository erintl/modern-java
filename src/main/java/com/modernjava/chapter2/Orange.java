package com.modernjava.chapter2;

public class Orange implements Fruit {
  private Integer weight = 0;
  private Color color = Color.ORANGE;

  public Orange() {}

  public Orange(Integer weight) {
    this.weight = weight;
  }

  public Integer getWeight() {
    return weight;
  }

  public Color getColor() {
    return color;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
    return String.format("Orange{color=%s, weight=%d}", color, weight);
  }
}
