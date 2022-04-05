package com.modernjava.generictest;

import java.util.Arrays;
import java.util.List;

public class GenericExamples {
  public static void main(String[] args) {
    List<Building> buildings = Arrays.asList(
      new Building("Bank"),
      new Building("Library")
    );
    List<House> houses = Arrays.asList(
      new House("Blue House"),
      new House("Red House")
    );

    paintAllBuildings(buildings);
    paintAllBuildings(houses);
  }

  public static void paintAllBuildings(List<? extends Building> buildings) {
    buildings.forEach(Building::paint);
  }
}
