package by.mozgo.handling.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class Client {
    private static final String SPACES = "\\p{Blank}+";
    private List<AbstractExpression> expressions;

    public Client(String expression) {
        expressions = new ArrayList<>();
        parse(expression);
    }

    public String calculate() {
        Context context = new Context();
        expressions.forEach((term) -> term.interpret(context));
        return context.popValue();
    }

    private void parse(String expression) {
        for (String symbol : expression.split(SPACES)) {
            if (symbol.isEmpty()) {
                continue;
            }
            switch (symbol) {
                case "+":
                    expressions.add(new TerminalExpressionPlus());
                    break;
                case "-":
                    expressions.add(new TerminalExpressionMinus());
                    break;
                case "*":
                    expressions.add(new TerminalExpressionMultiply());
                    break;
                case "/":
                    expressions.add(new TerminalExpressionDivide());
                    break;
                case "#":
                    expressions.add(new TerminalBeforeDecrement());
                    break;
                case "£":
                    expressions.add(new TerminalBeforeIncrement());
                    break;
                case "{":
                    expressions.add(new TerminalAfterIncrement());
                    break;
                case "}":
                    expressions.add(new TerminalAfterDecrement());
                    break;
                default:
                    expressions.add(new NonTerminalExpression(symbol));
            }
        }
    }
}
