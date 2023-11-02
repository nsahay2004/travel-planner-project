package sol;

import src.IEdge;
import src.IVertex;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * A City class representing the vertex of a travel graph
 */
public class City implements IVertex<Transport> {

    private  String cityName;

    private HashSet<Transport> transportEdges;

    /**
     * Constructor for a City
     * @param name The name of the city
     */
    public City(String name) {
        this.cityName = name;
        this.transportEdges = new HashSet<Transport>();
        // TODO: implement this method
    }


    public String getCityName(){
        return this.cityName;
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
        if (!this.transportEdges.contains(outEdge)) {
            this.transportEdges.add(outEdge);
            // TODO: implement this method
        }
    }

    @Override
    public String toString() {
        return this.cityName;
        // TODO
    }


}
