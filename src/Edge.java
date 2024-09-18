import java.lang.RuntimeException;

/**
 * The class Edge aims at physically coding edges as object
 */
public class Edge implements Comparable<Edge>{
    /**
     * The node from where the edge starts
     */
	private Node from;
    /**
     * The node where the edge is going to
     */
    private Node to;
    /**
     * The weight of the edge
     * 
     * 0 and negative values are allowed
     * 
     * Can be null if edge is not weighted
     */
    private Integer weight;
    /**
     * 
     */
    private Graph graphHolder;

    /**
     * Constructor for a non weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     */
    Edge(Graph graphHolder, Node from, Node to) {  //
        if(from == null || to == null || from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.from = from;
        this.to = to;
        weight = null;
    }

    /**
     * Constructor for a weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     * @param weight the weight of the Edge
     */
    Edge(Node from, Node to, int weight) {
        if(from == null || to == null || from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Constructor by ids for a non-weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     */
    Edge(int from, int to) {
        if(from == null || to == null || from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.from = from;
        this.to = to;
        this.weight = null;
    }

    /**
     * Constructor by ids for a weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     * @param weight the weight of the Edge
     */
    Edge(int from, int to, int weight) {
        if(from == null || to == null || from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Check if current Edge is equal to another object
     * @param obj another object
     * @return true if the object is an edge, has the same Node @from and @to and the same weight if weighted
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        
        Edge other = (Edge) obj;

        if (from == other.from && to == other.to  && 
        ((weight == null && other.weight == null) || weight == other.weight)) {
            return true;
        }

        return false;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    /**
     * 
     * @param o
     * @return
     */
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
	}

    // TODO Implement a Comparable<Edge> interface
}
