package com.modernjava.chapter10.dsl;

import static com.modernjava.chapter10.dsl.MethodChainingOrderBuilder.forCustomer;
import static com.modernjava.chapter10.dsl.NestedFunctionOrderBuilder.order;
import static com.modernjava.chapter10.dsl.NestedFunctionOrderBuilder.buy;
import static com.modernjava.chapter10.dsl.NestedFunctionOrderBuilder.sell;
import static com.modernjava.chapter10.dsl.NestedFunctionOrderBuilder.on;
import static com.modernjava.chapter10.dsl.NestedFunctionOrderBuilder.at;
import static com.modernjava.chapter10.dsl.NestedFunctionOrderBuilder.stock;

public class TradingMain {
  
  public static void main(String[] args) {
    System.out.println("------ Trading DSLs ------");
    plain();
    methodChaining();
    nestedFunctions();
    lambdas();
  }

  private static void plain() {
    System.out.println("--> Plain:");

    Order order = new Order();
    order.setCustomer("Big Bank");

    Stock stock1 = new Stock();
    stock1.setSymbol("IBM");
    stock1.setMarket("NYSE");

    Stock stock2 = new Stock();
    stock2.setSymbol("GOOG");
    stock2.setMarket("NASDAQ");

    Trade trade1 = new Trade();
    trade1.setType(Trade.Type.BUY);
    trade1.setStock(stock1);
    trade1.setPrice(125.00);
    trade1.setQuantity(80);
    order.addTrade(trade1);

    Trade trade2 = new Trade();
    trade2.setType(Trade.Type.SELL);
    trade2.setStock(stock2);
    trade2.setPrice(375.00);
    trade2.setQuantity(50);
    order.addTrade(trade2);

    System.out.println(order);
  }

  private static void methodChaining() {
    System.out.println("--> Method Chaining");

    Order order = forCustomer("Big Bank")
      .buy(80)
        .stock("IBM")
          .on("NYSE")
            .at(125.00)
      .sell(500)
        .stock("GOOG")
          .on("NASDAQ")
            .at(375)
      .end();
    System.out.println(order);
  }

  private static void nestedFunctions() {
    Order order = order("Big Bank", 
      buy(80,
        stock("IBM", on("NYSE")),
        at(125.00)),
      sell(50,
        stock("GOOG", on("NASDAQ")),
        at(375.00))
    );
    System.out.println(order);
  }

  private static void lambdas() {
    System.out.println("--> Lambdas");

    Order order = LambdaOrderBuilder.order(o -> {
      o.forCustomer("Big Bank");
      o.buy(t -> {
        t.price(125.00);
        t.quantity(80);
        t.stock(s -> {
          s.market("NYSE");
          s.symbol("IBM");
        });
      });
      o.sell(t -> {
        t.price(375.00);
        t.quantity(50);
        t.stock(s -> {
          s.symbol("GOOG");
          s.market("NASDAQ");
        });
      });
    });
    System.out.println(order);

    System.out.println("--> Calculating Tax");
    TaxCalculator taxCalculator = new TaxCalculator();
    double taxValue = taxCalculator.with(Tax::regional)
      .with(Tax::surcharge)
      .calculate(order);
    System.out.printf("The tax value is: %.2f%n", taxValue);
  }
}
