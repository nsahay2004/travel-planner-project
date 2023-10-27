package sol;

import src.IGraph;

import java.util.Set;

/**
 * Implementation for TravelGraph
 */
public class TravelGraph implements IGraph<City, Transport> {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex
     */
    @Override
    public void addVertex(City vertex) {
        // TODO: implement this method!
    }

    /**
     * Adds an edge to the graph.
     *
     * @param origin the origin of the edge.
     * @param edge
     */
    @Override
    public void addEdge(City origin, Transport edge) {
        // TODO: implement this method!
    }

    /**
     * Gets a set of vertices in the graph.
     *
     * @return the set of vertices
     */
    @Override
    public Set<City> getVertices() {
        // TODO: implement this method!
        return null;
    }

    /**
     * Gets the source of an edge.
     *
     * @param edge the edge
     * @return the source of the edge
     */
    @Override
    public City getEdgeSource(Transport edge) {
        // TODO: implement this method!
        return null;
    }

    /**
     * Gets the target of an edge.
     *
     * @param edge the edge
     * @return the target of the edge
     */
    @Override
    public City getEdgeTarget(Transport edge) {
        // TODO: implement this method!
        return null;
    }

    /**
     * Gets the outgoing edges of a vertex.
     *
     * @param fromVertex the vertex
     * @return the outgoing edges from that vertex
     */
    @Override
    public Set<Transport> getOutgoingEdges(City fromVertex) {
        // TODO: implement this method!
        return null;
    }

    // TODO: feel free to add your own methods here!
    // hint: maybe you need to get a City by its name
}