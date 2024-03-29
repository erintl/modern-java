package com.modernjava.chapter16;

public class Quote {
  
  private final String shopName;
  private final double price;
  private final Discount.Code discountCode;

  Quote(String shopName, double price, Discount.Code discountCode) {
    this.shopName = shopName;
    this.price = price;
    this.discountCode = discountCode;
  }

  public String getShopName() {
    return shopName;
  }

  public double getPrice() {
    return price;
  }

  public Discount.Code getDiscountCode() {
    return discountCode;
  }

  public static Quote parse(String s) {
    String[] tokens = s.split(":");
    String shopName = tokens[0];
    double price = Double.parseDouble(tokens[1]);
    Discount.Code discountCode = Discount.Code.valueOf(tokens[2]);
    return new Quote(shopName, price, discountCode);
  }
}
