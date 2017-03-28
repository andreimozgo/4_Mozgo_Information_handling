package test.by.mozgo.handling.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.by.mozgo.handling.builder.BusBuilderTest;
import test.by.mozgo.handling.reader.RouteReaderTest;

/**
 * @author Andrei Mozgo
 */

@Suite.SuiteClasses({RouteReaderTest.class, BusBuilderTest.class})
@RunWith(Suite.class)
public class SuiteTest {
}
