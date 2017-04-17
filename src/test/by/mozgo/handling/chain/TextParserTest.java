package test.by.mozgo.handling.chain;

import by.mozgo.handling.chain.ParagraphParser;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.reader.TextReader;
import by.mozgo.handling.writer.TextWriter;
import org.junit.Test;

/**
 * @author Andrei Mozgo
 */
public class TextParserTest {
    @Test
    public void testParseText() {
        String filename = "data/input.txt";
        String text = TextReader.readData(filename);
        ParagraphParser paragraphParser = new ParagraphParser();
        TextComponent textComponent = paragraphParser.parseText(text);
        System.out.println(textComponent.getComponents().toString());
        TextWriter.writeData(textComponent.toString());
    }
}
