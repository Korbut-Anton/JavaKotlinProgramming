package com.javalab1;

import java.util.HashMap;

public class Tests {
  public static final double eps = 1e-7;

  public static boolean doubleValuesAreEqual(double first, double second, double eps) {
    return Math.abs(first - second) <= eps;
  }

  public static void main(String[] args)
          throws ExpressionParseException, ExpressionCreationException {
    ParserImpl parser = new ParserImpl();
    HashMap<String, Double> ValuesOfVariables = new HashMap<>();
    String str1 = "5.0";
    Expression expr1 = parser.parseExpression(str1);
    String str2 = "-7.11";
    Expression expr2 = parser.parseExpression(str2);
    String str3 = "(9.999)";
    Expression expr3 = parser.parseExpression(str3);
    String str4 = "(-1.32)";
    Expression expr4 = parser.parseExpression(str4);
    String str5 = "+8.1";
    Expression expr5 = parser.parseExpression(str5);
    String str6 = "(+19.0)";
    Expression expr6 = parser.parseExpression(str6);
    String str7 = "(((-1019.005)))";
    Expression expr7 = parser.parseExpression(str7);
    String str8 = "123.0/100.0";
    Expression expr8 = parser.parseExpression(str8);
    String str9 = "5+7.0";
    Expression expr9 = parser.parseExpression(str9);
    String str10 = "-9.99-1.0";
    Expression expr10 = parser.parseExpression(str10);
    String str11 = "5.0+(-2.3*2.0)";
    Expression expr11 = parser.parseExpression(str11);
    String str12 = " ((5.0  / (-1.0)   ))/2.5";
    Expression expr12 = parser.parseExpression(str12);
    String str13 = "1.0+(2.0+3.0+4.0)+5.0+6.0+7.0*(5.3-4.21902)";
    Expression expr13 = parser.parseExpression(str13);
    String str14 = "-(-9.11)";
    Expression expr14 = parser.parseExpression(str14);
    String str15 = "x";
    Expression expr15 = parser.parseExpression(str15);
    String str16 = "-y";
    Expression expr16 = parser.parseExpression(str16);
    String str17 = "(-a)+(-a)-r";
    Expression expr17 = parser.parseExpression(str17);
    String str18 = "+s-(5.8+(+q))/s-m";
    Expression expr18 = parser.parseExpression(str18);
    String str19 = "+((x+y)+5.2)/(-(x*2.0)-(+(-w*w*w)))";
    Expression expr19 = parser.parseExpression(str19);

    assert (expr1.accept(DebugRepresentationExpressionVisitor.INSTANCE).equals("'5.0'"));
    assert (expr2.accept(DebugRepresentationExpressionVisitor.INSTANCE).equals("'-7.11'"));
    assert (expr3.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("paren-expr('9.999')"));
    assert (expr4.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("paren-expr('-1.32')"));
    assert (expr5.accept(DebugRepresentationExpressionVisitor.INSTANCE).equals("'8.1'"));
    assert (expr6.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("paren-expr('19.0')"));
    assert (expr7.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("paren-expr(paren-expr(paren-expr('-1019.005')))"));
    assert (expr8.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("div('123.0','100.0')"));
    assert (expr9.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("add('5.0','7.0')"));
    assert (expr10.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("sub('-9.99','1.0')"));
    assert (expr11.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("add('5.0',paren-expr(mul('-2.3','2.0')))"));
    assert (expr12.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("div(paren-expr(paren-expr(div('5.0',paren-expr('-1.0')))),'2.5')"));
    assert (expr13.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("add(add(add(add('1.0',paren-expr(add(add('2.0','3.0'),'4.0'))),'5.0'),'6.0')" +
                    ",mul('7.0',paren-expr(sub('5.3','4.21902'))))"));
    assert (expr14.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("-paren-expr('-9.11')"));
    assert (expr15.accept(DebugRepresentationExpressionVisitor.INSTANCE).equals("var[x]"));
    assert (expr16.accept(DebugRepresentationExpressionVisitor.INSTANCE).equals("-var[y]"));
    assert (expr17.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("sub(add(paren-expr(-var[a]),paren-expr(-var[a])),var[r])"));
    assert (expr18.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("sub(sub(var[s],div(paren-expr(add('5.8',paren-expr(var[q]))),var[s]))," +
                    "var[m])"));
    assert (expr19.accept(DebugRepresentationExpressionVisitor.INSTANCE).
            equals("div(paren-expr(add(paren-expr(add(var[x],var[y])),'5.2'))," +
                    "paren-expr(sub(-paren-expr(mul(var[x],'2.0'))," +
                    "paren-expr(paren-expr(mul(mul(-var[w],var[w]),var[w]))))))"));

    assert (expr1.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert (expr2.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert (expr3.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert (expr4.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert (expr5.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert (expr6.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert (expr7.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 4);
    assert (expr8.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert (expr9.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert (expr10.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert (expr11.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 4);
    assert (expr12.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 6);
    assert (expr13.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 8);
    assert (expr14.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert (expr15.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert (expr16.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert (expr17.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 4);
    assert (expr18.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 7);
    assert (expr19.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 8);

    assert (expr1.accept(ToStringExpressionVisitor.INSTANCE).equals(str1));
    assert (expr2.accept(ToStringExpressionVisitor.INSTANCE).equals(str2));
    assert (expr3.accept(ToStringExpressionVisitor.INSTANCE).equals(str3));
    assert (expr4.accept(ToStringExpressionVisitor.INSTANCE).equals(str4));
    assert (expr5.accept(ToStringExpressionVisitor.INSTANCE).equals("8.1"));
    assert (expr6.accept(ToStringExpressionVisitor.INSTANCE).equals("(19.0)"));
    assert (expr7.accept(ToStringExpressionVisitor.INSTANCE).equals(str7));
    assert (expr8.accept(ToStringExpressionVisitor.INSTANCE).equals(str8));
    assert (expr9.accept(ToStringExpressionVisitor.INSTANCE).equals("5.0+7.0"));
    assert (expr10.accept(ToStringExpressionVisitor.INSTANCE).equals(str10));
    assert (expr11.accept(ToStringExpressionVisitor.INSTANCE).equals(str11));
    assert (expr12.accept(ToStringExpressionVisitor.INSTANCE).equals("((5.0/(-1.0)))/2.5"));
    assert (expr13.accept(ToStringExpressionVisitor.INSTANCE).equals(str13));
    assert (expr14.accept(ToStringExpressionVisitor.INSTANCE).equals(str14));
    assert (expr15.accept(ToStringExpressionVisitor.INSTANCE).equals(str15));
    assert (expr16.accept(ToStringExpressionVisitor.INSTANCE).equals(str16));
    assert (expr17.accept(ToStringExpressionVisitor.INSTANCE).equals(str17));
    assert (expr18.accept(ToStringExpressionVisitor.INSTANCE).equals("s-(5.8+(q))/s-m"));
    assert (expr19.accept(ToStringExpressionVisitor.INSTANCE).
            equals("((x+y)+5.2)/(-(x*2.0)-((-w*w*w)))"));

    assert (doubleValuesAreEqual(
            expr1.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 5.0, eps));
    assert (doubleValuesAreEqual(
            expr2.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -7.11, eps));
    assert (doubleValuesAreEqual(
            expr3.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 9.999, eps));
    assert (doubleValuesAreEqual(
            expr4.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -1.32, eps));
    assert (doubleValuesAreEqual(
            expr5.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 8.1, eps));
    assert (doubleValuesAreEqual(
            expr6.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 19.0, eps));
    assert (doubleValuesAreEqual(
            expr7.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -1019.005, eps));
    assert (doubleValuesAreEqual(
            expr8.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 1.23, eps));
    assert (doubleValuesAreEqual(
            expr9.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 12.0, eps));
    assert (doubleValuesAreEqual(
            expr10.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -10.99, eps));
    assert (doubleValuesAreEqual(
            expr11.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 0.4, eps));
    assert (doubleValuesAreEqual(
            expr12.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -2.0, eps));
    assert (doubleValuesAreEqual(
            expr13.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 28.56686, eps));
    assert (doubleValuesAreEqual(
            expr14.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 9.11, eps));
    ValuesOfVariables.put("x", 8.0);
    assert (doubleValuesAreEqual(
            expr15.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 8.0, eps));
    ValuesOfVariables.put("x", 0.0);
    assert (doubleValuesAreEqual(
            expr15.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 0.0, eps));
    ValuesOfVariables.put("y", -88.018);
    assert (doubleValuesAreEqual(
            expr16.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 88.018, eps));
    ValuesOfVariables.put("y", 53.91);
    assert (doubleValuesAreEqual(
            expr16.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -53.91, eps));
    ValuesOfVariables.put("a", -9.0);
    ValuesOfVariables.put("r", 13.1);
    assert (doubleValuesAreEqual(
            expr17.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 4.9, eps));
    ValuesOfVariables.put("a", 68.223);
    ValuesOfVariables.put("r", -200.8);
    assert (doubleValuesAreEqual(
            expr17.accept(new ComputeExpressionVisitor(ValuesOfVariables)), 64.354, eps));
    ValuesOfVariables.put("s", -1.0);
    ValuesOfVariables.put("q", 0.77);
    ValuesOfVariables.put("m", 308.0);
    assert (doubleValuesAreEqual(
            expr18.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -302.43, eps));
    ValuesOfVariables.put("s", -19.84);
    ValuesOfVariables.put("q", 30.03);
    ValuesOfVariables.put("m", -10.0);
    assert (doubleValuesAreEqual(
            expr18.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -8.0340524, eps));
    ValuesOfVariables.put("x", 205.0);
    ValuesOfVariables.put("y", 9.54354);
    ValuesOfVariables.put("w", -0.03);
    assert (doubleValuesAreEqual(
            expr19.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -0.5359598889, eps));
    ValuesOfVariables.put("x", 1.0);
    ValuesOfVariables.put("y", 0.0);
    ValuesOfVariables.put("w", 1.0);
    assert (doubleValuesAreEqual(
            expr19.accept(new ComputeExpressionVisitor(ValuesOfVariables)), -6.2, eps));
  }
}
