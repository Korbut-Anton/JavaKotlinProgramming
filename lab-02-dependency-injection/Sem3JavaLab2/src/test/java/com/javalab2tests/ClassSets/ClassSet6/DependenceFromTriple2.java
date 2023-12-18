package com.javalab2tests.ClassSets.ClassSet6;

import javax.inject.Inject;

public class DependenceFromTriple2 {
  @Inject
  public DependenceFromTriple2() {
  }

  public DependenceFromTriple2(DependenceFromTriple1 dependenceFromTriple1) {
  }

  public DependenceFromTriple2(int intVar, double doubleVar, String... args) {
  }
}
