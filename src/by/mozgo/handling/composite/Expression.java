package by.mozgo.handling.composite;

/**
 * @author Andrei Mozgo
 */
public class Expression extends TextComponent {
    private String expression;

    public Expression(String value) {
        expression = value;
    }

    @Override
    public String toString() {
        return expression;
    }
}
