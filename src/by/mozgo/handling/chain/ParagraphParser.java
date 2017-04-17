package by.mozgo.handling.chain;

import by.mozgo.handling.composite.TextComponent;

import java.util.Arrays;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class ParagraphParser implements TextParser {
    private final String PARAGRAPH_DELIMETER = "\\n";
    private TextParser nextParser = new SentenceParser();

    @Override
    public TextComponent parseText(String text) {
        List<String> paragraphs = Arrays.asList(text.split(PARAGRAPH_DELIMETER));
        TextComponent textComponent = new TextComponent();

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent;
            paragraphComponent = nextParser.parseText(paragraph);
            textComponent.add(paragraphComponent);
        }

        return textComponent;
    }
}
