package com.javalab1;

import java.util.ArrayList;
import java.util.Stack;

import static com.javalab1.Pair.createPair;

public class ParserImpl implements Parser {
  enum Lexeme {
    NUMBER,
    VARIABLE,
    BINARY_OPERATION,
    OPENING_BRACKET,
    CLOSING_BRACKET,
  }

  private boolean IsVariable(char symbol) {
    return (symbol >= 'a') && (symbol <= 'z');
  }

  private boolean IsDigit(char symbol) {
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
    } else if (IsDigit(curSymbol)) {
      curNumb.append(curSymbol);
    } else if (IsVariable(curSymbol)) {
      result.add(createPair(String.valueOf(curSymbol), Lexeme.VARIABLE));
    } else if (curSymbol == '(') {
      result.add(createPair(String.valueOf(curSymbol), Lexeme.OPENING_BRACKET));
      diffBetweenAmountsOfBrackets++;
    } else if (".*/)".contains(String.valueOf(curSymbol))) {
      throw new ExpressionParseException("Invalid order of symbols");
    } else {
      throw new ExpressionParseException("Invalid symbol");
    }
    if (inputString.length() == 1) {
      if (IsDigit(curSymbol)) {
        result.add(createPair(curNumb.toString(), Lexeme.NUMBER));
        return result;
      }
    }
    char prevSymbol = curSymbol;

    for (int i = 1; i < inputString.length(); i++) {
      curSymbol = inputString.charAt(i);
      if (!IsDigit(curSymbol) && (curSymbol != '.') && IsDigit(prevSymbol)) {
        result.add(createPair(curNumb.toString(), Lexeme.NUMBER));
        curNumb.delete(0, curNumb.length());
        curNumbPointState = false;
      }

      if (curSymbol == '*' || curSymbol == '/') {
        if (!IsDigit(prevSymbol) && !IsVariable(prevSymbol) && (prevSymbol != ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        result.add(createPair(String.valueOf(curSymbol), Lexeme.BINARY_OPERATION));
      } else if (IsDigit(curSymbol)) {
        if (IsVariable(prevSymbol) || (prevSymbol == ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        if (isUnaryOperation) {
          curNumb.append(prevSymbol);
          isUnaryOperation = false;
        }
        curNumb.append(curSymbol);
      } else if (curSymbol == '.') {
        if (!IsDigit(prevSymbol)) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        if (curNumbPointState) {
          throw new ExpressionParseException("More than one point in number");
        }
        curNumbPointState = true;
        curNumb.append(curSymbol);
      } else if (curSymbol == '(') {
        if ((prevSymbol == '.') || IsDigit(prevSymbol) ||
                IsVariable(prevSymbol) || (prevSymbol == ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        result.add(createPair(String.valueOf(curSymbol), Lexeme.OPENING_BRACKET));
        diffBetweenAmountsOfBrackets++;
      } else if (curSymbol == ')') {
        switch (prevSymbol) {
          case '.', '*', '/', '+', '-' -> throw new ExpressionParseException("Invalid order of symbols");
          case '(' -> throw new ExpressionParseException("Empty brackets");
          default -> {
            result.add(createPair(String.valueOf(curSymbol), Lexeme.CLOSING_BRACKET));
            diffBetweenAmountsOfBrackets--;
          }
        }
      } else if (IsVariable(curSymbol)) {
        if (IsVariable(prevSymbol) || IsDigit(prevSymbol) || (prevSymbol == '.') ||
                (prevSymbol == ')')) {
          throw new ExpressionParseException("Invalid order of symbols");
        }
        if (isUnaryOperation) {
          result.add(createPair(prevSymbol + String.valueOf(curSymbol),
                  Lexeme.VARIABLE));
          isUnaryOperation = false;
        } else {
          result.add(createPair(String.valueOf(curSymbol), Lexeme.VARIABLE));
        }
      } else if ((curSymbol == '+') || (curSymbol == '-')) {
        if (prevSymbol == '(') {
          isUnaryOperation = true;
        } else if (IsDigit(prevSymbol) || IsVariable(prevSymbol) ||
                (prevSymbol == ')')) {
          result.add(createPair(String.valueOf(curSymbol), Lexeme.BINARY_OPERATION));
        } else {
          throw new ExpressionParseException("Invalid order of symbols");
        }
      } else {
        throw new ExpressionParseException("Invalid symbol");
      }
      prevSymbol = curSymbol;
    }

    if (IsDigit(curSymbol)) {
      result.add(createPair(curNumb.toString(), Lexeme.NUMBER));
    } else if (!IsVariable(curSymbol) && (curSymbol != ')')) {
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

  private Expression MakeTree(ArrayList<Pair<String, Lexeme>> inputList) throws Exception {
    ParenthesisExpressionImpl starter = new ParenthesisExpressionImpl(null);
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
  public Expression parseExpression(String input) throws Exception {
    ArrayList<Pair<String, Lexeme>> resultList = divideToTokens(input);
    convertToReversePolishNotation(resultList);
    return MakeTree(resultList);
  }
}
