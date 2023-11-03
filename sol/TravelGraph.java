package sol;

import src.IGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation for TravelGraph
 */
public class TravelGraph implements IGraph<City, Transport> {

    HashMap<String, City> travelMap;

    /**
     * constructor for a graph
     */
    public TravelGraph() {
        this.travelMap = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex
     */
    @Override
    public void addVertex(City vertex) {
        if (!this.travelMap.containsKey(vertex.toString())) {
            this.travelMap.put(vertex.toString(), vertex);
        }
    }

    /**
     * Adds an edge to the graph.
     *
     * @param origin the origin of the edge.
     * @param edge
     */
    @Override
    public void addEdge(City origin, Transport edge) {
        //make sure the edge has a source of the city
        //are we assuming that the origin city currently doesn't have this edge in it
        //maybe we should throw an exception
        if (edge.getSource().toString().equals(origin.toString())) {
            if (this.travelMap.containsKey(origin.toString())) {
                City realCity = this.travelMap.get(origin.toString());
                realCity.addOut(edge);
            } else {
                origin.addOut(edge);
                this.travelMap.put(origin.toString(), origin);
            }
        }
    }

    /**
     * Gets a set of vertices in the graph.
     *
     * @return the set of vertices
     */
    @Override
    public Set<City> getVertices() {
        Set<String> nameSet = this.travelMap.keySet();
        Set<City> returnSet = new HashSet<>();
        for (String name : nameSet) {
            City namedCity = this.travelMap.get(name);
            returnSet.add(namedCity);
        }
        return returnSet;
    }

    /**
     * Gets the source of an edge.
     *
     * @param edge the edge
     * @return the source of the edge
     */
    @Override
    public City getEdgeSource(Transport edge) {
        // we assume that the city already exists

            String cityName = edge.getSource().toString();
            return this.travelMap.get(cityName);

    }

    /**
     * Gets the target of an edge.
     *
     * @param edge the edge
     * @return the target of the edge
     */
    @Override
    public City getEdgeTarget(Transport edge) {
        // we are assuming the destination exists in the map
        String cityName = edge.getTarget().toString();
        return this.travelMap.get(cityName);
    }

    /**
     * Gets the outgoing edges of a vertex.
     *
     * @param fromVertex the vertex
     * @return the outgoing edges from that vertex
     */
    @Override
    public Set<Transport> getOutgoingEdges(City fromVertex) {
        String vertexName = fromVertex.toString();
        return this.travelMap.get(vertexName).getOutgoing();

    }

    public City getCityByName(String cityName){
        return this.travelMap.get(cityName);
    }

    // TODO: feel free to add your own methods here!
    // hint: maybe you need to get a City by its name
}