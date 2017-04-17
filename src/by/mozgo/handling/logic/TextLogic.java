package by.mozgo.handling.logic;

import by.mozgo.handling.composite.TextComponent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 17.04.2017.
 */
public class TextLogic {
    private static final Logger LOGGER = LogManager.getLogger();
/*    public void swapFirstLastLexeme(TextComponent text) {
        List<TextComponent> sentences = text.receiveComponents(TextChildLevel.SENTENCE);
        sentences.forEach((TextComponent component) -> {
            List<TextComponent> lexemes = component.receiveComponents(TextChildLevel.LEXEME);
            TextComponent temp = lexemes.get(0);
            lexemes.set(0, lexemes.get(lexemes.size() - 1));
            lexemes.set(lexemes.size() - 1, temp);
        });
    }*/

    public static void sortByLexemeNumber(TextComponent text) {
        List<TextComponent> sentences = getAllSentences(text);
        sentences.sort(new ComponentsSizeComporator());
        for (TextComponent sentence : sentences) {
            LOGGER.log(Level.INFO, "{} lexemes: {}", sentence.getComponents().size(), sentence);
        }
    }

    protected static List<TextComponent> getAllSentences(TextComponent text) {
        List<TextComponent> sentences = new ArrayList<>();
        for (TextComponent paragraph : text.getComponents()) {
            sentences.addAll(paragraph.getComponents());
        }
        return sentences;
    }
}
