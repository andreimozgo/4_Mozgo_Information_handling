package by.mozgo.handling.logic;

import by.mozgo.handling.composite.Lexeme;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.exception.TextHandlingException;
import by.mozgo.handling.interpreter.Client;
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
    private static final char SPACE = ' ';
    private static final String MATH_PATTERN = "([\\d(i\\-][\\d()ij+\\-*/\\p{Blank}]+[\\-\\d)ij+])|\\+";

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
        sentences.sort(new ComponentsSizeComparator());
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

    public static void calculateExpressions(TextComponent textComponent) {
        PostfixNotationConverter converter = new PostfixNotationConverter();
        Lexeme lexemeComponent;
        String preparedExpr = null;
        uniteExpressions(textComponent);
        List<TextComponent> sentenceComponents = getAllSentences(textComponent);
        for (TextComponent sentenceComponent : sentenceComponents) {
            List<TextComponent> lexemeComponents = sentenceComponent.getComponents();
            for (int i = 0; i < lexemeComponents.size(); i++) {
                lexemeComponent = (Lexeme) lexemeComponents.get(i);
                if (lexemeComponent.getLexeme().matches(MATH_PATTERN)) {
                    try {
                        preparedExpr = converter.convertExpression(lexemeComponent);
                    } catch (TextHandlingException e) {
                        LOGGER.log(Level.WARN, e.getMessage());
                    }
                    if (preparedExpr == null) {
                        LOGGER.log(Level.WARN, "Can't calculate an expression, leave old text");
                    } else {
                        Lexeme newLexemeConponent = new Lexeme(new Client(preparedExpr).calculate() + SPACE);
                        lexemeComponents.set(i, newLexemeConponent);
                    }
                }
            }
        }
    }

    private static void uniteExpressions(TextComponent textComponent) {
        List<TextComponent> sentenceComponents = getAllSentences(textComponent);
        for (TextComponent sentenceComponent : sentenceComponents) {
            List<TextComponent> lexemeComponents = sentenceComponent.getComponents();
            int i = 0;
            while (i < lexemeComponents.size() - 1) {
                if (((Lexeme) lexemeComponents.get(i)).getLexeme().matches(MATH_PATTERN) &&
                        ((Lexeme) lexemeComponents.get(i + 1)).getLexeme().matches(MATH_PATTERN)) {
                    String expression = ((Lexeme) lexemeComponents.get(i)).getLexeme()
                            .concat(((Lexeme) lexemeComponents.get(i + 1)).getLexeme());
                    Lexeme expressionLexeme = new Lexeme(expression);
                    lexemeComponents.set(i, expressionLexeme);
                    lexemeComponents.remove(i + 1);
                } else {
                    i++;
                }
            }
        }
    }
}
