package test.by.mozgo.handling.chain;

import by.mozgo.handling.chain.ParagraphParser;
import by.mozgo.handling.chain.TextParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class TextParserTest {
    private TextParser parser;
    private String text;

    @Before
    public void init() {
        parser = new ParagraphParser();
        text = "\tSome string.";
    }

    @After
    public void unInitParser() {
        parser = null;
        text = null;
    }

    @Test
    public void doChainTest() {
        String actual = parser.parseText(text).toString().trim();
        String expected = text.trim();
        Assert.assertEquals(expected, actual);
    }
}
