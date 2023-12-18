package com.javalab1gradle;

import java.util.ArrayList;
import java.util.Stack;

public class ParserImpl implements Parser {
  enum Lexeme {
    NUMBER,
    VARIABLE,
    BINARY_OPERATION,
    OPENING_BRACKET,
    CLOSING_BRACKET,
  }

  private boolean isVariable(char symbol) {
    return (symbol >= 'a') && (symbol <= 'z');
  }

  private boolean isDigit(char symbol) {
    return (symbol >= '0') && (symbol <= '9');
  }

  private ArrayList<Pair<String, Lexeme>> divideToTokens(String inputString)
          throws ExpressionParseException {
    inputString = inputString.replaceAll(" ", "");
    if (inputString.length() == 0) {
      throw new ExpressionParseException("Empty string");
    }

    ArrayList<Pair<String, Lexeme>> result = new ArrayList<>();
    char curSymbol = inputString.charAt(0);
    StringBuilder curNumb = new StringBuilder();
    boolean curNumbPointState = false;
    boolean isUnaryOperation = false;
    int diffBetweenAmountsOfBrackets = 0;

    if ((curSymbol == '-') || (curSymbol == '+')) {
      isUnaryOperation = true;
    } else if (isDigit(curSymbol)) {
      curNumb.append(curSymbol);
    } else if (isVariable(curSymbol)) {
      result.add(new Pair<>(String.valueOf(curSymbol), Lexeme.VARIABLE));
    } else if (curSymbol == '(') {
      result.add(new Pair<>(String.valueOf(curSymbol), Lexeme.OPENING_BRACKET));
      diffBetweenAmountsOfBrackets++;
    } else if (".*/)".contains(String.valueOf(curSymbol))) {
      throw new ExpressionParseException("Invalid order of symbols");
    } else {
      throw new ExpressionParseException("Invalid symbol");
    }
    if (inputString.length() == 1) {
      if (isDigit(curSymbol)) {
        result.add(new Pair<>(curNumb.toString(), Lexeme.NUMBER));
        return result;
      }
    }
    char prevSymbol = curSymbol;

    for (int i = 1; i < inputString.length(); i++) {
      curSymbol = inputString.charAt(i);
      if (!isDigit(curSymbol) && (curSymbol != '.') && isDigit(prevSymbol)) {
        result.add(new Pair<>(curNumb.toString(), Lexeme.NUMBER));
        curNumb.delete(0, curNumb.length());
        curNumbPointState = false;
      }

      if (curSymbol == '*' || curSymbol == '/') {
        if (!isDigit(prevSymbol) && !isVariable(prevSymbol) && (prevSymbol != ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        result.add(new Pair<>(String.valueOf(curSymbol), Lexeme.BINARY_OPERATION));
      } else if (isDigit(curSymbol)) {
        if (isVariable(prevSymbol) || (prevSymbol == ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        if (isUnaryOperation) {
          curNumb.append(prevSymbol);
          isUnaryOperation = false;
        }
        curNumb.append(curSymbol);
      } else if (curSymbol == '.') {
        if (!isDigit(prevSymbol)) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        if (curNumbPointState) {
          throw new ExpressionParseException("More than one point in number");
        }
        curNumbPointState = true;
        curNumb.append(curSymbol);
      } else if (curSymbol == '(') {
        if ((prevSymbol == '.') || isDigit(prevSymbol) ||
                isVariable(prevSymbol) || (prevSymbol == ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        if (isUnaryOperation) {
          result.add(new Pair<>(prevSymbol + String.valueOf(curSymbol), Lexeme.OPENING_BRACKET));
          isUnaryOperation = false;
        } else {
          result.add(new Pair<>(String.valueOf(curSymbol), Lexeme.OPENING_BRACKET));
        }
        diffBetweenAmountsOfBrackets++;
      } else if (curSymbol == ')') {
        switch (prevSymbol) {
          case '.', '*', '/', '+', '-' -> throw new
                  ExpressionParseException("Invalid order of symbols");
          case '(' -> throw new ExpressionParseException("Empty brackets");
          default -> {
            result.add(new Pair<>(String.valueOf(curSymbol), Lexeme.CLOSING_BRACKET));
            diffBetweenAmountsOfBrackets--;
          }
        }
      } else if (isVariable(curSymbol)) {
        if (isVariable(prevSymbol) || isDigit(prevSymbol) || (prevSymbol == '.') ||
                (prevSymbol == ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        if (isUnaryOperation) {
          result.add(new Pair<>(prevSymbol + String.valueOf(curSymbol),
                  Lexeme.VARIABLE));
          isUnaryOperation = false;
        } else {
          result.add(new Pair<>(String.valueOf(curSymbol), Lexeme.VARIABLE));
        }
      } else if ((curSymbol == '+') || (curSymbol == '-')) {
        if (prevSymbol == '(') {
          isUnaryOperation = true;
        } else if (isDigit(prevSymbol) || isVariable(prevSymbol) ||
                (prevSymbol == ')')) {
          result.add(new Pair<>(String.valueOf(curSymbol), Lexeme.BINARY_OPERATION));
        } else {
          throw new ExpressionParseException("Invalid order of symbols");
        }
      } else {
        throw new ExpressionParseException("Invalid symbol");
      }
      prevSymbol = curSymbol;
    }

    if (isDigit(curSymbol)) {
      result.add(new Pair<>(curNumb.toString(), Lexeme.NUMBER));
    } else if (!isVariable(curSymbol) && (curSymbol != ')')) {
      throw new ExpressionParseException("Invalid last symbol");
    }
    if (diffBetweenAmountsOfBrackets != 0) {
      throw new ExpressionParseException("Incorrect placement of brackets");
    }
    return result;
  }

  private void convertToReversePolishNotation(ArrayList<Pair<String, Lexeme>> inputList) {
    Stack<Pair<String, Lexeme>> stack = new Stack<>();
    int j = 0;
    for (int i = 0; i < inputList.size(); i++) {
      if ((inputList.get(i).getSecond() == Lexeme.NUMBER) ||
              (inputList.get(i).getSecond() == Lexeme.VARIABLE)) {
        inputList.set(j, inputList.get(i));
        j++;
      } else if (inputList.get(i).getSecond() == Lexeme.OPENING_BRACKET) {
        stack.push(inputList.get(i));
      } else if (inputList.get(i).getSecond() == Lexeme.CLOSING_BRACKET) {
        while (stack.peek().getSecond() != Lexeme.OPENING_BRACKET) {
          inputList.set(j, stack.pop());
          j++;
        }
        inputList.set(j, stack.pop());
        j++;
      } else {
        if ("*/".contains(inputList.get(i).getFirst())) {
          while (!stack.isEmpty() && "*/".contains(stack.peek().getFirst())) {
            inputList.set(j, stack.pop());
            j++;
          }
        } else {
          while (!stack.isEmpty() && "+-*/".contains(stack.peek().getFirst())) {
            inputList.set(j, stack.pop());
            j++;
          }
        }
        stack.push(inputList.get(i));
      }
    }
    while (!stack.isEmpty()) {
      inputList.set(j, stack.pop());
      j++;
    }
    inputList.subList(j, inputList.size()).clear();
  }

  private Expression makeTree(ArrayList<Pair<String, Lexeme>> inputList)
          throws ExpressionCreationException {
    ParenthesisExpressionImpl starter = new ParenthesisExpressionImpl("", null);
    BaseExpression curExpr = starter;
    for (int i = inputList.size() - 1; i >= 0; i--) {
      BaseExpression newExpr;
      while (true) {
        newExpr = curExpr.createRightChildIfPossible(inputList.get(i));
        if (newExpr == null) {
          newExpr = curExpr.createLeftChildIfPossible(inputList.get(i));
        }
        if (newExpr == null) {
          curExpr = curExpr.getParent();
        } else {
          curExpr = newExpr;
          break;
        }
      }
    }
    return starter.getExpr();
  }

  @Override
  public Expression parseExpression(String input)
          throws ExpressionParseException, ExpressionCreationException {
    ArrayList<Pair<String, Lexeme>> resultList = divideToTokens(input);
    convertToReversePolishNotation(resultList);
    return makeTree(resultList);
  }
}
