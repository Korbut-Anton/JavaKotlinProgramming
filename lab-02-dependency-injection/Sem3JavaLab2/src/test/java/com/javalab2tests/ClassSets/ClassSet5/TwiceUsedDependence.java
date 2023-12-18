package com.javalab2tests.ClassSets.ClassSet5;

import javax.inject.Inject;

public class TwiceUsedDependence {
  @Inject
  public TwiceUsedDependence(DependenceOfTUD dependenceOfTUD) {
    mDependenceOfTUD = dependenceOfTUD;
  }

  public DependenceOfTUD GetDependenceOfTUD() {
    return mDependenceOfTUD;
  }

  private final DependenceOfTUD mDependenceOfTUD;
}
