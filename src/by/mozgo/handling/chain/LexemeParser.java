package by.mozgo.handling.chain;

import by.mozgo.handling.composite.Lexeme;
import by.mozgo.handling.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andrei Mozgo
 */
public class LexemeParser implements TextParser {
    private final String LEXEME = "(?:(?:[\\d+\\-*\\/(]|[ij])" +
            "(?:[\\d+\\-*\\/(\\s\\)]|[ij\\s\\)])*(?!\\p{Alpha}))|((?<=\\s)[\\w\\p{Punct}]+)|(\\w+)|([\\w+\\.])";

    @Override
    public TextComponent parseText(String text) {

        Matcher lexemeMatcher = Pattern.compile(LEXEME).matcher(text);
        List<TextComponent> lexemes = new ArrayList<>();
        TextComponent textLexemes = new TextComponent();

        while (lexemeMatcher.find()) {
            lexemes.add(new Lexeme(lexemeMatcher.group()));
        }

        textLexemes.setComponents(lexemes);

        return textLexemes;
    }
}
