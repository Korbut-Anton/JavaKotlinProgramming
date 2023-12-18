package com.javalab2tests.ClassSets.ClassSet5;

import javax.inject.Inject;

public class DependentOnTUD1 {
  @Inject
  public DependentOnTUD1(TwiceUsedDependence twiceUsedDependence) {
    mTwiceUsedDependence = twiceUsedDependence;
  }

  public TwiceUsedDependence GetTwiceUsedDependence() {
    return mTwiceUsedDependence;
  }

  private final TwiceUsedDependence mTwiceUsedDependence;
}
