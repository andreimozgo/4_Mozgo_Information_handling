package by.mozgo.handling.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class Client {
    private static final String SPACES = "\\p{Blank}+";
    private List<AbstractExpression> expressions;
    private int i;
    private int j;

    public Client(String expression, int i, int j) {
        expressions = new ArrayList<>();
        this.i = i;
        this.j = j;
        parse(expression);
    }

    public String calculate() {
        Context context = new Context(i, j);
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
