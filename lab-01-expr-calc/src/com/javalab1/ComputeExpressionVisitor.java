package com.javalab1;

import java.util.HashMap;

public class ComputeExpressionVisitor implements ExpressionVisitor {
  private final HashMap<String, Double> mVariablesInfo;

  ComputeExpressionVisitor(HashMap<String, Double> variablesInfo) {
    mVariablesInfo = variablesInfo;
  }

  @Override
  public Object visitBinaryExpression(BinaryExpression expr) {
    return expr.getOperation().performOperation((double) expr.getLeft().accept(this),
            (double) expr.getRight().accept(this));
  }

  @Override
  public Object visitLiteral(Literal expr) {
    return expr.getValue();
  }

  @Override
  public Object visitParenthesis(ParenthesisExpression expr) {
    if (expr.hasMinus()) {
      return -(double) expr.getExpr().accept(this);
    }
    return expr.getExpr().accept(this);
  }

  @Override
  public Object visitVariable(Variable expr) {
    return expr.hasMinus() ? -mVariablesInfo.get(String.valueOf(expr.getName())) :
            mVariablesInfo.get(String.valueOf(expr.getName()));
  }
}
