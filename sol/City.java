package sol;

import src.IEdge;
import src.IVertex;

import java.util.HashSet;
import java.util.Set;

/**
 * A City class representing the vertex of a travel graph
 */
public class City implements IVertex<Transport> {

    public String cityName;

    public HashSet<Transport> transportEdges;

    /**
     * Constructor for a City
     * @param name The name of the city
     */
    public City(String name) {
        this.cityName = name;
        this.transportEdges = new HashSet<Transport>();
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
        return this.transportEdges;
    }

    /**
     * Adds an outgoing edge to this vertex
     *
     * @param outEdge the outgoing edge
     */
    @Override
    public void addOut(Transport outEdge) {
        this.transportEdges.add(outEdge);
        // TODO: implement this method
    }

    @Override
    public String toString() {
        String edges = "";
        for(Transport t: this.transportEdges){
           edges =  edges + t.destination.cityName + ", ";
        }

        return this.cityName + " goes to: " + edges; // TODO
    }
}
