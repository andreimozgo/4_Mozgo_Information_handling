package by.mozgo.handling.interpreter;

public interface IExpr {
    void interpret(Context context);

    default double defineOperand(Context context) {
        double res;
        String value = context.popValue();
        switch (value) {
            case "i":
                res = context.getI();
                break;
            case "j":
                res = context.getJ();
                break;
            default:
                res = Double.parseDouble(value);
        }
        return res;
    }
}
