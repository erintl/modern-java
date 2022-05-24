package com.modernjava.chapter10.dsl;

import java.util.function.DoubleUnaryOperator;

public class TaxCalculator {
  private DoubleUnaryOperator taxFunction = d -> d;

  public TaxCalculator with(DoubleUnaryOperator f) {
    taxFunction = taxFunction.andThen(f);
    return this;
  }

  public double calculate(Order order) {
    return taxFunction.applyAsDouble(order.getValue());
  }
}
