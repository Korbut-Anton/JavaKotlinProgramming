package com.javalab2tests.ClassSets.ClassSet7;

import javax.inject.Inject;

public class OuterClass {
  @Inject
  public OuterClass() {
  }

  public class MiddleClass {
    @Inject
    public MiddleClass(DependenceOfMiddleClass dependenceOfMiddleClass) {
    }

    public class InnerClass {
      @Inject
      public InnerClass(DependenceOfInnerClass dependenceOfInnerClass) {
      }
    }
  }
}
