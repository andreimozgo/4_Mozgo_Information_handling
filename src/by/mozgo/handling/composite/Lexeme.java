package by.mozgo.handling.composite;

/**
 * Created by Andrei Mozgo. 2017.
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
