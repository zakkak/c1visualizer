package at.ssw.visualizer.graphhelper;

import java.util.LinkedList;

/**
 * Implements a node of the directed graph.
 *
 * @author Stefan Loidl
 */
public class Node {

    /**unique identifier*/
    public String ID;

    /** flag used within algorithms*/
    public boolean visited;

    /** all edges adjacent to the node*/
    public LinkedList<Edge> edges;

    /**
     * List of successors and predecessors of the node
     * these lists are not meant to be changed by the user.
     * they are modified when inserting edges
     */
    public LinkedList<Node> succ,pred;

    /** data field for the use with algorithms*/
    public Object data=null;

    /** Creates a new instance of Node- ID should be unique within the graph*/
    public Node(String ID) {
        this.ID=ID;
        visited=false;
        succ=new LinkedList<Node>();
        pred=new LinkedList<Node>();
        edges=new LinkedList<Edge>();
    }


}
