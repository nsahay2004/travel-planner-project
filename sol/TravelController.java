package sol;

import src.ITravelController;
import src.TransportType;
import src.TravelCSVParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Implementation for TravelController
 */
public class TravelController implements ITravelController<City, Transport> {

    // Why is this field of type TravelGraph and not IGraph?
    // Are there any advantages to declaring a field as a specific type rather than the interface?
    // If this were of type IGraph, could you access methods in TravelGraph not declared in IGraph?
    // Hint: perhaps you need to define a method!
    private TravelGraph graph;

    /**
     * Constructor for TravelController
     */
    public TravelController() {
    }

    /**
     * Loads CSVs into the app.
     *
     * @param citiesFile    the filename of the cities csv
     * @param transportFile the filename of the transportations csv
     * @return an informative message to be printed in the REPL
     */
    @Override
    public String load(String citiesFile, String transportFile) {
        this.graph = new TravelGraph();
        TravelCSVParser parser = new TravelCSVParser();

        Function<Map<String, String>, Void> addVertex = map -> {
            this.graph.addVertex(new City(map.get("name")));
            return null; // need explicit return null to account for Void type
        };

        Function<Map<String, String>, Void> addEdge = map -> {
            if (this.graph.travelMap.containsKey(map.get("origin"))) {
                City originCity = new City(map.get("origin"));
                City destinationCity = new City(map.get("destination"));
                TransportType type = TransportType.fromString(map.get("type"));
                double price = Double.parseDouble(map.get("price"));
                double duration = Double.parseDouble(map.get("duration"));
                this.graph.addEdge(originCity, new Transport(originCity, destinationCity, type, price, duration));
            }
            return null;
        };

        // TODO: create a variable that is of type Function<Map<String, String>, Void>
        //       that will build connections between nodes in a graph. Keep in mind
        //       that the input to this function is a hashmap that relates the
        //       COLUMN NAMES of the csv to the VALUE IN THE COLUMN of the csv.
        //       It might be helpful to write a method in this class that takes the
        //       information from the csv needed to create an edge and uses that to
        //       build the edge on the graph.

        try {
            // pass in string for CSV and function to create City (vertex) using city name
            parser.parseLocations(citiesFile, addVertex);
        } catch (IOException e) {
            return "Error parsing file: " + citiesFile;
        }

        // TODO: call parseTransportation with the second Function variable as an argument and
        //  the right file

        try {
            parser.parseTransportation(transportFile, addEdge);
        } catch (IOException e) {
            return "Error parsing file: " + transportFile;
        }

        // hint: note that parseLocations and parseTransportation can
        //       throw IOExceptions. How can you handle these calls cleanly?

        return "Successfully loaded cities and transportation files.";
    }


    /**
     * Finds the fastest route in between two cities
     *
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return the path starting from the source to the destination,
     * or empty if there is none
     */

    Function <Transport,Double> timeEdgeWeight = t -> t.getMinutes();
    Function <Transport, Double> priceEdgeWeight = t -> t.getPrice();
    @Override
    public List<Transport> fastestRoute(String source, String destination) {


        City citySource = this.graph.getCityByName(source);
        City cityDestination = this.graph.getCityByName(destination);
        Dijkstra<City,Transport> applyMethod1 = new Dijkstra<City,Transport>();



        return applyMethod1.getShortestPath(this.graph,citySource,cityDestination,timeEdgeWeight);
    }
// TODO: implement this method!
    /**
     * Finds the cheapest route in between two cities
     *
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return the path starting from the source to the destination,
     * or empty if there is none
     */
    @Override
    public List<Transport> cheapestRoute(String source, String destination) {

        City citySource = this.graph.getCityByName(source);
        City cityDestination = this.graph.getCityByName(destination);
        Dijkstra<City,Transport> applyMethod2 = new Dijkstra<City,Transport>();


        return applyMethod2.getShortestPath(this.graph,citySource,cityDestination,priceEdgeWeight);


    }


    /**
     * Finds the most direct route in between two cities
     *
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return the path starting from the source to the destination,
     * or empty if there is none
     */
    @Override
    public List<Transport> mostDirectRoute(String source, String destination) {
        City citySource = this.graph.getCityByName(source);
        City cityDestination = this.graph.getCityByName(destination);
        BFS<City,Transport>  applyMethod3 = new BFS<City,Transport>();

        return applyMethod3.getPath(this.graph,citySource,cityDestination);

        // TODO: implement this method!

    }

    /**
     * Returns the graph stored by the controller
     *
     * NOTE: You __should not__ be using this in your implementation, this is purely meant to be used for the visualizer
     *
     * @return The TravelGraph currently stored in the TravelController
     */
    public TravelGraph getGraph() {
        return this.graph;
    }
}
