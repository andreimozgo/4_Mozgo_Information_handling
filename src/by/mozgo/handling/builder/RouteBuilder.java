package by.mozgo.handling.builder;

import by.mozgo.handling.entity.BusStop;
import by.mozgo.handling.entity.BusStopName;
import by.mozgo.handling.entity.Route;
import by.mozgo.handling.singleton.TimeTable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Mozgo
 */
public class RouteBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int NUMBER_OF_BUS_STOP_PARAMETERS = 4;
    private static final int ENTITY_IDENTIFIER = 0;
    private static final int ROUTE_NUMBER_INDEX = 1;
    private static final int FIRST_ROUTE_PARAMETER_INDEX = 2;

    public static void generateRoutes(List<String> inputLines) {
        List<BusStop> listBusStops = new ArrayList<>();
        for (String line : inputLines) {
            String[] routeParameters = line.split("\\s");
            switch (routeParameters[ENTITY_IDENTIFIER].toUpperCase()) {
                case "STOP":
                    if (routeParameters.length == NUMBER_OF_BUS_STOP_PARAMETERS) {
                        try {
                            BusStopName busStopName = BusStopName.valueOf(routeParameters[0] + routeParameters[1]);
                            int capacity = Integer.parseInt(routeParameters[2]);
                            int passengersOnStop = Integer.parseInt(routeParameters[3]);
                            BusStop busStop = new BusStop(busStopName, capacity, passengersOnStop);
                            listBusStops.add(busStop);
                            LOGGER.log(Level.INFO, "Added BusStop: {}, capacity: {}, passengers: {}", busStopName,
                                    capacity, passengersOnStop);
                        } catch (NumberFormatException e) {
                            LOGGER.log(Level.ERROR, "Incorrect symbol in line. {}", e);
                        }
                    } else {
                        LOGGER.log(Level.ERROR, "Invalid number of parameters in line. {}");
                    }
                    break;
                case "ROUTE":
                    List<BusStop> busStops = new ArrayList<>();
                    int number = Integer.parseInt(routeParameters[ROUTE_NUMBER_INDEX]);
                    for (int i = FIRST_ROUTE_PARAMETER_INDEX; i < routeParameters.length; i++) {
                        int busStopNameIndex = Integer.parseInt(routeParameters[i]);
                        for (BusStop busStop : listBusStops) {
                            if (busStopNameIndex == busStop.getName().ordinal()) {
                                busStops.add(busStop);
                            }
                        }
                    }
                    Route route = new Route(number, busStops);
                    TimeTable.getInstance().addRoute(route);
                    LOGGER.log(Level.INFO, "Added handling: " + route);
                    break;
                default:
                    LOGGER.log(Level.ERROR, "Invalid line.");
            }
        }
        if (listBusStops.isEmpty() || TimeTable.getInstance().isEmpty()) {
            LOGGER.log(Level.FATAL, "Incorrect input file. Data doesn't enough to run the application.");
            throw new RuntimeException("Incorrect input file. Data doesn't enough to run the application.");
        }
    }
}