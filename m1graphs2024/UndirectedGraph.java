import java.util.*;

/**
 * An extend of the class Graph to represent an undirected graph
 * @author Tristan de Saint Gilles
 * @author Renaud Joffrin
 */
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

    /* API */

    @Override
    public UndirectedGraph getTransitiveClosure() {
        UndirectedGraph transClosure = new UndirectedGraph();

        for (Node u : getAllNodes()){
            List<Node> visited = new ArrayList<>();
            Stack<Node> toVisit = new Stack<>();
            toVisit.add(u);
            transClosure.addNode(u.getId());
            while (!toVisit.isEmpty()) {
                Node current = toVisit.pop();
                if(!visited.contains(current)){
                    for (Edge v : getOutEdges(current)){ // here 'v' is an edge but the v from the algorithm is actually the @to of that edge  
                        if(!transClosure.existsEdge(u.getId(), v.to().getId())){
                            transClosure.addEdge(u.getId(), v.to().getId());
                            toVisit.remove(v.to());
                            toVisit.add(v.to());
                        }
                    }
                    visited.add(current);
                }
            }
        }

        return transClosure;
    }

    public static UndirectedGraph fromDotFile(String filename) {
        return (UndirectedGraph) Graph.fromDotFile(filename);
    }

    public static UndirectedGraph fromDotFile(String filename, String extension) {
        return null;
    }
}
