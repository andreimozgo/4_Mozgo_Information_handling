package by.mozgo.handling.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Andrei Mozgo
 */
public class TextReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public static String readData(String filename) {
        if (filename == null) {
            LOGGER.log(Level.FATAL, "Input filename is null!");
            throw new RuntimeException("Input filename is null!");
        }
        StringBuilder text = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(new File(filename)))) {
            String line = in.readLine();
            text.append(line);
            while ((line = in.readLine()) != null) {
                text.append("\n");
                text.append(line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Input file not found! {}", e);
            throw new RuntimeException(e + "Input file not found!", e);
        }
        if (text.toString().isEmpty()) {
            LOGGER.log(Level.FATAL, "Input file empty! ");
            throw new RuntimeException("Input file empty!");
        }

        return text.toString();
    }
}
