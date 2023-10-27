package sol;

import src.IEdge;
import src.IVertex;

import java.util.HashSet;
import java.util.Set;

/**
 * A City class representing the vertex of a travel graph
 */
public class City implements IVertex<Transport> {

    /**
     * Constructor for a City
     * @param name The name of the city
     */
    public City(String name) {
        // TODO: implement this method
    }

    /**
     * Gets outgoing edges from this vertex
     *
     * @return Set of outgoing edges
     */
    @Override
    public Set<Transport> getOutgoing() {
        // TODO: implement this method
        return null;
    }

    /**
     * Adds an outgoing edge to this vertex
     *
     * @param outEdge the outgoing edge
     */
    @Override
    public void addOut(Transport outEdge) {
        // TODO: implement this method
    }

    @Override
    public String toString() {
        return "fixme"; // TODO
    }
}
