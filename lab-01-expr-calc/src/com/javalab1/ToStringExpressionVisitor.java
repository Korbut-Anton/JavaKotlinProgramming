package com.javalab1;

public class ToStringExpressionVisitor implements ExpressionVisitor {
  private ToStringExpressionVisitor() {
  }

  public static final ToStringExpressionVisitor INSTANCE = new ToStringExpressionVisitor();

  @Override
  public Object visitBinaryExpression(BinaryExpression expr) {
    return new StringBuilder(expr.getLeft().accept(this).toString()).
            append(expr.getOperation().getSymbolOfOperation()).
            append(expr.getRight().accept(this));
  }

  @Override
  public Object visitLiteral(Literal expr) {
    return String.valueOf(expr.getValue());
  }

  @Override
  public Object visitParenthesis(ParenthesisExpression expr) {
    return new StringBuilder(expr.hasMinus() ? "-" : "").append("(").
            append(expr.getExpr().accept(this)).append(")");
  }

  @Override
  public Object visitVariable(Variable expr) {
    return new StringBuilder(expr.hasMinus() ? "-" : "").append(expr.getName());
  }
}
