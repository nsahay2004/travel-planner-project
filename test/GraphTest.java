package test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import sol.City;
import sol.Transport;
import sol.TravelController;
import sol.TravelGraph;
import src.TransportType;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;
import org.junit.FixMethodOrder;
import org.junit.Before;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Your Graph method tests should all go in this class!
 * The test we've given you will pass, but we still expect you to write more tests using the
 * City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 * TODO: Recreate the test below for the City and Transport classes
 * TODO: Expand on your tests, accounting for basic cases and edge cases
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GraphTest {
    private SimpleGraph graph;
    private TravelGraph travelGraph;

    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private City girard;
    private City golden;
    private City clovis;


    private SimpleEdge edgeAB;
    private SimpleEdge edgeBC;
    private SimpleEdge edgeCA;
    private SimpleEdge edgeAC;
    private Transport girardClovis;
    private Transport clovisGolden;
    private Transport goldenGirard;
    private Transport girardGolden;

    private City london;


    /**
     * Creates a simple graph.
     * You'll find a similar method in each of the Test files.
     * Normally, we'd like to use @Before, but because each test may require a different setup,
     * we manually call the setup method at the top of the test.
     *
     * TODO: create more setup methods!
     */


    @Before
    public void createNewGraph() {
        this.graph = new SimpleGraph();

        this.a = new SimpleVertex("A");
        this.b = new SimpleVertex("B");
        this.c = new SimpleVertex("C");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);

        // create and insert edges
        this.edgeAB = new SimpleEdge(1, this.a, this.b);
        this.edgeBC = new SimpleEdge(1, this.b, this.c);
        this.edgeCA = new SimpleEdge(1, this.c, this.a);
        this.edgeAC = new SimpleEdge(1, this.a, this.c);

        this.graph.addEdge(this.a, this.edgeAB);
        this.graph.addEdge(this.b, this.edgeBC);
        this.graph.addEdge(this.c, this.edgeCA);
        this.graph.addEdge(this.a, this.edgeAC);

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
     * Sample test for the graph. Tests that the number of vertices and the vertices in the graph are what we expect.
     */
    @Test
    public void testGetVertices() {
        // test getVertices to check this method AND insertVertex
        assertEquals(this.travelGraph.getVertices().size(), 4);
        assertTrue(this.travelGraph.getVertices().contains(this.clovis));
        assertTrue(this.travelGraph.getVertices().contains(this.girard));
        assertTrue(this.travelGraph.getVertices().contains(this.golden));
        assertFalse(this.travelGraph.getVertices().contains(new City("Boston")));
        City boston = new City ("Boston");
        this.travelGraph.addVertex(boston);
        assertTrue(this.travelGraph.getVertices().contains(boston));
    }

    @Test
    public void testGetEdgeSourceAndTarget(){
        assertEquals("Clovis", this.clovisGolden.getSource().toString());
        assertEquals("Girard", this.girardGolden.getSource().toString());
        assertEquals("Golden", this.clovisGolden.getTarget().toString());
        assertEquals("Golden", this.girardGolden.getTarget().toString());
    }

    @Test
    public void testGetOutgoingEdges(){
        assertEquals(this.travelGraph.getOutgoingEdges(this.clovis).size(),1);
        assertTrue(this.travelGraph.getOutgoingEdges(this.clovis).contains(this.clovisGolden));
        assertEquals(this.travelGraph.getOutgoingEdges(this.girard).size(),2);
        assertTrue(this.travelGraph.getOutgoingEdges(this.girard).contains(this.girardGolden));
        assertEquals(this.travelGraph.getOutgoingEdges(this.london).size(),0);
        Transport clovisGirard = new Transport(this.clovis, this.girard, TransportType.PLANE, 12, 87);
        assertFalse(this.travelGraph.getOutgoingEdges(this.clovis).contains(clovisGirard));
        this.travelGraph.addEdge(this.clovis,clovisGirard);
        assertTrue(this.travelGraph.getOutgoingEdges(this.clovis).contains(clovisGirard));

    }

    /**
     * testing we can basically load something-- this graph has a cycle in it
     */
    @Test
    public void testLoad() {
        TravelController travelController = new TravelController();
        travelController.load("data/cities1.csv", "data/transport1.csv");
        assertEquals(3, travelController.getGraph().getVertices().size());
        assertEquals(3, travelController.getGraph().getOutgoingEdges(new City("Boston")).size());
        assertEquals(2, travelController.getGraph().getOutgoingEdges(new City("New York City")).size());
        assertEquals(2, travelController.getGraph().getOutgoingEdges(new City("Providence")).size());
    }

    /**
     * testing we can load an empty graph
     */
    @Test
    public void testLoadEmpty() {
        TravelController travelController = new TravelController();
        travelController.load("data/emptycities.csv", "data/emptytransport.csv");
        assertEquals(0, travelController.getGraph().getVertices().size());
        assertNull(travelController.getGraph().getCityByName("nasa peepo"));
    }

    /**
     * test for GetOutgoing in City
     */
    @Test
    public void testGetOutgoing() {
        this.createNewGraph();
        Set<Transport> clovisEdge = this.clovis.getOutgoing();
        assertEquals(1, clovisEdge.size());
        Set<Transport> londonEdge = this.london.getOutgoing();
        assertEquals(0, londonEdge.size());
    }

    /**
     * testing AddOut method in City
     */
    @Test
    public void testAddOut() {
        this.createNewGraph();
        Set<Transport> clovisEdge = this.clovis.getOutgoing();
        assertEquals(1, clovisEdge.size());
        Transport clovisLondon = new Transport(this.clovis, this.london, TransportType.TRAIN, 3, 3);
        this.clovis.addOut(clovisLondon);
        Set<Transport> clovisEdges = this.clovis.getOutgoing();
        assertEquals(2, clovisEdges.size());
        Set<Transport> londonEdge = this.london.getOutgoing();
        assertEquals(0, londonEdge.size());
        Transport londonClovis = new Transport(this.london, this.clovis, TransportType.TRAIN, 3, 3);
        this.london.addOut(londonClovis);
        assertEquals(1, londonEdge.size());
        this.london.addOut(clovisLondon);
        assertEquals(1, this.london.getOutgoing().size());
    }

    /**
     * simple test for the toString method
     */
    @Test
    public void testToString() {
        assertEquals("Clovis", this.clovis.toString());
    }
}
