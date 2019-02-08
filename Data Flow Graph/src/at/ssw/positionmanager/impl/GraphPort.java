package at.ssw.positionmanager.impl;

import at.ssw.positionmanager.LayoutGraph;
import at.ssw.positionmanager.Port;
import at.ssw.positionmanager.Vertex;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

/**
 * Implements a Port in the open layouter model of SSW.
 *
 * @author Stefan Loidl
 */
public class GraphPort implements Port{

    private Vertex vertex;
    private LayoutGraph graph=null;

    /** Creates a new instance of GraphPort */
    public GraphPort(Vertex v) {
        this.vertex=v;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setLayoutGraph(LayoutGraph graph){
        this.graph=graph;
    }

    public Point getRelativePosition() {
        Dimension d=vertex.getSize();
        int y=0, x=0;

        if(graph==null) return new Point(d.width/2,d.height/2);

        Set<Port> ports=graph.getInputPorts(vertex);
        if(!ports.contains(this)){
            ports=graph.getOutputPorts(vertex);
            y=d.height;
        }

        int offset= d.width/(ports.size()+1);
        boolean found=false;
        for(Port p:ports){
            x+=offset;
            if(p==this) {
                found=true;
                break;
            }
        }
        if(found) return new Point(x,y);
        else return new Point(d.width/2,d.height/2);
    }
}
