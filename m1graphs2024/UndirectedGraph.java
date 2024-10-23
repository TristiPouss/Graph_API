import java.util.*;

public class UndirectedGraph extends Graph{
    /* Attributes */

    /*
     * The Adjencent Edge List implemented as a Map
     */
    private Map<Node, List<Edge>> adjEdList;

    /* Constructors */

    /**
     * Constructor for empty graph
     */
    public UndirectedGraph() {
        super();
    }
    
    /**
     * Constructor from Successor array
     * @param sa unknown number of int as successor array
     */
    public UndirectedGraph(int ... sa) {
        super(sa);
    }

    // Redefine Add Edge to have both edge and symmetric in the undirected

    @Override
    public UndirectedGraph getTransitiveClosure() {
        return (UndirectedGraph) super.getTransitiveClosure();
    }

    public static UndirectedGraph fromDotFile(String filename) {
        return (UndirectedGraph) Graph.fromDotFile(filename);
    }

    public static UndirectedGraph fromDotFile(String filename, String extension) {
        return null;
    }
}
