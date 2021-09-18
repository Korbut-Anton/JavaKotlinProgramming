package com.javalab1;

import java.util.HashMap;
import java.util.Scanner;

public class SetValuesForVariablesExpressionVisitor implements ExpressionVisitor {
  private final HashMap<String, Double> result = new HashMap<>();

  @Override
  public Object visitBinaryExpression(BinaryExpression expr) {
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return result;
  }

  @Override
  public Object visitLiteral(Literal expr) {
    return result;
  }

  @Override
  public Object visitParenthesis(ParenthesisExpression expr) {
    expr.getExpr().accept(this);
    return result;
  }

  @Override
  public Object visitVariable(Variable expr) {
    Scanner scanner = new Scanner(System.in);
    if (result.containsKey(String.valueOf(expr.getName()))) {
      return result;
    }
    System.out.println(new StringBuilder("Enter value of var ").append(expr.getName()));
    if (scanner.hasNextLine()) {
      result.put(String.valueOf(expr.getName()),
              Double.valueOf(scanner.next()));
    }
    return result;
  }
}