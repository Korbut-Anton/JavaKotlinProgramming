package com.javalab1;

public class ComputeTreeHeightExpressionVisitor implements ExpressionVisitor {
  private ComputeTreeHeightExpressionVisitor() {
  }

  public static final ComputeTreeHeightExpressionVisitor INSTANCE =
          new ComputeTreeHeightExpressionVisitor();

  @Override
  public Object visitBinaryExpression(BinaryExpression expr) {
    return Math.max((int) expr.getRight().accept(this),
            (int) expr.getLeft().accept(this)) + 1;
  }

  @Override
  public Object visitLiteral(Literal expr) {
    return 1;
  }

  @Override
  public Object visitParenthesis(ParenthesisExpression expr) {
    return (int) expr.getExpr().accept(this) + 1;
  }

  @Override
  public Object visitVariable(Variable expr) {
    return 1;
  }
}
