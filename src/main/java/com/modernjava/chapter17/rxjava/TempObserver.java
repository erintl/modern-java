package com.modernjava.chapter17.rxjava;

import com.modernjava.chapter17.TempInfo;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class TempObserver implements Observer<TempInfo> {
  
  @Override
  public void onComplete() {
    System.out.println("Done!");
  }

  @Override
  public void onError(Throwable t) {
    System.err.printf("Got error: %s", t.getMessage());
  }

  @Override
  public void onNext(TempInfo tempInfo) {
    System.out.println(tempInfo);
  }

  @Override
  public void onSubscribe(Disposable d) {}
}
