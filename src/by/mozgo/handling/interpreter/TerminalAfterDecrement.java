package by.mozgo.handling.interpreter;

/**
 * Created by Andrei Mozgo. 2017.
 */
class TerminalAfterDecrement extends AbstractExpression {

    @Override
    void interpret(Context context) {
        String var = context.popValue();
        switch (var) {
            case "j":
                double j = context.getJ();
                context.pushValue(String.valueOf(j--));
                context.setJ(j);
                break;
            case "i":
                double i = context.getI();
                context.pushValue(String.valueOf(i--));
                context.setI(i);
                break;
            default:
                double num = Double.parseDouble(var);
                num--;
                context.pushValue(String.valueOf(num));
        }
    }
}
