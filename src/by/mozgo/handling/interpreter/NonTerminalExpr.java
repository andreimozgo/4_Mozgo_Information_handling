package by.mozgo.handling.interpreter;

public class NonTerminalExpr implements IExpr {
    private String value;

    public NonTerminalExpr(String value) {
        this.value = value;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(value);
    }
}
