package com.javalab1;

public class ParenthesisExpressionImpl extends BaseExpression implements ParenthesisExpression {
  private final boolean hasMinus;

  public ParenthesisExpressionImpl(String inputString, BaseExpression parent) {
    mParent = parent;
    hasMinus = !inputString.isEmpty() && inputString.charAt(0) == '-';
  }

  @Override
  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitParenthesis(this);
  }

  @Override
  public Expression getExpr() {
    return mRight;
  }

  @Override
  protected BaseExpression createLeftChildIfPossible(Pair<String, ParserImpl.Lexeme> pair) {
    return null;
  }

  @Override
  public boolean hasMinus() {
    return hasMinus;
  }
}
