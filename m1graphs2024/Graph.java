import java.util.*;

public class Graph {

    private Map<Node, List<Edge>> adjEdList;

    public Graph() {
        adjEdList = new HashMap<>();
    }

    /**
     * @return the number of nodes in the graph
     */
    public int nbNodes(){
        return adjEdList.size();
    }

    /**
     * 
     * @param i an integer
     * @return the node with the id corresponding parameter @i
     */
    Node getNode(int i) {
        /*if (adjEdList.keySet().contains(i)){
            
        }*/
        throw new UnsupportedOperationException("Not supported yet.");
    }
}