package by.mozgo.handling.composite;

import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class Lexeme extends TextComponent {
    List<Word> wordExpressionList;
    String lexeme;

    public Lexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return lexeme;
    }
}
