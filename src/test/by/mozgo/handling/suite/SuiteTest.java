package test.by.mozgo.handling.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.by.mozgo.handling.chain.TextParserTest;
import test.by.mozgo.handling.composite.CompositeTest;
import test.by.mozgo.handling.interpreter.ClientTest;
import test.by.mozgo.handling.logic.TextLogicTest;
import test.by.mozgo.handling.reader.TextReaderTest;

/**
 * @author Andrei Mozgo
 */

@Suite.SuiteClasses({TextReaderTest.class, CompositeTest.class, TextParserTest.class, TextLogicTest.class, ClientTest.class})
@RunWith(Suite.class)
public class SuiteTest {
}
