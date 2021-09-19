package com.javalab1;

public class DebugRepresentationExpressionVisitor implements ExpressionVisitor {
  private DebugRepresentationExpressionVisitor() {
  }

  public static final DebugRepresentationExpressionVisitor INSTANCE =
          new DebugRepresentationExpressionVisitor();

  @Override
  public Object visitBinaryExpression(BinaryExpression expr) {
    return new StringBuilder(expr.getOperation().getShortNameOfOperation()).append("(").
            append(expr.getLeft().accept(this)).append(",").
            append(expr.getRight().accept(this)).append(")");
  }

  @Override
  public Object visitLiteral(Literal expr) {
    return new StringBuilder("'").append(expr.getValue()).append("'");
  }

  @Override
  public Object visitParenthesis(ParenthesisExpression expr) {
    return new StringBuilder(expr.hasMinus() ? "-" : "").append("paren-expr(").
            append(expr.getExpr().accept(this)).append(")");
  }

  @Override
  public Object visitVariable(Variable expr) {
    return new StringBuilder(expr.hasMinus() ? "-" : "").append("var[").append(expr.getName()).
            append("]");
  }
}
