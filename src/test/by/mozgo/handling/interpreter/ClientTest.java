package test.by.mozgo.handling.interpreter;

import by.mozgo.handling.interpreter.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Andrei Mozgo. 2017.
 */
@RunWith(Parameterized.class)
public class ClientTest {
    private String actual;
    private String expected;

    public ClientTest(String actual, String expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"5 1 +", "6.0"},
                {"i 1 -", "9.0"}
        });
    }

    @Test
    public void calculateTest() {
        int i = 10;
        int j = 20;
        Client interpreter = new Client(actual, i, j);
        String actual = interpreter.calculate();
        Assert.assertEquals(expected, actual);
    }
}
