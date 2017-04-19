package by.mozgo.handling.interpreter;

public class PreDecrementExpr implements IExpr {
    @Override
    public void interpret(Context context) {
        String var = context.popValue();
        double num;
        switch (var) {
            case "i":
                num = context.getI();
                context.pushValue(String.valueOf(--num));
                break;
            case "j":
                num = context.getJ();
                context.pushValue(String.valueOf(--num));
                break;
            default:
                num = Double.parseDouble(var);
                context.pushValue(String.valueOf(--num));
        }
    }
}
