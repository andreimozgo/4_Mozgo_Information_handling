package by.mozgo.handling.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {
    private Deque<String> contextValues;
    private double i = 89;
    private double j = 42;

    public Context() {
        contextValues = new ArrayDeque<>();
    }

    public String popValue() {
        return contextValues.pop();
    }

    public void pushValue(String value) {
        this.contextValues.push(value);
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

}
