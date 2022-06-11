package com.modernjava.chapter17;

import java.util.Random;

public class TempInfo {
  
  public static final Random random = new Random();

  private final String town;
  private final int temp;

  public TempInfo(String town, int temp) {
    this.town = town;
    this.temp = temp;
  }

  public static TempInfo fetch(String town) {
    if (random.nextInt(10) == 0) {
      throw new RuntimeException("An error has ocurred!");
    }
    return new TempInfo(town, random.nextInt(100));
  }

  public int getTemp() {
    return temp;
  }

  public String getTown() {
    return town;
  }

  @Override
  public String toString() {
    return String.format("%s : %s", town, temp);
  }
}
