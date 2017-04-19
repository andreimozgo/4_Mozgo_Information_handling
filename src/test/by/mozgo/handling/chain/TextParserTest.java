package test.by.mozgo.handling.chain;

import by.mozgo.handling.chain.ParagraphParser;
import by.mozgo.handling.chain.TextParser;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.logic.TextLogic;
import by.mozgo.handling.reader.TextReader;
import by.mozgo.handling.writer.TextWriter;
import org.junit.Test;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class TextParserTest {
    @Test
    public void testParseText() {
        String filename = "data/input.txt";
        String text = TextReader.readData(filename);
        TextParser paragraphParser = new ParagraphParser();
        TextComponent textComponent = paragraphParser.parseText(text);
        TextLogic.calculateExpressions(textComponent);
        System.out.println(textComponent.getComponents().toString());
        TextWriter.writeData(textComponent.toString());
    }
}
