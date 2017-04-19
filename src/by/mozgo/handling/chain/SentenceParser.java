package by.mozgo.handling.chain;

import by.mozgo.handling.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andrei Mozgo
 */
public class SentenceParser implements TextParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String SENTENCE_PATTERN = "\\p{Upper}[^.]+\\.";

    private TextParser nextParser = new LexemeParser();

    @Override
    public TextComponent parseText(String text) {
        Matcher sentenceMatcher = Pattern.compile(SENTENCE_PATTERN).matcher(text);
        TextComponent paragraphComponent = new TextComponent();

        while (sentenceMatcher.find()) {
            TextComponent sentenceComponent = nextParser.parseText(sentenceMatcher.group());
            paragraphComponent.add(sentenceComponent);
        }

        return paragraphComponent;
    }
}
