package by.mozgo.handling.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Andrei Mozgo
 */
class Context {
    private Deque<String> contextValues;
    private double i = 89;
    private double j = 42;

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
