package by.mozgo.handling.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class RouteReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public static List<String> readData(String filename) {
        if (filename == null) {
            LOGGER.log(Level.FATAL, "Input filename is null!");
            throw new RuntimeException("Input filename is null!");
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(new File(filename)))) {
            String s;
            while ((s = in.readLine()) != null) {
                lines.add(s);
            }
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Input file not found! {}", e);
            throw new RuntimeException(e + "Input file not found!", e);
        }
        if (lines.isEmpty()) {
            LOGGER.log(Level.FATAL, "Input file empty! ");
            throw new RuntimeException("Input file empty!");
        }
        return lines;
    }
}
