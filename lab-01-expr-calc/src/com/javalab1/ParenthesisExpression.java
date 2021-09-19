package com.javalab1;

public interface ParenthesisExpression extends Expression {
  Expression getExpr();

  boolean hasMinus();
}
