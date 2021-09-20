package com.javalab1;

public class DebugRepresentationExpressionVisitor implements ExpressionVisitor {
  private DebugRepresentationExpressionVisitor() {
  }

  public static final DebugRepresentationExpressionVisitor INSTANCE =
          new DebugRepresentationExpressionVisitor();

  @Override
  public Object visitBinaryExpression(BinaryExpression expr) {
    return expr.getOperation().getShortNameOfOperation() + "(" + expr.getLeft().accept(this) + ","
            + expr.getRight().accept(this) + ")";
  }

  @Override
  public Object visitLiteral(Literal expr) {
    return "'" + expr.getValue() + "'";
  }

  @Override
  public Object visitParenthesis(ParenthesisExpression expr) {
    return (expr.hasMinus() ? "-" : "") + "paren-expr(" + expr.getExpr().accept(this) + ")";
  }

  @Override
  public Object visitVariable(Variable expr) {
    return (expr.hasMinus() ? "-" : "") + "var[" + expr.getName() + "]";
  }
}
