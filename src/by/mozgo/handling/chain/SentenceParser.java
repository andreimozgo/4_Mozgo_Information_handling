package by.mozgo.handling.chain;

import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.composite.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class SentenceParser implements TextParser {
    private final String SENTENCE_PATTERN = "\\p{Upper}[^.]+\\.";
    private TextParser nextParser = new LexemeParser();

    @Override
    public TextComponent parseText(String text) {
        Matcher sentenceMatcher = Pattern.compile(SENTENCE_PATTERN).matcher(text);
        TextComponent paragraphComponent = new TextComposite();

        while (sentenceMatcher.find()) {
            TextComponent sentenceComponent = nextParser.parseText(sentenceMatcher.group());
            paragraphComponent.add(sentenceComponent);
        }
        return paragraphComponent;
    }
}
