package com.javalab2tests.ClassSets.ClassSet5;

import javax.inject.Inject;

public class DependentOnTUD2 {
  @Inject
  public DependentOnTUD2(TwiceUsedDependence twiceUsedDependence, Z z) {
    mTwiceUsedDependence = twiceUsedDependence;
  }

  public TwiceUsedDependence GetTwiceUsedDependence() {
    return mTwiceUsedDependence;
  }

  private final TwiceUsedDependence mTwiceUsedDependence;
}
