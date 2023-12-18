package com.javalab2tests.ClassSets.ClassSet6;

import javax.inject.Inject;

public class SeparatedSingleClass extends SeparatedSingleClassParent {
  @Inject
  public SeparatedSingleClass() {
    super();
  }

  public SeparatedSingleClass(Integer... args) {
  }
}
