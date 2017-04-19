package test.by.mozgo.handling.logic;

import by.mozgo.handling.chain.ParagraphParser;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.logic.TextLogic;
import by.mozgo.handling.reader.TextReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class TextLogicTest {
    TextComponent textComponent;

    @Before
    public void createComposite() {
        String filename = "data/input.txt";
        String text = TextReader.readData(filename);
        ParagraphParser paragraphParser = new ParagraphParser();
        textComponent = paragraphParser.parseText(text);
    }

    @Test
    public void testSortByLexemeNumber() {
        TextLogic.sortByLexemeNumber(textComponent);
    }

    @Test
    public void testSwapFirstLastLexeme() {
        TextLogic.swapFirstLastLexeme(textComponent);
    }

    @Test
    public void testRemoveSpecificLexemes() {
        TextLogic.removeSpecificLexemes(textComponent, 'r', 8);
    }

}
