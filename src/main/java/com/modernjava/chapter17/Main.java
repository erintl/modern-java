package com.modernjava.chapter17;

import java.util.concurrent.Flow.Publisher;

public class Main {

  public static void main(String[] args) {
    // getTemperatures("New York").subscribe(new TempSubscriber());
    getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
  }

  private static Publisher<TempInfo> getTemperatures(String town) {
    return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
  }

  private static Publisher<TempInfo> getCelsiusTemperatures(String town) {
    return subscriber -> {
      TempProcessor tempProcessor = new TempProcessor();
      tempProcessor.subscribe(subscriber);
      tempProcessor.onSubscribe(new TempSubscription(tempProcessor, town));
    };
  }
}
