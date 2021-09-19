package com.javalab1;

public class VariableImpl extends BaseExpression implements Variable {
  private final String mName;
  private boolean hasMinus = false;

  public VariableImpl(String inputString, BaseExpression parent) {
    mParent = parent;
    if (inputString.charAt(0) == '-') {
      hasMinus = true;
    }
    mName = String.valueOf(inputString.charAt(inputString.length() - 1));
  }

  @Override
  public String getName() {
    return mName;
  }

  @Override
  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitVariable(this);
  }

  @Override
  protected BaseExpression createRightChildIfPossible(Pair<String, ParserImpl.Lexeme> pair) {
    return null;
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
