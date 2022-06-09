package com.modernjava.chapter16;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class Util {

  private static final Random random = new Random();
  private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

  public static double format(double number) {
    synchronized (formatter) {
      return Double.parseDouble(formatter.format(number));
    }
  }
  
  public static void delay() {
    int delay = 500 + random.nextInt(2000);
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
