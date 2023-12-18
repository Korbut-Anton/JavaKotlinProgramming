package com.javalab2tests.ClassSets.ClassSet8;

import javax.inject.Inject;

public class BaseImpl implements BaseInterface {
  @Inject
  public BaseImpl(BaseDependence1Interface baseDependence1Interface,
                  BaseDependence2 baseDependence2) {
  }
}
