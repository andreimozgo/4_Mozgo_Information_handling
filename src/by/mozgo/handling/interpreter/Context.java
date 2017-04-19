package by.mozgo.handling.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

class Context {
    private Deque<String> contextValues;
    private double i = 10;
    private double j = 20;

    Context() {
        contextValues = new ArrayDeque<>();
    }

    String popValue() {
        return contextValues.pop();
    }

    void pushValue(String value) {
        this.contextValues.push(value);
    }

    double getI() {
        return i;
    }

    void setI(double i) {
        this.i = i;
    }

    double getJ() {
        return j;
    }

    void setJ(double j) {
        this.j = j;
    }

}
