package sol;

import src.IBFS;
import src.IEdge;
import src.IGraph;
import src.IVertex;

import java.util.List;

/**
 * Implementation for BFS, implements IBFS interface
 * @param <V> The type of the vertices
 * @param <E> The type of the edges
 */
public class BFS<V extends IVertex<E>, E extends IEdge<V>> implements IBFS<V, E> {
    
    /**
     * Returns the path from start to end.
     *
     * @param graph the graph including the vertices
     * @param start the start vertex
     * @param end   the end vertex
     * @return a list of edges starting from the start to the end
     */
    @Override
    public List<E> getPath(IGraph<V, E> graph, V start, V end) {
        // TODO: implement the getPath method!
        return null;
    }

    // TODO: feel free to add your own methods here!
    // hint: maybe you need to get a City by its name
}
