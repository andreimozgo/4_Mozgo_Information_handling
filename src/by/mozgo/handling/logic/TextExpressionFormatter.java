package by.mozgo.handling.logic;

import by.mozgo.handling.composite.Expression;

/**
 * Created by Андрей on 18.04.2017.
 */
public class TextExpressionFormatter {
    private static final String ICREMENT_PREFIX_I = "(\\+\\+i)";
    private static final String INREMENT_POSTFIX_I = "(i\\+\\+)";
    private static final String DECREMENT_PREFIX_I = "(--i)";
    private static final String ICREMENT_PREFIX_J = "(\\+\\+j)";
    private static final String INCREMENT_POSTFIX_J = "(j\\+\\+)";
    private static final String DECREMENT_POSTFIX_I = "(i--)";
    private static final String DECREMENT_PREFIX_J = "(--j)";
    private static final String DECREMENT_POSTFIX_J = "(j--)";

    private String replaceIncrementsDecrements(String expression) {
        return expression
                .replaceAll(INREMENT_POSTFIX_I, "i{").replaceAll(ICREMENT_PREFIX_I, "£i")
                .replaceAll(DECREMENT_POSTFIX_I, "i}").replaceAll(DECREMENT_PREFIX_I, "#i")
                .replaceAll(INCREMENT_POSTFIX_J, "j{").replaceAll(ICREMENT_PREFIX_J, "£j")
                .replaceAll(DECREMENT_POSTFIX_J, "j}").replaceAll(DECREMENT_PREFIX_J, "#j");
    }

    private String putSpaces(String expression) {
        StringBuilder newExpr = new StringBuilder();
        char[] oldExpr = expression.toCharArray();
        char space = ' ';
        for (char c : oldExpr) {
            if (Character.isDigit(c) || Character.isLetter(c)) {
                newExpr.append(c);
            } else {
                newExpr.append(space).append(c).append(space);
            }
        }
        return newExpr.toString().trim();
    }

    public String formatExpression(Expression expression) {
        return replaceIncrementsDecrements(putSpaces(expression.toString())).trim();
    }
}
