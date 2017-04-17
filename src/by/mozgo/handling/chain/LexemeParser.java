package by.mozgo.handling.chain;

import by.mozgo.handling.composite.Lexeme;
import by.mozgo.handling.composite.TextComponent;

import java.util.Arrays;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class LexemeParser implements TextParser {
    private final String LEXEME_DELIMETER = "\\s";

    @Override
    public TextComponent parseText(String text) {
        List<String> lexemes = Arrays.asList(text.split(LEXEME_DELIMETER));
        TextComponent sentenceComponent = new TextComponent();

        for (String lexeme : lexemes) {
            Lexeme lexemeComponent = new Lexeme(lexeme);
            sentenceComponent.add(lexemeComponent);
        }

        return sentenceComponent;
    }
}
