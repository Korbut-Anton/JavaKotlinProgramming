package com.javalab1;

public enum BinOpKind {
  ADDITION {
    @Override
    double performOperation(double first, double second) {
      return first + second;
    }

    @Override
    String getShortNameOfOperation() {
      return "add";
    }

    @Override
    String getSymbolOfOperation() {
      return "+";
    }
  },
  DIFFERENCE {
    @Override
    double performOperation(double first, double second) {
      return first - second;
    }

    @Override
    String getShortNameOfOperation() {
      return "sub";
    }

    @Override
    String getSymbolOfOperation() {
      return "-";
    }
  },
  MULTIPLICATION {
    @Override
    double performOperation(double first, double second) {
      return first * second;
    }

    @Override
    String getShortNameOfOperation() {
      return "mul";
    }

    @Override
    String getSymbolOfOperation() {
      return "*";
    }
  },
  DIVISION {
    @Override
    double performOperation(double first, double second) {
      return first / second;
    }

    @Override
    String getShortNameOfOperation() {
      return "div";
    }

    @Override
    String getSymbolOfOperation() {
      return "/";
    }
  };

  double performOperation(double first, double second) {
    return 0;
  }

  String getShortNameOfOperation() {
    return null;
  }

  String getSymbolOfOperation() {
    return null;
  }
}