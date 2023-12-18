package com.javalab1gradle;

public interface BinaryExpression extends Expression {
  Expression getLeft();

  Expression getRight();

  BinOpKind getOperation();
}
