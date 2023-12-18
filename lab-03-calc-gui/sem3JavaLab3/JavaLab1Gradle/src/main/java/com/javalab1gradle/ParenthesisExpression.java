package com.javalab1gradle;

public interface ParenthesisExpression extends Expression {
  Expression getExpr();

  boolean hasMinus();
}
