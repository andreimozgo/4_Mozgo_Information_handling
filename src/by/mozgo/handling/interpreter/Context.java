package by.mozgo.handling.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Andrei Mozgo. 2017.
 */
class Context {
    private Deque<String> contextValues;
    private double i;
    private double j;

    Context(int i, int j) {
        contextValues = new ArrayDeque<>();
        this.i = i;
        this.j = j;
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
