package com.javalab2tests.ClassSets.ClassSet6;

import javax.inject.Inject;

public class DependentFromPair extends DependentFromPairParent {
  @Inject
  public DependentFromPair(DependenceFromPair dependenceFromPair) {
    super(dependenceFromPair);
  }
}
