package com.javalab1;

public class LiteralImpl extends BaseExpression implements Literal {
  private final double mValue;

  public LiteralImpl(String inputString, BaseExpression parent) {
    mParent = parent;
    mValue = Double.parseDouble(inputString);
  }

  @Override
  public double getValue() {
    return mValue;
  }

  @Override
  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitLiteral(this);
  }

  @Override
  protected BaseExpression createRightChildIfPossible(Pair<String, ParserImpl.Lexeme> pair) {
    return null;
  }

  @Override
  protected BaseExpression createLeftChildIfPossible(Pair<String, ParserImpl.Lexeme> pair) {
    return null;
  }
}