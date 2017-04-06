package by.mozgo.handling.composite;

import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class Sentence extends TextComponent {
    List<Lexeme> lexemes;
    private String sentence;

    @Override
    public String toString() {
        return sentence;
    }
}
