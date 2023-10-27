package sol;

import src.IEdge;
import src.ITransport;
import src.IVertex;
import src.TransportType;

/**
 * A Transport class representing the edge of a travel graph
 */
public class Transport implements IEdge<City>, ITransport {

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
    }


    @Override
    public City getSource() {
        // TODO: implement this method
        return null;
    }

    @Override
    public City getTarget() {
        // TODO: implement this method
        return null;
    }

    /**
     * Gets the cost of this transport
     *
     * @return the price
     */
    @Override
    public double getPrice() {
        // TODO: implement this method
        return 0;
    }


    /**
     * Gets the type of this transport, as a String ("Bus", "Plane", etc)
     *
     * @return the transport
     */
    @Override
    public double getMinutes() {
        // TODO: implement this method
        return 0;
    }

    /**
     * Gets the type of this transport, as a String ("Bus", "Plane", etc)
     *
     * @return the transport
     */
    @Override
    public String getType() {
        // TODO: implement this method
        return null;
    }

    @Override
    public String toString() {
        return this.getSource().toString() + " -> " + this.getTarget().toString() +
                ", Type: " + this.getType() +
                ", Cost: $" + this.getPrice() +
                ", Duration: " + this.getMinutes() + " minutes";
    }
}
