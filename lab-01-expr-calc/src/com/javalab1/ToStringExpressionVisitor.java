package com.javalab1;

public class ToStringExpressionVisitor implements ExpressionVisitor {
  private ToStringExpressionVisitor() {
  }

  public static final ToStringExpressionVisitor INSTANCE = new ToStringExpressionVisitor();

  @Override
  public Object visitBinaryExpression(BinaryExpression expr) {
    return expr.getLeft().accept(this).toString() + expr.getOperation().getSymbolOfOperation() +
            expr.getRight().accept(this);
  }

  @Override
  public Object visitLiteral(Literal expr) {
    return String.valueOf(expr.getValue());
  }

  @Override
  public Object visitParenthesis(ParenthesisExpression expr) {
    return (expr.hasMinus() ? "-" : "") + "(" + expr.getExpr().accept(this) + ")";
  }

  @Override
  public Object visitVariable(Variable expr) {
    return (expr.hasMinus() ? "-" : "") + expr.getName();
  }
}
