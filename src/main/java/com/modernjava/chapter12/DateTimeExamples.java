package com.modernjava.chapter12;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateTimeExamples {
  
  public static void main(String[] args) {
    localDateTimeExamples();
    durationPeriodExamples();
    temporalAdjusterExamples();
    timeZoneExamples();
  }

  public static void localDateTimeExamples() {
    System.out.println("------ LocalDateTime Examples ------");

    LocalDate date = LocalDate.of(2017, 9, 21);
    LocalTime time = LocalTime.of(13, 45, 20);
    System.out.printf("LocalDate: %s%n", date);
    System.out.printf("LocalTime: %s%n", time);
  }

  public static void durationPeriodExamples() {
    System.out.println("------ Duration and Period Examples ------");
    Duration threeMinutes1 = Duration.ofMinutes(3);
    System.out.printf("Three minute duration 1: %s%n", threeMinutes1);
  }

  public static void temporalAdjusterExamples() {
    System.out.println("------ Temporal Adjuster Examples ------");
    LocalDate localDate1 = LocalDate.of(2022, 6, 3);
    LocalDate localDate2 = LocalDate.of(2022, 6, 4);
    LocalDate localDate3 = LocalDate.of(2022, 6, 5);
    System.out.printf("Adjusted from Friday: %s%n", localDate1.with(new NextWorkingDay()));
    System.out.printf("Adjusted from Saturday: %s%n", localDate2.with(new NextWorkingDay()));
    System.out.printf("Adjusted from Sunday: %s%n", localDate3.with(new NextWorkingDay()));
  }

  public static void timeZoneExamples() {
    System.out.println("------ Time Zone Examples ------");
    ZoneId romeZone = ZoneId.of("Europe/Rome");

    Instant instant = Instant.now();
    LocalDateTime fromInstant = LocalDateTime.ofInstant(instant, romeZone);
    System.out.printf("Rome zone date time %s%n", fromInstant);

    LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 14, 45);
    System.out.printf("LocalDateTime: %s%n", dateTime);
    ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
    OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);
    System.out.printf("New York offset: %s%n", dateTimeInNewYork);
  }
}
