package by.mozgo.handling.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class InterpreterClient {
    private static final String SPACES = "\\s+";
    private List<AbstractExpr> expressionList;

    public InterpreterClient(String expression) {
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
                    expressionList.add(new AddExpr());
                    break;
                case "-":
                    expressionList.add(new SubstractExpr());
                    break;
                case "*":
                    expressionList.add(new MultiplyExpr());
                    break;
                case "/":
                    expressionList.add(new DivideExpr());
                    break;
                case "#":
                    expressionList.add(new PreDecrementExpr());
                    break;
                case "£":
                    expressionList.add(new PreIncrementExpr());
                    break;
                case "{":
                    expressionList.add(new PostIncrementExpr());
                    break;
                case "}":
                    expressionList.add(new PostDecrementExpr());
                    break;
                default:
                    expressionList.add(new NonTerminalExpr(token));
            }
        }
    }


    public String calculate() {
        Context context = new Context();
        expressionList.forEach((term) -> term.interpret(context));
        return context.popValue();
    }
}
