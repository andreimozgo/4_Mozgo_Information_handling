package by.mozgo.handling.interpreter;

/**
 * Created by Andrei Mozgo. 2017.
 */
class TerminalExpressionPlus extends AbstractExpression {

    @Override
    void interpret(Context context) {
        double rightOperand = defineOperand(context);
        double leftOperand = defineOperand(context);
        context.pushValue(String.valueOf(leftOperand + rightOperand));
    }
}
