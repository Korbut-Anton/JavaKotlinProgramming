package com.javalab2tests.ClassSets.ClassSet3;

import javax.inject.Inject;

public class DependentOnTwoClass {
  @Inject
  public DependentOnTwoClass(Dependence1 dependence1, Dependence2 dependence2) {
    mDependence1 = dependence1;
    mDependence2 = dependence2;
  }

  public Dependence1 GetFirstDependence() {
    return mDependence1;
  }

  public Dependence2 GetSecondDependence() {
    return mDependence2;
  }

  private final Dependence1 mDependence1;
  private final Dependence2 mDependence2;
}
