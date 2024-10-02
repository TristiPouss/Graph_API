
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

    @Override
    public UndirectedGraph getTransitiveClosure() {
        return (UndirectedGraph) super.getTransitiveClosure();
    }

    /*@Override
    public static UndirectedGraph fromDotFile(String filename) {
        return (UndirectedGraph) super.fromDotFile(filename);
    }*/
}
