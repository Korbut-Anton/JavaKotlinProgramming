package com.javalab1;

public class ParenthesisExpressionImpl extends BaseExpression implements ParenthesisExpression {
  public ParenthesisExpressionImpl(BaseExpression parent) {
    mParent = parent;
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
}
