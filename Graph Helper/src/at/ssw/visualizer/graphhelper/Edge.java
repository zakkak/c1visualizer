package at.ssw.visualizer.graphhelper;

/**
 * Implements the edge of an directed graph.
 *
 * @author Stefan Loidl
 */
public class Edge {

    public Node source, destination;

    /** data field for the use with user algorithms*/
    public Object data=null;

    public boolean visited=false;

    /**
     * Creates a new edge form source to destination Node
     */
    public Edge(Node source, Node destination) {
        this.source=source;
        this.destination=destination;
    }

    /**
     * Returns a new Edge from destination to source.
     */
    public Edge getReverseEdge(){
        return new Edge(destination,source);
    }

    /**
     * Reverses this Edge.
     */
    public void reverseEdge(){
        Node n=source;
        source=destination;
        destination=n;
    }
}
