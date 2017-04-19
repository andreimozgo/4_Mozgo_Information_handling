package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.exception.TextHandlingException;

import java.util.ArrayDeque;


public class PostfixNotationConverter {
    private static final String OPERATIONS = "[\\+-=\\*/{}£#]";
    private static final String WHITESPACES = "\\s+";
    private static final char WHITESPACE = ' ';
    private static final int PLUS_MINUS_PRIORITY = 1;
    private static final int MULTIPLY_DIVIDE_PRIORITY = 2;
    private static final int INCREMENT_DECREMENT_PRIOROTY = 3;
    private ArrayDeque<String> operations = new ArrayDeque<>();

    public String convertExpression(TextComponent mathExpression) throws TextHandlingException {
        MathExpressionFormatter formatter = new MathExpressionFormatter();
        String expression = formatter.formatExpression(mathExpression);
        StringBuilder postfixExpression = new StringBuilder();
        for (String token : expression.split(WHITESPACES)) {
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
                    putByPriority(token, INCREMENT_DECREMENT_PRIOROTY, postfixExpression);
                    break;
                case "(":
                    operations.push(token);
                    break;
                case ")":
                    gotPair(postfixExpression);
                    break;
                default:
                    postfixExpression.append(token).append(WHITESPACE);
                    break;
            }
        }
        while (!operations.isEmpty()) {
            postfixExpression.append(operations.pop()).append(WHITESPACE);
        }
        return postfixExpression.toString().trim();
    }

    private void gotPair(StringBuilder notation) throws TextHandlingException {
        while (!operations.isEmpty()) {
            String onTop = operations.pop();
            if (onTop.equals("(")) {
                break;
            } else if (onTop.matches(OPERATIONS)) {
                notation.append(onTop).append(WHITESPACE);
            } else {
                throw new TextHandlingException("Error in expression");
            }
        }
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
                        topPriority = INCREMENT_DECREMENT_PRIOROTY;
                        break;
                    default:
                        throw new TextHandlingException("Unsupported operation");
                }
                if (topPriority < priority) {
                    operations.push(topOperation);
                    break;
                } else {
                    notation.append(topOperation).append(WHITESPACE);
                }
            }
        }
        operations.push(thisOperation);
    }
}