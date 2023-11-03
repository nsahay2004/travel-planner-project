package test;

import org.junit.Test;
import sol.BFS;
import sol.City;
import sol.Transport;
import sol.TravelGraph;
import src.TransportType;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Your BFS tests should all go in this class!
 * The test we've given you will pass if you've implemented BFS correctly, but we still expect
 * you to write more tests using the City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 * TODO: Recreate the test below for the City and Transport classes
 * TODO: Expand on your tests, accounting for basic cases and edge cases
 */
public class BFSTest {

    private static final double DELTA = 0.001;

    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private SimpleVertex d;
    private SimpleVertex e;
    private SimpleVertex f;
    private SimpleGraph graph;

    private City girard;
    private City golden;
    private City clovis;
    private City london;
    private Transport girardClovis;
    private Transport clovisGolden;
    private Transport goldenGirard;
    private Transport girardGolden;
    private TravelGraph travelGraph;


    /**
     * Creates a simple graph.
     * You'll find a similar method in each of the Test files.
     * Normally, we'd like to use @Before, but because each test may require a different setup,
     * we manually call the setup method at the top of the test.
     *
     * TODO: create more setup methods!
     */
    public void makeSimpleGraph() {
        this.graph = new SimpleGraph();

        this.a = new SimpleVertex("a");
        this.b = new SimpleVertex("b");
        this.c = new SimpleVertex("c");
        this.d = new SimpleVertex("d");
        this.e = new SimpleVertex("e");
        this.f = new SimpleVertex("f");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);
        this.graph.addVertex(this.d);
        this.graph.addVertex(this.e);
        this.graph.addVertex(this.f);

        this.graph.addEdge(this.a, new SimpleEdge(1, this.a, this.b));
        this.graph.addEdge(this.b, new SimpleEdge(1, this.b, this.c));
        this.graph.addEdge(this.c, new SimpleEdge(1, this.c, this.e));
        this.graph.addEdge(this.d, new SimpleEdge(1, this.d, this.e));
        this.graph.addEdge(this.a, new SimpleEdge(100, this.a, this.f));
        this.graph.addEdge(this.f, new SimpleEdge(100, this.f, this.e));
    }

    /**
     * makes graph with regular edges and vertexes
     */
    public void makeMafiaGraph() {
        this.travelGraph = new TravelGraph();

        this.clovis = new City("Clovis");
        this.girard = new City("Girard");
        this.golden = new City("Golden");
        this.london = new City("London");

        this.travelGraph.addVertex(this.clovis);
        this.travelGraph.addVertex(this.girard);
        this.travelGraph.addVertex(this.golden);
        this.travelGraph.addVertex(this.london);

        //i am not testing if the transport connects to the right object
        //maybe we should????
        //but maybe we deal with this when we create our graph
        this.clovisGolden = new Transport(new City("Clovis"), new City("Golden"), TransportType.TRAIN, 20, 240);
        this.girardClovis = new Transport(this.girard, new City("Clovis"), TransportType.PLANE, 100, 20);
        this.girardGolden = new Transport(new City("Girard"), this.golden, TransportType.BUS, 10, 100);
        this.goldenGirard = new Transport(this.golden, this.girard, TransportType.PLANE, 40, 40);

        this.travelGraph.addEdge(this.clovis, this.clovisGolden);
        this.travelGraph.addEdge(new City("Girard"), this.girardGolden);
        this.travelGraph.addEdge(this.girard, this.girardClovis);
        this.travelGraph.addEdge(this.golden, this.goldenGirard);

    }

    /**
     * A sample test that tests BFS on a simple graph. Checks that running BFS gives us the path we expect.
     */
    @Test
    public void testBasicBFS() {
        this.makeSimpleGraph();
        BFS<SimpleVertex, SimpleEdge> bfs = new BFS<>();
        List<SimpleEdge> path = bfs.getPath(this.graph, this.a, this.e);
        assertEquals(SimpleGraph.getTotalEdgeWeight(path), 200.0, DELTA);
        assertEquals(path.size(), 2);
    }

    // TODO: write more tests +

    @Test
    public void testBostonBFS() {
        this.makeMafiaGraph();
        BFS<City, Transport> bfs = new BFS<>();
        List<Transport> path = bfs.getPath(this.travelGraph, this.clovis, this.girard);
        assertEquals(2, path.size());
        List<Transport> path2 = bfs.getPath(this.travelGraph, this.golden, this.girard);
        assertEquals(1, path2.size());
        List<Transport> path3 = bfs.getPath(this.travelGraph, this.london, this.golden);
        assertEquals(0, path3.size());
    }
}
