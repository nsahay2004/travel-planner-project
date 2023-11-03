package sol;

import src.IBFS;
import src.IEdge;
import src.IGraph;
import src.IVertex;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        LinkedList<V> toCheck  = new LinkedList<V>();
        HashSet<V> visited = new HashSet<V>();
        HashMap<V,E> cameFrom = new HashMap<V,E>();
        toCheck.add(start);
        visited.add(start);

        while(!toCheck.isEmpty()) {
            V checkingVertex = toCheck.removeFirst();
            if(checkingVertex.equals(end)){
                ArrayList<E> retList = new ArrayList<E>();
                V checking = end;
                while(checking != start){
                    E newEdge = cameFrom.get(checking);
                    retList.add(0,newEdge);
                    checking  = newEdge.getSource();
                }
                return retList;
            }
            for (E neighbourEdge : checkingVertex.getOutgoing()){
                if (!visited.contains(neighbourEdge.getTarget())){
                    visited.add(graph.getEdgeTarget(neighbourEdge));
                    toCheck.add(graph.getEdgeTarget(neighbourEdge));
                    cameFrom.put(graph.getEdgeTarget(neighbourEdge), neighbourEdge);

                }
            }
        }
        return new ArrayList<>();

    }

    // TODO: feel free to add your own methods here!
    // hint: maybe you need to get a City by its name
}
