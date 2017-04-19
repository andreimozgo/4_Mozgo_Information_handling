package by.mozgo.handling.interpreter;

class SubstractExpression extends AbstractExpression {
    @Override
    void interpret(Context context) {
        double rightOperand = defineOperand(context);
        double leftOperand = defineOperand(context);
        context.pushValue(String.valueOf(leftOperand - rightOperand));
    }
}
