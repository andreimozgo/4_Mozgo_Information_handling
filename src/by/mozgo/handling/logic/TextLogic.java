package by.mozgo.handling.logic;

import by.mozgo.handling.composite.Lexeme;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.composite.TextComposite;
import by.mozgo.handling.exception.TextHandlingException;
import by.mozgo.handling.interpreter.Client;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class TextLogic {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MATH_PATTERN = "([\\d(i\\-][\\d()ij+\\-*/\\p{Blank}]+[\\-\\d)ij+])|\\+";

    public static String swapFirstLastLexeme(TextComponent textComponent) {
        List<TextComponent> sentences = getAllSentences(textComponent);
        sentences.forEach((TextComponent sentence) -> {
            List<TextComponent> lexemes = sentence.getComponents();
            TextComponent temp = lexemes.get(0);
            lexemes.set(0, lexemes.get(lexemes.size() - 1));
            lexemes.set(lexemes.size() - 1, temp);
        });
        LOGGER.log(Level.INFO, "{}", textComponent);
        return textComponent.toString();
    }

    public static String sortByLexemeNumber(TextComponent textComponent) {
        List<TextComponent> sentences = getAllSentences(textComponent);
        sentences.sort(new ComponentsSizeComparator());
        for (TextComponent sentence : sentences) {
            LOGGER.log(Level.INFO, "{} lexemes: {}", sentence.getComponents().size(), sentence);
        }
        TextComponent resultTextComponent = new TextComposite();
        for (TextComponent component : sentences) {
            resultTextComponent.add(component);
        }
        return resultTextComponent.toString();
    }

    public static String removeSpecificLexemes(TextComponent textComponent, char firstCharacter, int length) {
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
        return textComponent.toString();
    }

    public static String calculateExpressions(TextComponent textComponent, int i, int j) {
        MathExpressionFormatter formatter = new MathExpressionFormatter();
        Lexeme lexemeComponent;
        String formattedExpression = null;
        uniteExpressions(textComponent);
        List<TextComponent> sentenceComponents = getAllSentences(textComponent);
        for (TextComponent sentenceComponent : sentenceComponents) {
            List<TextComponent> lexemeComponents = sentenceComponent.getComponents();
            for (int index = 0; index < lexemeComponents.size(); index++) {
                lexemeComponent = (Lexeme) lexemeComponents.get(index);
                if (lexemeComponent.getLexeme().matches(MATH_PATTERN)) {
                    try {
                        formattedExpression = formatter.format(lexemeComponent);
                    } catch (TextHandlingException e) {
                        LOGGER.log(Level.WARN, "{}", e.getMessage());
                    }
                    if (formattedExpression == null) {
                        LOGGER.log(Level.WARN, "Can't convert expression");
                    } else {
                        Lexeme newLexemeComponent = new Lexeme(new Client(formattedExpression, i, j).calculate());
                        lexemeComponents.set(index, newLexemeComponent);
                    }
                }
            }
        }
        return textComponent.toString();
    }

    private static void uniteExpressions(TextComponent textComponent) {
        List<TextComponent> sentenceComponents = getAllSentences(textComponent);
        Lexeme lexemeComponent;
        Lexeme nextLexemeComponent;
        for (TextComponent sentenceComponent : sentenceComponents) {
            List<TextComponent> lexemeComponents = sentenceComponent.getComponents();
            int i = 0;
            while (i < lexemeComponents.size() - 1) {
                lexemeComponent = (Lexeme) lexemeComponents.get(i);
                nextLexemeComponent = (Lexeme) lexemeComponents.get(i + 1);
                if (lexemeComponent.getLexeme().matches(MATH_PATTERN) &&
                        (nextLexemeComponent).getLexeme().matches(MATH_PATTERN)) {
                    String expression = lexemeComponent.getLexeme().concat(nextLexemeComponent.getLexeme());
                    Lexeme expressionLexeme = new Lexeme(expression);
                    lexemeComponents.set(i, expressionLexeme);
                    lexemeComponents.remove(i + 1);
                } else {
                    i++;
                }
            }
        }
    }

    private static List<TextComponent> getAllSentences(TextComponent textComponent) {
        List<TextComponent> sentenceComponents = new ArrayList<>();
        for (TextComponent paragraphComponent : textComponent.getComponents()) {
            sentenceComponents.addAll(paragraphComponent.getComponents());
        }
        return sentenceComponents;
    }
}
