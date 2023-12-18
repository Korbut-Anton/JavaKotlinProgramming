package com.javalab2tests.ClassSets.ClassSet5;

import javax.inject.Inject;

public class X {
  @Inject
  public X(DependentOnTUD1 dependentOnTUD1, DependentOnTUD2 dependentOnTUD2) {
    mDependentOnTUD1 = dependentOnTUD1;
    mDependentOnTUD2 = dependentOnTUD2;
  }

  public DependentOnTUD1 GetDeDependentOnTUD1() {
    return mDependentOnTUD1;
  }

  public DependentOnTUD2 GetDeDependentOnTUD2() {
    return mDependentOnTUD2;
  }

  private final DependentOnTUD1 mDependentOnTUD1;
  private final DependentOnTUD2 mDependentOnTUD2;
}
