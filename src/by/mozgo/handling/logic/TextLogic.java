package by.mozgo.handling.logic;

import by.mozgo.handling.composite.Lexeme;
import by.mozgo.handling.composite.TextComponent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Андрей on 17.04.2017.
 */
public class TextLogic {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void swapFirstLastLexeme(TextComponent text) {
        List<TextComponent> sentences = getAllSentences(text);
        sentences.forEach((TextComponent sentence) -> {
            List<TextComponent> lexemes = sentence.getComponents();
            TextComponent temp = lexemes.get(0);
            lexemes.set(0, lexemes.get(lexemes.size() - 1));
            lexemes.set(lexemes.size() - 1, temp);
        });
        LOGGER.log(Level.INFO, "{}", text);
    }

    public static void sortByLexemeNumber(TextComponent textComponent) {
        List<TextComponent> sentences = getAllSentences(textComponent);
        sentences.sort(new ComponentsSizeComporator());
        for (TextComponent sentence : sentences) {
            LOGGER.log(Level.INFO, "{} lexemes: {}", sentence.getComponents().size(), sentence);
        }
    }

    public static void removeSpecificLexemes(TextComponent textComponent, char firstCharacter, int length) {
        List<TextComponent> sentenceComponents = getAllSentences(textComponent);
        for (TextComponent sentenceComponent : sentenceComponents) {
            List<TextComponent> lexemeComponents = sentenceComponent.getComponents();
            Iterator<TextComponent> iterator = lexemeComponents.iterator();
            while (iterator.hasNext()) {
                TextComponent lexemeComponent = iterator.next();
                String lexeme = ((Lexeme) lexemeComponent).getLexeme();
                if (lexeme.charAt(0) == firstCharacter && lexeme.length() == length) {
                    iterator.remove();
                    LOGGER.log(Level.INFO, "Lexeme '{}' has been removed", lexeme);
                }
            }
        }
    }

    protected static List<TextComponent> getAllSentences(TextComponent textComponent) {
        List<TextComponent> sentenceComponents = new ArrayList<>();
        for (TextComponent paragraphComponent : textComponent.getComponents()) {
            sentenceComponents.addAll(paragraphComponent.getComponents());
        }
        return sentenceComponents;
    }
}
