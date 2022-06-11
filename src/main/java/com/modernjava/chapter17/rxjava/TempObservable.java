package com.modernjava.chapter17.rxjava;

import java.util.concurrent.TimeUnit;
import com.modernjava.chapter17.TempInfo;
import io.reactivex.rxjava3.core.Observable;

public class TempObservable {

  public static Observable<TempInfo> getTemperature(String town) {
    return Observable.create(emitter -> Observable.interval(1, TimeUnit.SECONDS)
      .subscribe(i -> {
        if (!emitter.isDisposed()) {
          if (i >= 5) {
            emitter.onComplete();
          } else {
            try {
              emitter.onNext(TempInfo.fetch(town));
            } catch (Exception e) {
              emitter.onError(e);
            }
          }
        }
      }));
  }

  public static Observable<TempInfo> getCelsiusTemperature(String town) {
    return getTemperature(town)
      .map(t -> new TempInfo(t.getTown(), (t.getTemp() - 32) * 5 / 9));
  }

  public static Observable<TempInfo> GetNegativeCelsiusTempurature(String town) {
    return getCelsiusTemperature(town)
      .filter(t -> t.getTemp() < 0);
  }
}
