package com.modernjava.chapter10.dsl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
  
  private String customer;
  private List<Trade> trades = new ArrayList<>();

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public void addTrade(Trade trade) {
    trades.add(trade);
  }

  public double getValue() {
    return trades.stream().mapToDouble(Trade::getValue).sum();
  }

  @Override
  public String toString() {
    String tradesStr = trades.stream().map(t -> " " + t).collect(Collectors.joining("\n", "[\n", "\n]"));
    return String.format("Order[customer=%s, trades=%s]", customer, tradesStr);
  }
}
