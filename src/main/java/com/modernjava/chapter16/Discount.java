package com.modernjava.chapter16;

public class Discount {
  
  public enum Code {
    NONE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15),
    DIAMOND(20);

    private final int percentage;

    Code(int percentage) {
      this.percentage = percentage;
    }
  }

  public static String applyDiscount(Quote quote) {
    double discountedPrice = Discount.apply(quote.getPrice(), quote.getDiscountCode());
    return String.format("%s price is %.2f", quote.getShopName(), discountedPrice);
  }

  private static double apply(double price, Code code) {
    Util.delay();
    return Util.format(price * (100 - code.percentage) / 100);
  }
}
