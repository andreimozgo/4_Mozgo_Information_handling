package by.mozgo.handling.main;

import by.mozgo.handling.builder.RouteBuilder;
import by.mozgo.handling.entity.Bus;
import by.mozgo.handling.reader.RouteReader;
import by.mozgo.handling.singleton.PassengersCount;
import by.mozgo.handling.singleton.TimeTable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class RouteMain {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        RouteBuilder.generateRoutes(RouteReader.readData("data/input.txt"));
        int numberOfBuses = TimeTable.getInstance().getSize();
        List<Bus> buses = new ArrayList<>();
        for (int i = 1; i <= numberOfBuses; i++) {
            Bus bus = new Bus();
            buses.add(bus);
            bus.start();
        }
        buses.stream().forEach((bus) -> {
            try {
                bus.join();
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, "Thread interrupted. {}", e);
            }
        });
        LOGGER.log(Level.INFO, "{} passengers were transported.", PassengersCount.getInstance().getPassengersCount());
    }
}