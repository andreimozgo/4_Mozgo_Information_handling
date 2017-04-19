package by.mozgo.handling.composite;

/**
 * @author Andrei Mozgo
 */
public class Lexeme implements TextComponent {
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
