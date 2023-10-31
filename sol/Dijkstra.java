package sol;

import src.IDijkstra;
import src.IEdge;
import src.IGraph;
import src.IVertex;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Implementation for Dijkstra's algorithm
 *
 * @param <V> the type of the vertices
 * @param <E> the type of the edges
 */
public class Dijkstra<V extends IVertex<E>, E extends IEdge<V>> implements IDijkstra<V, E> {


    /**
     * Finds the lowest cost path from source to destination.
     *
     * @param graph       the graph including the vertices
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param edgeWeight  the weight of an edge
     * @return a list of edges from source to destination
     */
    @Override
    public List<E> getShortestPath(IGraph<V, E> graph, V source, V destination,
                                   Function<E, Double> edgeWeight) {
        // TODO: implement the getShortestPath method!
        //initialize bestDistance map
        HashMap<V, Double> bestDistance = new HashMap<>();
        Set<V> citySet = graph.getVertices();
        for (V city : citySet) {
            bestDistance.put(city, Double.POSITIVE_INFINITY);
        }
        //if we put something that's already there, does it update?
        bestDistance.put(source, (double) 0);
        //initalize cameFrom map
        //initialize PriorityQueue with comparator based on values in bestDistance map

        // when you get to using a PriorityQueue, remember to remove and re-add a vertex to the
        // PriorityQueue when its priority changes (based on distance from source)
        return null;
    }

    // TODO: feel free to add your own methods here!
}
