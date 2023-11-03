package sol;

import src.IDijkstra;
import src.IEdge;
import src.IGraph;
import src.IVertex;

import java.util.*;
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
        HashMap<V,E> cameFrom = new HashMap<V,E>();
        HashMap<V,Double> cityCost  = new HashMap<V,Double>();
        Comparator<V> leastCostCity = (city1,city2) -> {
            return Double.compare(cityCost.get(city1),cityCost.get(city2));
        };
        PriorityQueue<V> toCheckQueue = new PriorityQueue<V>(leastCostCity);

        for (V city: graph.getVertices()){
            if (city.toString().equals(source.toString())){
                cityCost.put(source,0.0);
            }
            else {
                cityCost.put(city, Double.POSITIVE_INFINITY);
            }
            toCheckQueue.add(city);
        }


        while(!toCheckQueue.isEmpty()){
            V checkingCity = toCheckQueue.poll();
            for (E neighbourEdge : checkingCity.getOutgoing()){
                if (cityCost.get(checkingCity)  + edgeWeight.apply(neighbourEdge) < cityCost.get(neighbourEdge.getTarget())){
                    cityCost.put((neighbourEdge.getTarget()), (cityCost.get(checkingCity)  + edgeWeight.apply(neighbourEdge)));
                    cameFrom.put(neighbourEdge.getTarget(), neighbourEdge);
                }
            }

        }
        ArrayList<E> retList = new ArrayList<E>();
        V checking = destination ;
        while(checking != source) {
            E newEdge = cameFrom.get(checking);
            retList.add(0, newEdge);
            checking = newEdge.getSource();
        }


        return retList;

    }

    // TODO: feel free to add your own methods here!
}
