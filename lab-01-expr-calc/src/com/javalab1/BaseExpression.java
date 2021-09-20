package com.javalab1;

public abstract class BaseExpression implements Expression {
  protected BaseExpression mParent = null;
  protected BaseExpression mLeft = null;
  protected BaseExpression mRight = null;

  protected BaseExpression createRightChildIfPossible(Pair<String, ParserImpl.Lexeme> pair)
          throws ExpressionCreationException {
    if (mRight != null) {
      return null;
    }
    switch (pair.getSecond()) {
      case NUMBER -> mRight = new LiteralImpl(pair.getFirst(), this);
      case VARIABLE -> mRight = new VariableImpl(pair.getFirst(), this);
      case BINARY_OPERATION -> mRight = new BinaryExpressionImpl(pair.getFirst().charAt(0), this);
      case OPENING_BRACKET -> mRight = new ParenthesisExpressionImpl(pair.getFirst(), this);
      default -> throw new ExpressionCreationException("Wrong token to create right child");
    }
    return mRight;
  }

  protected BaseExpression createLeftChildIfPossible(Pair<String, ParserImpl.Lexeme> pair)
          throws ExpressionCreationException {
    if (mLeft != null) {
      return null;
    }
    switch (pair.getSecond()) {
      case NUMBER -> mLeft = new LiteralImpl(pair.getFirst(), this);
      case VARIABLE -> mLeft = new VariableImpl(pair.getFirst(), this);
      case BINARY_OPERATION -> mLeft = new BinaryExpressionImpl(pair.getFirst().charAt(0), this);
      case OPENING_BRACKET -> mLeft = new ParenthesisExpressionImpl(pair.getFirst(), this);
      default -> throw new ExpressionCreationException("Wrong token to create left child");
    }
    return mLeft;
  }

  protected BaseExpression getParent() {
    return mParent;
  }
}
