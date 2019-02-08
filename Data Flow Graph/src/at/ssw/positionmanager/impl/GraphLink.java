package at.ssw.positionmanager.impl;

import at.ssw.positionmanager.Link;
import at.ssw.positionmanager.Port;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements a Link in the open Layouter specification of SSW.
 *
 * @author Stefan Loidl
 */
public class GraphLink implements Link, Comparable<Link>{

    private Port from, to;
    List<Point> controlPoints;
    protected int index;

    /** Creates a new instance of GraphLink */
    public GraphLink(int index ,Port from, Port to) {
        this.index=index;
        this.from=from;
        this.to=to;
        controlPoints=new LinkedList<Point>();
    }

    public Port getFrom() {
        return from;
    }

    public Port getTo() {
        return to;
    }

    public List<Point> getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(List<Point> list) {
        controlPoints=list;
    }


    public int compareTo(Link o) {
        if(o instanceof GraphLink){
            return index-((GraphLink)o).index;
        }
        return this.hashCode()-o.hashCode();
    }
}
