package test.by.mozgo.handling.reader;

import by.mozgo.handling.reader.TextReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class TextReaderTest {
    @Test
    public void testFileReader() {
        String filename = "data/input.txt";
        String text = TextReader.readData(filename);
        boolean fileNotEmpty = !text.isEmpty();
        Assert.assertTrue(fileNotEmpty);
    }

    @Test(expected = RuntimeException.class)
    public void testFileReaderException() {
        String filename = "";
        TextReader.readData(filename);
    }
}
