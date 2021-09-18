package com.javalab1;

public interface BinaryExpression extends Expression {
  Expression getLeft();

  Expression getRight();

  BinOpKind getOperation();
}