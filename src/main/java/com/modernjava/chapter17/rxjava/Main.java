package com.modernjava.chapter17.rxjava;

import com.modernjava.chapter17.TempInfo;
import io.reactivex.rxjava3.core.Observable;

public class Main {
  
  public static void main(String[] args) {
    Observable<TempInfo> tempObservable = TempObservable.GetNegativeCelsiusTempurature("New York");
    tempObservable.subscribe(new TempObserver());

    try {
      Thread.sleep(6000L);
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
