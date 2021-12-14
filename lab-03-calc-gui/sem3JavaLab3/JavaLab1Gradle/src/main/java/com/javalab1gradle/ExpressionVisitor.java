package com.javalab1gradle;

public interface ExpressionVisitor<T> {
  T visitBinaryExpression(BinaryExpression expr);

  T visitLiteral(Literal expr);

  T visitParenthesis(ParenthesisExpression expr);

  T visitVariable(Variable expr);
}
