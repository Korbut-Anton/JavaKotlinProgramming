package com.javalab1;

public class BinaryExpressionImpl extends BaseExpression implements BinaryExpression {
  private final BinOpKind mBinOpKind;

  public BinaryExpressionImpl(char inputChar, BaseExpression parent) {
    mParent = parent;
    switch (inputChar) {
      case '+' -> {
        mBinOpKind = BinOpKind.ADDITION;
      }
      case '-' -> {
        mBinOpKind = BinOpKind.DIFFERENCE;
      }
      case '*' -> {
        mBinOpKind = BinOpKind.MULTIPLICATION;
      }
      case '/' -> {
        mBinOpKind = BinOpKind.DIVISION;
      }
      default -> {
        mBinOpKind = null;
      }
    }
  }

  @Override
  public Object accept(ExpressionVisitor visitor) {
    return visitor.visitBinaryExpression(this);
  }

  @Override
  public Expression getLeft() {
    return mLeft;
  }

  @Override
  public Expression getRight() {
    return mRight;
  }

  @Override
  public BinOpKind getOperation() {
    return mBinOpKind;
  }
}