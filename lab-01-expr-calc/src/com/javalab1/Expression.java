package com.javalab1;

public interface Expression {
  <T> T accept(ExpressionVisitor<T> visitor);
}
