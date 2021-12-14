package com.javalab1gradle;

import java.util.HashMap;

public class ComputeExpressionVisitor implements ExpressionVisitor<Double> {
  private final HashMap<String, Double> mVariablesInfo;

  public ComputeExpressionVisitor(HashMap<String, Double> variablesInfo) {
    mVariablesInfo = variablesInfo;
  }

  @Override
  public Double visitBinaryExpression(BinaryExpression expr) {
    return expr.getOperation().performOperation(expr.getLeft().accept(this),
            expr.getRight().accept(this));
  }

  @Override
  public Double visitLiteral(Literal expr) {
    return expr.getValue();
  }

  @Override
  public Double visitParenthesis(ParenthesisExpression expr) {
    if (expr.hasMinus()) {
      return -expr.getExpr().accept(this);
    }
    return expr.getExpr().accept(this);
  }

  @Override
  public Double visitVariable(Variable expr) {
    return expr.hasMinus() ? -mVariablesInfo.get(String.valueOf(expr.getName())) :
            mVariablesInfo.get(String.valueOf(expr.getName()));
  }
}
