package com.javalab2tests.ClassSets.ClassSet10;

import javax.inject.Inject;

public class NoPublicInjectConstructorsClass {
  public NoPublicInjectConstructorsClass(ManyInjectConstructorsClass1
                                                 manyInjectConstructorsClass1) {
  }

  @Inject
  private NoPublicInjectConstructorsClass() {
  }
}
