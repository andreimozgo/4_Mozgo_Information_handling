package by.mozgo.handling.interpreter;

/**
 * @author Andrei Mozgo
 */
public class SubstractExpr extends AbstractExpr {
    @Override
    void interpret(Context context) {
        double rightOperand = defineOperand(context);
        double leftOperand = defineOperand(context);
        context.pushValue(String.valueOf(leftOperand - rightOperand));
    }
}
