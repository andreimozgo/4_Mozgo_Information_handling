package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.exception.TextHandlingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;

/**
 * @author Andrei Mozgo
 */
public class PostfixNotationConverter {
    private static Logger logger = LogManager.getLogger(PostfixNotationConverter.class);
    private final char WHITESPACE = ' ';
    private final int PLUS_MINUS_PRIORITY = 1;
    private final int MULTIPLY_DIVIDE_PRIORITY = 2;
    private final int INCREMENT_DECREMENT_PRIOROTY = 3;
    private ArrayDeque<String> operations = new ArrayDeque<>();
    private MathExpressionFormatter formatter = new MathExpressionFormatter();

    public String convertExpression(TextComponent mathExpression) throws TextHandlingException {
        String expression = formatter.formatExpression(mathExpression);
        StringBuilder postfixExpression = new StringBuilder();
        String delimiter = "\\s+";
        for (String token : expression.split(delimiter)) {
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

    private void gotPair(StringBuilder notation) {
        while (!operations.isEmpty()) {
            String onTop = operations.pop();
            if (onTop.equals("(")) {
                break;
            } else {
                notation.append(onTop).append(WHITESPACE);
            }
        }
    }

}