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
        //Initialize the toCheck List for  and the visited HashSet for visited cities.
        //Creates a hashmap cameFrom from cities to transport to store how we get to a city (what transport edge)
        LinkedList<V> toCheck  = new LinkedList<V>();
        HashSet<V> visited = new HashSet<V>();
        HashMap<V,E> cameFrom = new HashMap<V,E>();
        toCheck.add(start);
        visited.add(start);

        // creates a while loop that keeps updating the cameFrom map until toCheck is empty
        // if the vertex we are checking is the end we are trying to reach , it does the backtracking to get the path
        // if not, it keeps updating the cameFrom, toCheck and visited

        while(!toCheck.isEmpty()) {
            V checkingVertex = toCheck.removeFirst();
            if(checkingVertex.equals(end)){
                ArrayList<E> retList = new ArrayList<E>();
                V checking = end;
                while(checking != start){
                    E newEdge = cameFrom.get(checking);
                    retList.add(0,newEdge);
                    checking  = graph.getEdgeSource(newEdge);
                }
                return retList;
            }
            for (E neighbourEdge : checkingVertex.getOutgoing()){
                if (!visited.contains(graph.getEdgeTarget(neighbourEdge))){
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
