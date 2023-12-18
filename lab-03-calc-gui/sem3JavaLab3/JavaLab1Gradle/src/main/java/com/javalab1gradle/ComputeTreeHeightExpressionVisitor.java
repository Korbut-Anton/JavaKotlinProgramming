package com.javalab1gradle;

public class ComputeTreeHeightExpressionVisitor implements ExpressionVisitor<Integer> {
  private ComputeTreeHeightExpressionVisitor() {
  }

  public static final ComputeTreeHeightExpressionVisitor INSTANCE =
          new ComputeTreeHeightExpressionVisitor();

  @Override
  public Integer visitBinaryExpression(BinaryExpression expr) {
    return Math.max(expr.getRight().accept(this),
            expr.getLeft().accept(this)) + 1;
  }

  @Override
  public Integer visitLiteral(Literal expr) {
    return 1;
  }

  @Override
  public Integer visitParenthesis(ParenthesisExpression expr) {
    return expr.getExpr().accept(this) + 1;
  }

  @Override
  public Integer visitVariable(Variable expr) {
    return 1;
  }
}
