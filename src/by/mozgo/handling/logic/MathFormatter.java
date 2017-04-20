package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.exception.TextHandlingException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrei Mozgo. 2017.
 */
class MathFormatter {
    private static final String OPERATIONS = "[+-=*/{}£#]";
    private static final String SPACES = "\\p{Blank}+";
    private static final char SPACE = ' ';
    private static final int PLUS_MINUS_PRIORITY = 1;
    private static final int MULTIPLY_DIVIDE_PRIORITY = 2;
    private static final int INCREMENT_DECREMENT_PRIORITY = 3;
    private static final String INCREMENT_PREFIX_I = "(\\+\\+i)";
    private static final String INCREMENT_POSTFIX_I = "(i\\+\\+)";
    private static final String DECREMENT_PREFIX_I = "(--i)";
    private static final String INCREMENT_PREFIX_J = "(\\+\\+j)";
    private static final String INCREMENT_POSTFIX_J = "(j\\+\\+)";
    private static final String DECREMENT_POSTFIX_I = "(i--)";
    private static final String DECREMENT_PREFIX_J = "(--j)";
    private static final String DECREMENT_POSTFIX_J = "(j--)";
    private static final String POSITIVE_NUMBER = "\\d";
    private static final String NEGATIVE_NUMBER = "(-\\d)";
    private List<String> positiveNumbers = new ArrayList<>();
    private List<String> negativeNumbers = new ArrayList<>();
    private ArrayDeque<String> operations = new ArrayDeque<>();

    String format(TextComponent mathExpression) throws TextHandlingException {
        String expression = refactorExpression(mathExpression);
        StringBuilder postfixExpression = new StringBuilder();
        for (String token : expression.split(SPACES)) {
            switch (token) {
                case "+":
                case "-":
                    putByPriority(token, PLUS_MINUS_PRIORITY, postfixExpression);
                    break;
                case "*":
                case "/":
                    putByPriority(token, MULTIPLY_DIVIDE_PRIORITY, postfixExpression);
                    break;
                case "£":
                case "#":
                case "{":
                case "}":
                    putByPriority(token, INCREMENT_DECREMENT_PRIORITY, postfixExpression);
                    break;
                case "(":
                    operations.push(token);
                    break;
                case ")":
                    getPair(postfixExpression);
                    break;
                default:
                    postfixExpression.append(token).append(SPACE);
                    break;
            }
        }
        while (!operations.isEmpty()) {
            postfixExpression.append(operations.pop()).append(SPACE);
        }
        return postfixExpression.toString().trim();
    }

    private String refactorExpression(TextComponent expression) {
        String res = expression.toString();
        res = replaceSpecialOperations(res);
        res = replaceSigns(res);
        res = buildExpression(res);
        return res;
    }

    private void putByPriority(String thisOperation, int priority, StringBuilder notation)
            throws TextHandlingException {
        while (!operations.isEmpty()) {
            String topOperation = operations.pop();
            if (topOperation.equals("(")) {
                operations.push(topOperation);
                break;
            } else {
                int topPriority;
                switch (topOperation) {
                    case "+":
                    case "-":
                        topPriority = PLUS_MINUS_PRIORITY;
                        break;
                    case "*":
                    case "/":
                        topPriority = MULTIPLY_DIVIDE_PRIORITY;
                        break;
                    case "£":
                    case "#":
                    case "{":
                    case "}":
                        topPriority = INCREMENT_DECREMENT_PRIORITY;
                        break;
                    default:
                        throw new TextHandlingException("Unsupported operation");
                }
                if (topPriority < priority) {
                    operations.push(topOperation);
                    break;
                } else {
                    notation.append(topOperation).append(SPACE);
                }
            }
        }
        operations.push(thisOperation);
    }

    private void getPair(StringBuilder notation) throws TextHandlingException {
        while (!operations.isEmpty()) {
            String onTop = operations.pop();
            if (onTop.equals("(")) {
                break;
            } else if (onTop.matches(OPERATIONS)) {
                notation.append(onTop).append(SPACE);
            } else {
                throw new TextHandlingException("Error in expression");
            }
        }
    }

    private String replaceSpecialOperations(String expression) {
        return expression
                .replaceAll(INCREMENT_POSTFIX_I, "i{")
                .replaceAll(INCREMENT_PREFIX_I, "£i")
                .replaceAll(DECREMENT_POSTFIX_I, "i}")
                .replaceAll(DECREMENT_PREFIX_I, "#i")
                .replaceAll(INCREMENT_POSTFIX_J, "j{")
                .replaceAll(INCREMENT_PREFIX_J, "£j")
                .replaceAll(DECREMENT_POSTFIX_J, "j}")
                .replaceAll(DECREMENT_PREFIX_J, "#j");
    }

    private String replaceSigns(String expr) {
        Matcher matcher = Pattern.compile(NEGATIVE_NUMBER).matcher(expr);

        while (matcher.find()) {
            negativeNumbers.add(matcher.group());
            expr = expr.replace(matcher.group(), "N");
        }

        matcher.reset();
        matcher.usePattern(Pattern.compile(POSITIVE_NUMBER));

        while (matcher.find()) {
            positiveNumbers.add(matcher.group());
            expr = expr.replace(matcher.group(), "P");
        }

        return expr;
    }

    private String buildExpression(String expression) {
        StringBuilder newExpr = new StringBuilder();
        char whitespace = ' ';
        int posCount = 0;
        int negCount = 0;
        for (char c : expression.toCharArray()) {
            switch (c) {
                case 'N':
                    newExpr.append(whitespace).append(negativeNumbers.get(negCount));
                    negCount++;
                    break;
                case 'P':
                    newExpr.append(positiveNumbers.get(posCount));
                    posCount++;
                    break;
                default:
                    newExpr.append(whitespace).append(c).append(whitespace);
            }
        }
        return newExpr.toString().trim();
    }
}