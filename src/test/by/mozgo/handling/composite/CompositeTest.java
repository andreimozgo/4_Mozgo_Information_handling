package test.by.mozgo.handling.composite;

import by.mozgo.handling.composite.Lexeme;
import by.mozgo.handling.composite.TextComponent;
import by.mozgo.handling.composite.TextComposite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class CompositeTest {
    private TextComponent textComponent;

    @Before
    public void init() {
        textComponent = new TextComposite();
        Stream.of("Five", "centuries,", "but", "also.").map(Lexeme::new).forEach(textComponent::add);
    }

    @After
    public void unInit() {
        textComponent = null;
    }

    @Test
    public void receiveTextTest() {
        String expected = "Five centuries, but also.";
        String actual = textComponent.toString().trim();
        Assert.assertEquals(expected, actual);
    }
}
