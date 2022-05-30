package com.modernjava.chapter10.dsl;

import java.util.stream.Stream;

public class NestedFunctionOrderBuilder {

  private NestedFunctionOrderBuilder() {}

  public static Order order(String customer, Trade... trades) {
    Order order = new Order();
    order.setCustomer(customer);
    Stream.of(trades).forEach(order::addTrade);
    return order;
  }

  public static Trade buy(int quantity, Stock stock, double price) {
    return buildTrade(quantity, stock, price, Trade.Type.BUY);
  }

  public static Trade sell(int quantity, Stock stock, double price) {
    return buildTrade(quantity, stock, price, Trade.Type.SELL);
  }

  public static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type type) {
    Trade trade = new Trade();
    trade.setQuantity(quantity);
    trade.setStock(stock);
    trade.setPrice(price);
    trade.setType(type);
    return trade;
  }

  public static Stock stock(String symbol, String market) {
    Stock stock = new Stock();
    stock.setMarket(market);
    stock.setSymbol(symbol);
    return stock;
  }

  public static double at(double price) {
    return price;
  }

  public static String on(String market) {
    return market;
  }
}