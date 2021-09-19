package com.javalab1;

import java.util.HashMap;

public class Tests {
  public static final double eps = 1e-7;

  public static void main(String[] args) throws Exception {
    ParserImpl parser = new ParserImpl();
    HashMap<String, Double> hashMap = new HashMap<>();
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

    String str14 = "x";
    Expression expr14 = parser.parseExpression(str14);
    String str15 = "-y";
    Expression expr15 = parser.parseExpression(str15);
    String str16 = "(-a)+(-a)-r";
    Expression expr16 = parser.parseExpression(str16);
    String str17 = "+s-(5.8+(+q))/s-m";
    Expression expr17 = parser.parseExpression(str17);

    assert (expr1.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().equals("'5.0'"));
    assert (expr2.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("'-7.11'"));
    assert (expr3.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("paren-expr('9.999')"));
    assert (expr4.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("paren-expr('-1.32')"));
    assert (expr5.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().equals("'8.1'"));
    assert (expr6.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("paren-expr('19.0')"));
    assert (expr7.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("paren-expr(paren-expr(paren-expr('-1019.005')))"));
    assert (expr8.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("div('123.0','100.0')"));
    assert (expr9.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("add('5.0','7.0')"));
    assert (expr10.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("sub('-9.99','1.0')"));
    assert (expr11.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("add('5.0',paren-expr(mul('-2.3','2.0')))"));
    assert (expr12.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("div(paren-expr(paren-expr(div('5.0',paren-expr('-1.0')))),'2.5')"));
    assert (expr13.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("add(add(add(add('1.0',paren-expr(add(add('2.0','3.0'),'4.0'))),'5.0'),'6.0')" +
                    ",mul('7.0',paren-expr(sub('5.3','4.21902'))))"));
    assert (expr14.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("var[x]"));
    assert (expr15.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("-var[y]"));
    assert (expr16.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("sub(add(paren-expr(-var[a]),paren-expr(-var[a])),var[r])"));
    assert (expr17.accept(DebugRepresentationExpressionVisitor.INSTANCE).toString().
            equals("sub(sub(var[s],div(paren-expr(add('5.8',paren-expr(var[q]))),var[s]))," +
                    "var[m])"));

    assert ((int) expr1.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert ((int) expr2.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert ((int) expr3.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert ((int) expr4.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert ((int) expr5.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert ((int) expr6.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert ((int) expr7.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 4);
    assert ((int) expr8.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert ((int) expr9.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert ((int) expr10.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 2);
    assert ((int) expr11.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 4);
    assert ((int) expr12.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 6);
    assert ((int) expr13.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 8);
    assert ((int) expr14.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert ((int) expr15.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 1);
    assert ((int) expr16.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 4);
    assert ((int) expr17.accept(ComputeTreeHeightExpressionVisitor.INSTANCE) == 7);

    assert (expr1.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str1));
    assert (expr2.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str2));
    assert (expr3.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str3));
    assert (expr4.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str4));
    assert (expr5.accept(ToStringExpressionVisitor.INSTANCE).toString().equals("8.1"));
    assert (expr6.accept(ToStringExpressionVisitor.INSTANCE).toString().equals("(19.0)"));
    assert (expr7.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str7));
    assert (expr8.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str8));
    assert (expr9.accept(ToStringExpressionVisitor.INSTANCE).toString().equals("5.0+7.0"));
    assert (expr10.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str10));
    assert (expr11.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str11));
    assert (expr12.accept(ToStringExpressionVisitor.INSTANCE).toString().
            equals("((5.0/(-1.0)))/2.5"));
    assert (expr13.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str13));
    assert (expr14.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str14));
    assert (expr15.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str15));
    assert (expr16.accept(ToStringExpressionVisitor.INSTANCE).toString().equals(str16));
    assert (expr17.accept(ToStringExpressionVisitor.INSTANCE).toString().equals("s-(5.8+(q))/s-m"));

    assert (Math.abs((double) expr1.accept(new ComputeExpressionVisitor(hashMap)) - 5.0)
            <= eps);
    assert (Math.abs((double) expr2.accept(new ComputeExpressionVisitor(hashMap)) - -7.11)
            <= eps);
    assert (Math.abs((double) expr3.accept(new ComputeExpressionVisitor(hashMap)) - 9.999)
            <= eps);
    assert (Math.abs((double) expr4.accept(new ComputeExpressionVisitor(hashMap)) - -1.32)
            <= eps);
    assert (Math.abs((double) expr5.accept(new ComputeExpressionVisitor(hashMap)) - 8.1)
            <= eps);
    assert (Math.abs((double) expr6.accept(new ComputeExpressionVisitor(hashMap)) - 19.0)
            <= eps);
    assert (Math.abs((double) expr7.accept(new ComputeExpressionVisitor(hashMap)) - -1019.005)
            <= eps);
    assert (Math.abs((double) expr8.accept(new ComputeExpressionVisitor(hashMap)) - 1.23)
            <= eps);
    assert (Math.abs((double) expr9.accept(new ComputeExpressionVisitor(hashMap)) - 12.0)
            <= eps);
    assert (Math.abs((double) expr10.accept(new ComputeExpressionVisitor(hashMap)) - -10.99)
            <= eps);
    assert (Math.abs((double) expr11.accept(new ComputeExpressionVisitor(hashMap)) - 0.4)
            <= eps);
    assert (Math.abs((double) expr12.accept(new ComputeExpressionVisitor(hashMap)) - -2.0)
            <= eps);
    assert (Math.abs((double) expr13.accept(new ComputeExpressionVisitor(hashMap)) - 28.56686)
            <= eps);
    hashMap.put("x", 8.0);
    assert (Math.abs((double) expr14.accept(new ComputeExpressionVisitor(hashMap)) - 8.0)
            <= eps);
    hashMap.put("x", 0.0);
    assert (Math.abs((double) expr14.accept(new ComputeExpressionVisitor(hashMap)) - 0.0)
            <= eps);
    hashMap.put("y", -88.018);
    assert (Math.abs((double) expr15.accept(new ComputeExpressionVisitor(hashMap)) - 88.018)
            <= eps);
    hashMap.put("y", 53.91);
    assert (Math.abs((double) expr15.accept(new ComputeExpressionVisitor(hashMap)) - -53.91)
            <= eps);
    hashMap.put("a", -9.0);
    hashMap.put("r", 13.1);
    assert (Math.abs((double) expr16.accept(new ComputeExpressionVisitor(hashMap)) - 4.9)
            <= eps);
    hashMap.put("a", 68.223);
    hashMap.put("r", -200.8);
    assert (Math.abs((double) expr16.accept(new ComputeExpressionVisitor(hashMap)) - 64.354)
            <= eps);
    hashMap.put("s", -1.0);
    hashMap.put("q", 0.77);
    hashMap.put("m", 308.0);
    assert (Math.abs((double) expr17.accept(new ComputeExpressionVisitor(hashMap)) - -302.43)
            <= eps);
    hashMap.put("s", -19.84);
    hashMap.put("q", 30.03);
    hashMap.put("m", -10.0);
    assert (Math.abs((double) expr17.accept(new ComputeExpressionVisitor(hashMap)) - -8.0340524)
            <= eps);
  }
}
