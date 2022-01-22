package com.modernjava.chapter2;

public class Apple implements Fruit {
  private Integer weight = 0;
  private Color color;
  private String country;

  public Apple() {}

  public Apple(Integer weight) {
      this(weight, Color.GREEN);
  }

  public Apple(Integer weight, Color color) {
    this(weight, color, "United States");
  }

  public Apple(Integer weight, Color color, String country) {
      this.weight = weight;
      this.color = color;
      this.country = country;
  }

  public Integer getWeight() {
      return weight;
  }

  public void setWeight(int weight) {
      this.weight = weight;
  }

  public Color getColor() {
      return color;
  }

  public void setColor(Color color) {
      this.color = color;
  }

  public String getCountry() {
      return country;
  }

  public void setCountry(String country) {
      this.country = country;
  }

  @Override
  public String toString() {
      return String.format("Apple{color=%s, weight=%d, country=%s}", color, weight, country);
  }
}
