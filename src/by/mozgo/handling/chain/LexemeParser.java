package by.mozgo.handling.chain;

import by.mozgo.handling.composite.Lexeme;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.composite.TextComposite;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrei Mozgo. 2017.
 */
class LexemeParser implements TextParser {
    private static final String LEXEME_DELIMITER = "\\p{Blank}";

    @Override
    public TextComponent parseText(String text) {
        List<String> lexemes = Arrays.asList(text.split(LEXEME_DELIMITER));
        TextComponent sentenceComponent = new TextComposite();

        for (String lexeme : lexemes) {
            Lexeme lexemeComponent = new Lexeme(lexeme);
            sentenceComponent.add(lexemeComponent);
        }
        return sentenceComponent;
    }
}
