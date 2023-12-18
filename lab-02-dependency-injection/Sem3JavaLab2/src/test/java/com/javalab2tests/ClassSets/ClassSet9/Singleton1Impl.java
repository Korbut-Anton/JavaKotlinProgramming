package com.javalab2tests.ClassSets.ClassSet9;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Singleton1Impl implements Singleton1Interface {
  @Inject
  public Singleton1Impl(BetweenSingletons1 betweenSingletons1,
                        BetweenSingletons2 betweenSingletons2) {
    mBetweenSingletons1 = betweenSingletons1;
    mBetweenSingletons2 = betweenSingletons2;
  }

  public BetweenSingletons1 GetFirstDependence() {
    return mBetweenSingletons1;
  }

  public BetweenSingletons2 GetSecondDependence() {
    return mBetweenSingletons2;
  }

  private final BetweenSingletons1 mBetweenSingletons1;
  private final BetweenSingletons2 mBetweenSingletons2;
}
