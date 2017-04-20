package test.by.mozgo.handling.logic;

import by.mozgo.handling.chain.ParagraphParser;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.logic.TextLogic;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class TextLogicTest {
    TextComponent textComponent;

    @Before
    public void initData() {
        String text = "Five centuries, but also the leap. It has survived not only. " +
                "Into 13+ i-- electronic typesetting, remaining 3+5 leap.";
        textComponent = new ParagraphParser().parseText(text);
    }

    @After
    public void clearData() {
        textComponent = null;
    }

    @Test
    public void testSortByLexemeNumber() {
        String expected = "It has survived not only. Five centuries, but also the leap. " +
                "Into 13+ i-- electronic typesetting, remaining 3+5 leap.";
        String actual = TextLogic.sortByLexemeNumber(textComponent);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSwapFirstLastLexeme() {
        String expected = "leap. centuries, but also the Five only. has survived not It " +
                "leap. 13+ i-- electronic typesetting, remaining 3+5 Into";
        String actual = TextLogic.swapFirstLastLexeme(textComponent);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveSpecificLexemes() {
        String expected = "Five centuries, also the leap. It has survived not only. " +
                "Into 13+ i-- electronic typesetting, remaining 3+5 leap.";
        String actual = TextLogic.removeSpecificLexemes(textComponent, 'b', 3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateExpressions() {
        String expected = "Five centuries, but also the leap. It has survived not only. " +
                "Into 23.0 electronic typesetting, remaining 4.0 leap.";
        int i = 10;
        int j = 20;
        String actual = TextLogic.calculateExpressions(textComponent, i, j);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReceiveText() {
        String expected = "\\tFive centuries, but also the leap. It has survived not only. " +
                "Into 13+ i-- electronic typesetting, remaining 3+5 leap.";
        String actual = TextLogic.receiveText(textComponent);
        Assert.assertEquals(expected, actual);
    }
}
