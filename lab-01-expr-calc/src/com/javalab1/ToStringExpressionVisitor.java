package com.javalab1;

public class ToStringExpressionVisitor implements ExpressionVisitor<String> {
  private ToStringExpressionVisitor() {
  }

  public static final ToStringExpressionVisitor INSTANCE = new ToStringExpressionVisitor();

  @Override
  public String visitBinaryExpression(BinaryExpression expr) {
    return expr.getLeft().accept(this) + expr.getOperation().getSymbolOfOperation() +
            expr.getRight().accept(this);
  }

  @Override
  public String visitLiteral(Literal expr) {
    return String.valueOf(expr.getValue());
  }

  @Override
  public String visitParenthesis(ParenthesisExpression expr) {
    return (expr.hasMinus() ? "-" : "") + "(" + expr.getExpr().accept(this) + ")";
  }

  @Override
  public String visitVariable(Variable expr) {
    return (expr.hasMinus() ? "-" : "") + expr.getName();
  }
}
