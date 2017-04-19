package by.mozgo.handling.interpreter;

/**
 * Created by Andrei Mozgo. 2017.
 */
class NonTerminalExpression extends AbstractExpression {
    private String value;

    NonTerminalExpression(String value) {
        this.value = value;
    }

    @Override
    void interpret(Context context) {
        context.pushValue(value);
    }
}
