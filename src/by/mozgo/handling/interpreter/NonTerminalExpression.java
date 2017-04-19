package by.mozgo.handling.interpreter;

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
