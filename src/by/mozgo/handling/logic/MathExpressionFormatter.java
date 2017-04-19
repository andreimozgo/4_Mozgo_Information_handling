package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andrei Mozgo
 */
class MathExpressionFormatter {
    private final String ICREMENT_PREFIX_I = "(\\+\\+i)";
    private final String INREMENT_POSTFIX_I = "(i\\+\\+)";
    private final String DECREMENT_PREFIX_I = "(--i)";
    private final String ICREMENT_PREFIX_J = "(\\+\\+j)";
    private final String INCREMENT_POSTFIX_J = "(j\\+\\+)";
    private final String DECREMENT_POSTFIX_I = "(i--)";
    private final String DECREMENT_PREFIX_J = "(--j)";
    private final String DECREMENT_POSTFIX_J = "(j--)";
    private final String POSITIVE_NUM = "\\d";
    private final String NEGATIVE_NUM = "(-\\d)";
    private List<String> positives = new ArrayList<>();
    private List<String> negatives = new ArrayList<>();

    private String replaceUnusualOperations(String expression) {
        return expression
                .replaceAll(INREMENT_POSTFIX_I, "i{").replaceAll(ICREMENT_PREFIX_I, "£i")
                .replaceAll(DECREMENT_POSTFIX_I, "i}").replaceAll(DECREMENT_PREFIX_I, "#i")
                .replaceAll(INCREMENT_POSTFIX_J, "j{").replaceAll(ICREMENT_PREFIX_J, "£j")
                .replaceAll(DECREMENT_POSTFIX_J, "j}").replaceAll(DECREMENT_PREFIX_J, "#j");
    }

    private String replaceValues(String expr) {
        Matcher m = Pattern.compile(NEGATIVE_NUM).matcher(expr);

        while (m.find()) {
            negatives.add(m.group());
            expr = expr.replace(m.group(), "N");
        }

        m.reset();
        m.usePattern(Pattern.compile(POSITIVE_NUM));

        while (m.find()) {
            positives.add(m.group());
            expr = expr.replace(m.group(), "P");
        }

        return expr;
    }


    private String makeExpression(String expression) {
        StringBuilder newExpr = new StringBuilder();
        char whitespace = ' ';
        int posCount = 0;
        int negCount = 0;
        for (char c : expression.toCharArray()) {
            switch (c) {
                case 'N':
                    newExpr.append(whitespace).append(negatives.get(negCount));
                    negCount++;
                    break;
                case 'P':
                    newExpr.append(positives.get(posCount));
                    posCount++;
                    break;
                default:
                    newExpr.append(whitespace).append(c).append(whitespace);

            }
        }
        return newExpr.toString().trim();
    }

    String formatExpression(TextComponent expression) {
        String res = expression.toString();
        res = replaceUnusualOperations(res);
        res = replaceValues(res);
        res = makeExpression(res);
        return res;
    }
}
