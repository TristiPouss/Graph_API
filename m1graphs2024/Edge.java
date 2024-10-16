import java.util.Objects;

/**
 * The class Edge aims at physically coding edges as object
 * @author Tristan de Saint Gilles
 */
public class Edge implements Comparable<Edge>{

    /* Attributes */
    
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

    /* Constructors */

    /**
     * Constructor for a non weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     * @throws IllegalArgumentException if any of the arguments are null 
     * or if the graph of @from is different from the one of @to
     */
    public Edge(Node from, Node to, Graph graphHolder) {  //
        if(graphHolder == null || from == null || to == null || from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.graphHolder = graphHolder;
        this.from = from;
        this.to = to;
        weight = null;
    }

    /**
     * Constructor for a weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     * @param weight the weight of the Edge
     * @throws IllegalArgumentException if any of the arguments are null 
     * or if the graph of @from is different from the one of @to
     */
    public Edge(Node from, Node to, int weight, Graph graphHolder) {
        if(graphHolder == null || from == null || to == null || from.getGraph() != to.getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.graphHolder = graphHolder;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Constructor by ids for a non-weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     * @throws IllegalArgumentException if any of the arguments are null 
     * or if the graph of @from is different from the one of @to
     */
    public Edge(int from, int to, Graph graphHolder) {
        if(graphHolder == null || graphHolder.getNode(from) == null || 
        graphHolder.getNode(to) == null || graphHolder.getNode(from).getGraph() != graphHolder.getNode(to).getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.graphHolder = graphHolder;
        this.from = graphHolder.getNode(from);
        this.to = graphHolder.getNode(to);
        this.weight = null;
    }

    /**
     * Constructor by ids for a weighted Edge
     * @param from the Node where it begins
     * @param to the Node where it ends
     * @param weight the weight of the Edge
     * @throws IllegalArgumentException 
     * if any of the arguments are null or
     * if the graph of @from is different from the one of @to
     */
    public Edge(int from, int to, int weight, Graph graphHolder) {
        if(graphHolder == null || graphHolder.getNode(from) == null || 
        graphHolder.getNode(to) == null || graphHolder.getNode(from).getGraph() != graphHolder.getNode(to).getGraph()){
            throw new IllegalArgumentException("Invalid arguments for edge.");
        }
        this.graphHolder = graphHolder;
        this.from = graphHolder.getNode(from);
        this.to = graphHolder.getNode(to);
        this.weight = weight;
    }

    /* API */

    /**
     * Get node @from
     * @return a Node
     */
    public Node from(){
        return from;
    }

    /**
     * Get node @to
     * @return a Node
     */
    public Node to(){
        return to;
    }

    /**
     * Get symmetric, reverse @from and @to 
     * @return a new Edge
     */
    public Edge getSymmetric(){
        if (!isWeighted()) return new Edge(this.to, this.from, this.graphHolder);
        else return new Edge(this.to, this.from, this.weight, this.graphHolder);
    }

    /**
     * Check if self loop, @from equals @to
     * @return true if self loop, false else
     */
    public boolean isSelfLoop(){
        return this.from.equals(this.to);
    }

    /**
     * Check if weighht is not null
     * @return true if weighted, false else
     */
    public boolean isWeighted(){
        return weight != null;
    }

    /**
     * Get the weight of the Edge
     * @return the Integer @weight
     */
    public Integer getWeight() {
        return weight;
    }

    /* Overrides */

    /**
     * Check if current Edge is equal to another object
     * @param obj another object
     * @return true if the object is an edge, has the same Node @from and @to and the same weight if weighted
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        
        Edge other = (Edge) obj;

        return from == other.from && to == other.to  && 
                ((!isWeighted() && !other.isWeighted()) || Objects.equals(weight, other.weight));
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash * (this.from != null ? this.from.hashCode() : 0);
        hash = 31 * hash * (this.to != null ? this.to.hashCode() : 0);
        hash = 31 * hash * (isWeighted() ? this.weight : 0);
        return hash;
    }

    /**
     * 
     * @param o
     * @return
     */
	@Override
	public int compareTo(Edge o) {
        if (from.getId() != o.from.getId()) return from.getId() - o.from.getId();
        if (to.getId() != o.to.getId()) return to.getId() - o.to.getId(); 
        if (isWeighted() && o.isWeighted() && !Objects.equals(weight, o.weight)) return weight - o.weight;
        return 0;
	}
}
