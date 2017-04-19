package by.mozgo.handling.interpreter;

public class DivideExpr implements IExpr {
    @Override
    public void interpret(Context context) {
        double rightOperand = defineOperand(context);
        double leftOperand = defineOperand(context);
        context.pushValue(String.valueOf(leftOperand / rightOperand));
    }


}
