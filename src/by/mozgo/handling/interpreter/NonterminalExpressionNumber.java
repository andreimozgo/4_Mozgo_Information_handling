package by.mozgo.handling.interpreter;

/**
 * Created by Андрей on 18.04.2017.
 */
public class NonterminalExpressionNumber extends AbstractMathExpression {
    private int number;

    public NonterminalExpressionNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
