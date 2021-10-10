package com.javalab2tests.ClassSets.ClassSet9;

import javax.inject.Inject;

public class BetweenSingletons1 {
  @Inject
  public BetweenSingletons1(Singleton2 singleton2) {
    mSingleton2 = singleton2;
  }

  public Singleton2 GetSingleton() {
    return mSingleton2;
  }

  private final Singleton2 mSingleton2;
}
