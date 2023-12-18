package com.javalab1gradle;

import java.util.HashMap;
import java.util.Scanner;

public class SetValuesForVariablesExpressionVisitor
        implements ExpressionVisitor<HashMap<String, Double>> {
  private final HashMap<String, Double> result = new HashMap<>();

  @Override
  public HashMap<String, Double> visitBinaryExpression(BinaryExpression expr) {
    expr.getLeft().accept(this);
    expr.getRight().accept(this);
    return result;
  }

  @Override
  public HashMap<String, Double> visitLiteral(Literal expr) {
    return result;
  }

  @Override
  public HashMap<String, Double> visitParenthesis(ParenthesisExpression expr) {
    expr.getExpr().accept(this);
    return result;
  }

  @Override
  public HashMap<String, Double> visitVariable(Variable expr) {
    Scanner scanner = new Scanner(System.in);
    if (result.containsKey(String.valueOf(expr.getName()))) {
      return result;
    }
    System.out.println("Enter value of var " + expr.getName());
    if (scanner.hasNextLine()) {
      result.put(String.valueOf(expr.getName()),
              Double.valueOf(scanner.next()));
    }
    return result;
  }
}
