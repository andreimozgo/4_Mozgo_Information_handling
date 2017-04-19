package by.mozgo.handling.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final String SPACES = "\\s+";
    private List<AbstractExpression> expressionList;

    public Client(String expression) {
        expressionList = new ArrayList<>();
        parse(expression);
    }

    private void parse(String expression) {
        for (String token : expression.split(SPACES)) {
            if (token.isEmpty()) {
                continue;
            }
            switch (token) {
                case "+":
                    expressionList.add(new AddExpression());
                    break;
                case "-":
                    expressionList.add(new SubstractExpression());
                    break;
                case "*":
                    expressionList.add(new MultiplyExpression());
                    break;
                case "/":
                    expressionList.add(new DivideExpression());
                    break;
                case "#":
                    expressionList.add(new BeforeDecrement());
                    break;
                case "£":
                    expressionList.add(new BeforeIncrement());
                    break;
                case "{":
                    expressionList.add(new AfterIncrement());
                    break;
                case "}":
                    expressionList.add(new AfterDecrement());
                    break;
                default:
                    expressionList.add(new NonTerminalExpression(token));
            }
        }
    }

    public String calculate() {
        Context context = new Context();
        expressionList.forEach((term) -> term.interpret(context));
        return context.popValue();
    }
}
