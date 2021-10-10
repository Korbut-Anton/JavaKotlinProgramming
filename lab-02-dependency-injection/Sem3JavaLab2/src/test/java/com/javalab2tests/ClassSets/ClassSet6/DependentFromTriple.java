package com.javalab2tests.ClassSets.ClassSet6;

import javax.inject.Inject;

public class DependentFromTriple {
  @Inject
  public DependentFromTriple(DependenceFromTriple1 dependenceFromTriple1,
                             DependenceFromTriple2 dependenceFromTriple2) {
  }
}
