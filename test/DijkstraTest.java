package test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import sol.Dijkstra;
import src.IDijkstra;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;
import sol.TravelGraph;
import sol.TravelController;
import sol.City;
import sol.Transport;

import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Your Dijkstra's tests should all go in this class!
 * The test we've given you will pass if you've implemented Dijkstra's correctly, but we still
 * expect you to write more tests using the City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 * TODO: Recreate the test below for the City and Transport classes
 * TODO: Expand on your tests, accounting for basic cases and edge cases
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DijkstraTest {

    private static final double DELTA = 0.001;

    private SimpleGraph graph;
    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private SimpleVertex d;
    private SimpleVertex e;

    private TravelController controller1;
    private TravelController controller2;

    private TravelController controller3;




    public void setup1(){
        this.controller1 = new TravelController();
        this.controller1.load("data/cities1.csv","data/transport1.csv");
         TravelGraph testGraph1 = this.controller1.getGraph();

    }

    public void setup2(){
        this.controller2 = new TravelController();
        this.controller2.load("data/cities2.csv","data/transport2.csv");
        TravelGraph testGraph2 = this.controller2.getGraph();
    }

    public void setup3(){
        this.controller3 = new TravelController();
        this.controller3.load("data/testcities.csv", "data/testtransports.csv");
        TravelGraph testGraph3 = this.controller3.getGraph();
    }

    /**
     * Creates a simple graph.
     * You'll find a similar method in each of the Test files.
     * Normally, we'd like to use @Before, but because each test may require a different setup,
     * we manually call the setup method at the top of the test.
     *
     * TODO: create more setup methods!
     */
    private void createSimpleGraph() {
        this.graph = new SimpleGraph();

        this.a = new SimpleVertex("a");
        this.b = new SimpleVertex("b");
        this.c = new SimpleVertex("c");
        this.d = new SimpleVertex("d");
        this.e = new SimpleVertex("e");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);
        this.graph.addVertex(this.d);
        this.graph.addVertex(this.e);

        this.graph.addEdge(this.a, new SimpleEdge(100, this.a, this.b));
        this.graph.addEdge(this.a, new SimpleEdge(3, this.a, this.c));
        this.graph.addEdge(this.a, new SimpleEdge(1, this.a, this.e));
        this.graph.addEdge(this.c, new SimpleEdge(6, this.c, this.b));
        this.graph.addEdge(this.c, new SimpleEdge(2, this.c, this.d));
        this.graph.addEdge(this.d, new SimpleEdge(1, this.d, this.b));
        this.graph.addEdge(this.d, new SimpleEdge(5, this.e, this.d));
    }

    /**
     * A sample test that tests Dijkstra's on a simple graph. Checks that the shortest path using Dijkstra's is what we
     * expect.
     */
    @Test
    public void testSimple() {
        this.createSimpleGraph();

        IDijkstra<SimpleVertex, SimpleEdge> dijkstra = new Dijkstra<>();
        Function<SimpleEdge, Double> edgeWeightCalculation = e -> e.weight;
        // a -> c -> d -> b
        List<SimpleEdge> path =
            dijkstra.getShortestPath(this.graph, this.a, this.b, edgeWeightCalculation);
        assertEquals(6, SimpleGraph.getTotalEdgeWeight(path), DELTA);
        assertEquals(3, path.size());

        // c -> d -> b
        path = dijkstra.getShortestPath(this.graph, this.c, this.b, edgeWeightCalculation);
        assertEquals(3, SimpleGraph.getTotalEdgeWeight(path), DELTA);
        assertEquals(2, path.size());
    }

    @Test
    public void test1(){
        this.setup1();
        List<Transport> path = this.controller1.fastestRoute("Boston", "Providence");
        assertEquals(1,path.size());
        assertEquals("Boston", path.get(0).getSource().toString());
        assertEquals("Providence", path.get(0).getTarget().toString());
    }

    @Test
    public void test2(){
        this.setup1();
        List<Transport> path = this.controller1.cheapestRoute("Providence", "New York City");
        assertEquals(2, path.size());
        assertEquals(274, this.controller1.getSumofPriceWeight(path), DELTA);
        List<Transport> path1 = this.controller1.cheapestRoute("Providence", "Providence");
        assertEquals(0, path1.size());
    }

    @Test
    public void test3(){
        this.setup2();
        List<Transport> path = this.controller2.cheapestRoute("London","Washington");
        assertEquals(0, path.size());

    }

    @Test
    public void test4(){
        this.setup3();
        List<Transport> path1 = this.controller3.cheapestRoute("Delhi","Rome");
        assertEquals(3, path1.size());
        assertEquals(260, this.controller3.getSumofPriceWeight(path1), DELTA );
        List<Transport> path2 = this.controller3.fastestRoute("Delhi","Rome");
        assertEquals(2,path2.size());
        assertEquals(115, this.controller3.getSumofTimeWeight(path2), DELTA);
        List<Transport> path3 = this.controller3.cheapestRoute("Providence","Delhi");
        assertEquals(0,path3.size());

    }

    // TODO: write more tests + make sure you test all the cases in your testing plan!
}
