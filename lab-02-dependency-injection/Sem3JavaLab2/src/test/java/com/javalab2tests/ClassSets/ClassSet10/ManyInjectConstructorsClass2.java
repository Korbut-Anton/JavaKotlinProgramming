package com.javalab2tests.ClassSets.ClassSet10;

import javax.inject.Inject;

public class ManyInjectConstructorsClass2 {
  @Inject
  public ManyInjectConstructorsClass2(ManyInjectConstructorsClass1 manyInjectConstructorsClass1) {
  }

  @Inject
  private ManyInjectConstructorsClass2() {
  }

  @Inject
  private ManyInjectConstructorsClass2(String str) {
  }
}
