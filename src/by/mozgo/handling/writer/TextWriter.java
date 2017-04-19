package by.mozgo.handling.writer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Andrei Mozgo. 2017.
 */
public class TextWriter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String OUTPUT_FILE = "data/output.txt";

    public static void writeData(String text) {

        if (text.isEmpty()) {
            LOGGER.log(Level.FATAL, "Output data is empty! ");
            throw new RuntimeException("Output data is empty! ");
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            out.write(text);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "File output problem {}", e);
            throw new RuntimeException("File output problem! " + e, e);
        }
    }
}
