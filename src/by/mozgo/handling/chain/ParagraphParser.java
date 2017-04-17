package by.mozgo.handling.chain;

import by.mozgo.handling.composite.ComponentLevel;
import by.mozgo.handling.composite.TextComponent;

import java.util.ArrayList;
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
        List<String> paragraphStrings = Arrays.asList(text.split(PARAGRAPH_DELIMETER));
        List<TextComponent> paragraphs = new ArrayList<>();
        TextComponent textComponent = new TextComponent();

        for (String paragraphString : paragraphStrings) {
            TextComponent paragraph;
            paragraph = nextParser.parseText(paragraphString);
            paragraph.setLevel(ComponentLevel.PARAGRAPH);
            paragraphs.add(paragraph);
        }

        textComponent.setComponents(paragraphs);
        return textComponent;
    }
}
