package com.modernjava.chapter17;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TempProcessor implements Processor<TempInfo, TempInfo> {
  
  private Subscriber<? super TempInfo> subscriber;

  @Override
  public void onComplete() {
    subscriber.onComplete();
  }

  @Override
  public void onError(Throwable t) {
    subscriber.onError(t);
  }

  @Override
  public void onNext(TempInfo tempInfo) {
    System.out.println("Converting F to C");
    subscriber.onNext(new TempInfo(tempInfo.getTown(), (tempInfo.getTemp() - 32) * 5 / 9));
  }

  @Override
  public void onSubscribe(Subscription subscription) {
    subscriber.onSubscribe(subscription);
  }

  @Override
  public void subscribe(Subscriber<? super TempInfo> subscriber) {
    this.subscriber = subscriber;
  }
}
