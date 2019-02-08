package at.ssw.graphanalyzer.positioning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Node<N, E> {

    private N data;
    private List<Edge<N, E>> inEdges;
    private List<Edge<N, E>> outEdges;
    private boolean visited;
    private boolean active;
    private boolean reachable;
    private Graph<N, E> graph;

    protected boolean isVisited() {
        return visited;
    }

    protected void setVisited(boolean b) {
        visited = b;
    }

    protected boolean isReachable() {
        return reachable;
    }

    protected void setReachable(boolean b) {
        reachable = b;
    }

    protected boolean isActive() {
        return active;
    }

    protected void setActive(boolean b) {
        active = b;
    }

    public int getInDegree() {
        return getInDegree(true);
    }

    public int getInDegree(boolean countSelfLoops) {
        if(countSelfLoops) {
            return inEdges.size();
        } else {
            int cnt = 0;
            for(Edge<N, E> e : inEdges) {
                if(e.getSource() != this) {
                    cnt++;
                }
            }
            return cnt;
        }
    }

    public int getOutDegree() {
        return outEdges.size();
    }

    protected Node(Graph<N, E> graph, N data) {
        setData(data);
        this.graph = graph;
        inEdges = new ArrayList<Edge<N, E>>();
        outEdges = new ArrayList<Edge<N, E>>();
    }

    protected void addInEdge(Edge<N, E> e) {
        assert !inEdges.contains(e);
        inEdges.add(e);
    }

    protected void addOutEdge(Edge<N, E> e) {
        assert !outEdges.contains(e);
        outEdges.add(e);
    }

    protected void removeInEdge(Edge<N, E> e) {
        assert inEdges.contains(e);
        inEdges.remove(e);
    }

    protected void removeOutEdge(Edge<N, E> e) {
        assert outEdges.contains(e);
        outEdges.remove(e);
    }

    public List<Edge<N, E>> getInEdges() {
        return Collections.unmodifiableList(inEdges);
    }

    public List<Edge<N, E>> getOutEdges() {
        return Collections.unmodifiableList(outEdges);
    }

    public List<Node<N, E>> getSuccessors() {
        ArrayList<Node<N, E>> succ = new ArrayList<Node<N, E>>();
        for(Edge<N, E> e : getOutEdges()) {
            Node<N, E> n = e.getDest();
            if(!succ.contains(n)) {
                succ.add(n);
            }
        }
        return succ;
    }

    public List<Node<N, E>> getPredecessors() {
        ArrayList<Node<N, E>> pred = new ArrayList<Node<N, E>>();
        for(Edge<N, E> e : getInEdges()) {
            Node<N, E> n = e.getSource();
            if(!pred.contains(n)) {
                pred.add(n);
            }
        }
        return pred;
    }

    public N getData() {
        return data;
    }

    public void setData(N d) {
        data = d;
    }

    public String toString() {
        return "Node: " + data;
    }

}
