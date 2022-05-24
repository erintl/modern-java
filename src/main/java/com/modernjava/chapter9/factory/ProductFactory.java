package com.modernjava.chapter9.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

  private static final Map<String, Supplier<Product>> products = new HashMap<>();
  static {
    products.put("loan", Loan::new);
    products.put("stock", Stock::new);
    products.put("bond", Bond::new);
  }

  private ProductFactory() {}
  
  public static Product createProduct(String name) {
    switch (name) {
      case "loan":
        return new Loan();
      case "stock":
        return new Stock();
      case "bond":
        return new Bond();
      default:
        throw new RuntimeException("No such product " + name);
    }
  }

  public static Product createProduct2(String name) {
    Supplier<Product> product = products.get(name);
    if (product != null) {
      return product.get();
    } else {
      throw new RuntimeException("No such product " + name);
    }
  }
}
