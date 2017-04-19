package by.mozgo.handling.interpreter;

class AfterDecrement extends AbstractExpression {
    @Override
    void interpret(Context context) {
        String var = context.popValue();
        switch (var) {
            case "i":
                double i = context.getI();
                context.pushValue(String.valueOf(i--));
                context.setI(i);
                break;
            case "j":
                double j = context.getJ();
                context.pushValue(String.valueOf(j--));
                context.setJ(j);
                break;
            default:
                double num = Double.parseDouble(var);
                num--;
                context.pushValue(String.valueOf(num));
        }
    }
}
