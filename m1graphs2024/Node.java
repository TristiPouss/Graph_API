import java.util.*;

public class Node implements Comparable<Node>{

    /* Attributes */

    /**
     * 
     * id is an identifier
     * Two nodes in the same graph can't have the same id
     * Two nodes in different graphs can have the same id 
     */
    private int id;

    /**
     * 
     * A node is not obligated to have a name, it can have it or not depend of the constructor 
     */
    private String name;

    /**
     * 
     * graphHolder is a reference to the graph where the node belongs
     */
    private Graph graphHolder;

    /* Constructors */

    /**
     * 
     * @param id            The id of the Node
     * @param graphHolder   The reference to the graph where the node will belongs
     * 
     * If the graph already contain a Node with this id the node isn't created. 
     * If the graph don't have the id, the programm create a node and add it to the graph
     */
    public Node(int id, Graph graphHolder){
        this.id = id;
        this.graphHolder = graphHolder;
    }
    
    /**
     * 
     * @param id
     * @param name
     * @param graphHolder
     */
    public Node(int id, String name, Graph graphHolder){
        this.id = id;
        this.name = name;
        this.graphHolder = graphHolder;
    }

    /**
     * 
     * @param graphHolder
     */
    public Node(Graph graphHolder){
        this.graphHolder = graphHolder;
        this.id = graphHolder.nbNodes();
    }
    
    /* API */

    public int getId() {
        return id;
    }

    /**
     * The goal is getting a list without duplicates of the successors (or neighbours in the undirected case) of node this. 
     * This should be overloaded with node id as usual. “Without duplicates” means that each successor appears uniquely in the list returned, even in the case of a multigraph (as opposed to the next method).
     * @return
     */
    public List<Node> getSuccessors(){
        return this.graphHolder.getSuccessors(this);
    }

    public List<Node> getSuccessorsMulti(){
        return this.graphHolder.getSuccessorsMulti(this);
    }

    public boolean adjacent(Node u){
        return this.graphHolder.adjacent(this, u);
    }

    public boolean adjacent(int id){
        return this.graphHolder.adjacent(this.id, id);
    }

    public int inDegree(){
        return this.graphHolder.inDegree(this);
    }
    
    public int outDegree(){
        return this.graphHolder.outDegree(this);
    }

    public int degree(){
        return this.graphHolder.degree(this);
    }

    public List<Edge> getOutEdges(){
        return this.graphHolder.getOutEdges(this);
    }   

    public List<Edge> getInEdges(){
        return this.graphHolder.getInEdges(this);
    }

    public List<Edge> getIncidentEdges(){
        return this.graphHolder.getIncidentEdges(this);
    }

    public List<Edge> getEdgesTo(Node u){
        return this.graphHolder.getEdges(this, u);
    }

    public Graph getGraph() {
        return graphHolder;
    }

    /* Overrides */

    /**
     * 
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Node other = (Node) obj;
        return this.id == other.id && this.graphHolder.equals(other.graphHolder);
    }

    /**
     * 
     */
    @Override
    public int hashCode(){
        int hash = 1;
        hash = this.id * hash;
        hash = hash * 13 + this.graphHolder.nbNodes();
        hash = hash * 17 + (this.name == null? 0 : this.name.hashCode());
        return hash;
    }

    /**
     * 
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return id - o.id;
    }
}