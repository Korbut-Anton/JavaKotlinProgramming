package com.javalab1;

public interface Expression {
  Object accept(ExpressionVisitor visitor);
}