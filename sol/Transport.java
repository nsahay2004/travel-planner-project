package sol;

import src.IEdge;
import src.ITransport;
import src.IVertex;
import src.TransportType;

/**
 * A Transport class representing the edge of a travel graph
 */
public class Transport implements IEdge<City>, ITransport {

    private City source;

    private City destination;

    private TransportType type;

   private double price;

    private double minutes;

    /**
     * Constructor for Transport
     * @param source  Source city (for this edge)
     * @param destination Destination city (for this edge)
     * @param type Type/method of transport
     * @param price The price
     * @param minutes The time in minutes
     */
    public Transport(City source, City destination, TransportType type, double price,
                     double minutes) {
        // TODO: implement this method
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.minutes = minutes;
    }


    /**
     * getter method for source city
     * TODO: maybe we can just return the name, or otherwise protect the reference
     * @return city at the source of the edge
     */
    @Override
    public City getSource() {
        return this.source;
    }

    /**
     * method for grabbing the target destination
     * TODO: practice defensive programming here later
     * @return the destination city for this edge
     */
    @Override
    public City getTarget() {
        return this.destination;
    }

    /**
     * Gets the cost of this transport
     * TODO: practice defensive programming here
     * @return the price
     */
    @Override
    public double getPrice() {
        return this.price;
    }


    /**
     * Gets the length this transport is supposed to take
     * TODO: practice defensive programming
     * @return the time as a double
     */
    @Override
    public double getMinutes() {
        return this.minutes;
    }

    /**
     * Gets the type of this transport, as a String ("Bus", "Plane", etc)
     *
     * @return the transport
     */
    @Override
    public String getType() {
        return this.type.toString();
    }

    @Override
    public String toString() {
        return this.getSource().toString() + " -> " + this.getTarget().toString() +
                ", Type: " + this.getType() +
                ", Cost: $" + this.getPrice() +
                ", Duration: " + this.getMinutes() + " minutes";
    }
}
