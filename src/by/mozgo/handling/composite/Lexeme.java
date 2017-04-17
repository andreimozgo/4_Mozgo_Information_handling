package by.mozgo.handling.composite;

/**
 * @author Andrei Mozgo
 */
public class Lexeme extends TextComponent {
    private String lexeme;

    public Lexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return " " + lexeme;
    }
}
