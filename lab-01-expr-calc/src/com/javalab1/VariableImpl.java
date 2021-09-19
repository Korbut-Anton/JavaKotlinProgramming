package com.javalab1;

public class VariableImpl extends BaseExpression implements Variable {
  private final String mName;
  private final boolean hasMinus;

  public VariableImpl(String inputString, BaseExpression parent) {
    mParent = parent;
    if (!inputString.isEmpty()) {
      hasMinus = inputString.charAt(0) == '-';
      mName = String.valueOf(inputString.charAt(inputString.length() - 1));
    } else {
      hasMinus = false;
      mName = "";
    }
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
