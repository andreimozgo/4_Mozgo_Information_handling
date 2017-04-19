package by.mozgo.handling.interpreter;

/**
 * @author Andrei Mozgo
 */
class NonTerminalExpr extends AbstractExpr {
    private String value;

    NonTerminalExpr(String value) {
        this.value = value;
    }

    @Override
    void interpret(Context context) {
        context.pushValue(value);
    }
}
