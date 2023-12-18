package com.javalab1gradle;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
  public static void main(String[] args)
          throws ExpressionCreationException, ExpressionParseException {
    ParserImpl parser = new ParserImpl();
    System.out.println("Enter expression: ");
    Scanner scanner = new Scanner(System.in);
    Expression expression = parser.parseExpression(scanner.nextLine());
    System.out.print("Tree: ");
    System.out.println(expression.accept(DebugRepresentationExpressionVisitor.INSTANCE));
    System.out.print("Depth of tree: ");
    System.out.println(expression.accept(ComputeTreeHeightExpressionVisitor.INSTANCE));
    HashMap<String, Double> mapForVariables =
            expression.accept(new SetValuesForVariablesExpressionVisitor());
    System.out.print("Result: ");
    System.out.print(String.format("%8.7f", expression.
            accept(new ComputeExpressionVisitor(mapForVariables))).replace(',', '.'));
  }
}
