package by.mozgo.handling.composite;

import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class Sentence extends TextComponent {
    List<Lexeme> lexemes;
    private String sentence;

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return sentence;
    }
}
