package com.javalab1;

public enum BinOpKind {
  ADDITION("add", "+") {
    @Override
    double performOperation(double first, double second) {
      return first + second;
    }
  },
  DIFFERENCE("sub", "-") {
    @Override
    double performOperation(double first, double second) {
      return first - second;
    }
  },
  MULTIPLICATION("mul", "*") {
    @Override
    double performOperation(double first, double second) {
      return first * second;
    }
  },
  DIVISION("div", "/") {
    @Override
    double performOperation(double first, double second) {
      return first / second;
    }
  };

  private final String mShortNameOfOperation;
  private final String mSymbolOfOperation;

  BinOpKind(String shortNameOfOperation, String symbolOfOperation) {
    mShortNameOfOperation = shortNameOfOperation;
    mSymbolOfOperation = symbolOfOperation;
  }

  double performOperation(double first, double second) {
    return 0;
  }

  String getShortNameOfOperation() {
    return mShortNameOfOperation;
  }

  String getSymbolOfOperation() {
    return mSymbolOfOperation;
  }
}
