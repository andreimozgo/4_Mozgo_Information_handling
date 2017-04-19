package test.by.mozgo.handling.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.by.mozgo.handling.chain.TextParserTest;
import test.by.mozgo.handling.logic.TextLogicTest;
import test.by.mozgo.handling.reader.TextReaderTest;

/**
 * @author Andrei Mozgo
 */

@Suite.SuiteClasses({TextReaderTest.class, TextParserTest.class, TextLogicTest.class})
@RunWith(Suite.class)
public class SuiteTest {
}
