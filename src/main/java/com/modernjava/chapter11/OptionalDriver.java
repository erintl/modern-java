package com.modernjava.chapter11;

import java.util.Optional;

public class OptionalDriver {
  
  public static void main(String[] args) {
      System.out.println("------ Operations with Optional ------");


      System.out.println("--> Get car insurance name");
      Optional<Car> car1 = Optional.empty();
      Person p1 = new Person();
      p1.setAge(23);
      p1.setCar(car1);
      Optional<Person> person1 = Optional.of(p1);

      Car c2 = new Car();
      c2.setInsurance(Optional.empty());
      Optional<Car> car2 = Optional.of(c2);
      Person p2 = new Person();
      p2.setAge(24);
      p2.setCar(car2);
      Optional<Person> person2 = Optional.of(p2);

      Insurance i3 = new Insurance();
      i3.setName("Bad Insurance Co.");
      Car c3 = new Car();
      c3.setInsurance(Optional.of(i3));
      Optional<Car> car3 = Optional.of(c3);
      Person p3 = new Person();
      p3.setAge(24);
      p3.setCar(car3);
      Optional<Person> person3 = Optional.of(p3);
      
      System.out.printf("Person without car: %s%n", getCarInsuranceName(person1));
      System.out.printf("Car without insurance: %s%n", getCarInsuranceName(person2));
      System.out.printf("Car with insurance: %s%n", getCarInsuranceName(person3));
  }

  public static String getCarInsuranceName(Optional<Person> person) {
    return person.flatMap(Person::getCar)
      .flatMap(Car::getInsurance)
      .map(Insurance::getName)
      .orElse("Unknown");
  }
}
