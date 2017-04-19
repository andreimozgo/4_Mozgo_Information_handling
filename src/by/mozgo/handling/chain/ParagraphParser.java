package by.mozgo.handling.chain;

import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.composite.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class ParagraphParser implements TextParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAGRAPH_DELIMITER = "\\n";
    private TextParser nextParser = new SentenceParser();

    @Override
    public TextComponent parseText(String text) {
        List<String> paragraphs = Arrays.asList(text.split(PARAGRAPH_DELIMITER));
        TextComponent textComponent = new TextComposite();

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = nextParser.parseText(paragraph);
            textComponent.add(paragraphComponent);
        }
        LOGGER.log(Level.INFO, "Text has been parsed successfully");
        return textComponent;
    }
}
