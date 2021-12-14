package com.javalab1gradle;

public interface Expression {
  <T> T accept(ExpressionVisitor<T> visitor);
}
