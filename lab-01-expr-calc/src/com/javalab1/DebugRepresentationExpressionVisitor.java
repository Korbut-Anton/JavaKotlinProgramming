package com.javalab1;

public class DebugRepresentationExpressionVisitor implements ExpressionVisitor<String> {
  private DebugRepresentationExpressionVisitor() {
  }

  public static final DebugRepresentationExpressionVisitor INSTANCE =
          new DebugRepresentationExpressionVisitor();

  @Override
  public String visitBinaryExpression(BinaryExpression expr) {
    return expr.getOperation().getShortNameOfOperation() + "(" + expr.getLeft().accept(this) + ","
            + expr.getRight().accept(this) + ")";
  }

  @Override
  public String visitLiteral(Literal expr) {
    return "'" + expr.getValue() + "'";
  }

  @Override
  public String visitParenthesis(ParenthesisExpression expr) {
    return (expr.hasMinus() ? "-" : "") + "paren-expr(" + expr.getExpr().accept(this) + ")";
  }

  @Override
  public String visitVariable(Variable expr) {
    return (expr.hasMinus() ? "-" : "") + "var[" + expr.getName() + "]";
  }
}
