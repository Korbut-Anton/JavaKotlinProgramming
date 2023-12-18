package com.javalab2tests.ClassSets.ClassSet3;

import javax.inject.Inject;

public class BaseClass {
  @Inject
  public BaseClass(DependentOnTwoClass dependentOnTwoClass) {
    mDependentOnTwoClass = dependentOnTwoClass;
  }

  public DependentOnTwoClass GetDependence() {
    return mDependentOnTwoClass;
  }

  private final DependentOnTwoClass mDependentOnTwoClass;
}
