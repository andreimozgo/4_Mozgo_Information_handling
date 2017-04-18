package by.mozgo.handling.interpreter;

/**
 * Created by Андрей on 18.04.2017.
 */
public class TerminalExpressionMultiply extends AbstractMathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue() * c.popValue());
    }
}
