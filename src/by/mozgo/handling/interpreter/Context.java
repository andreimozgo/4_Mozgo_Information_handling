package by.mozgo.handling.interpreter;

import java.util.ArrayDeque;

/**
 * Created by Андрей on 18.04.2017.
 */
public class Context {
    private ArrayDeque<Integer> contextValues = new ArrayDeque<>();

    public Integer popValue() {
        return contextValues.pop();
    }

    public void pushValue(Integer value) {
        this.contextValues.push(value);
    }
}
