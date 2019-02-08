package at.ssw.graphanalyzer.positioning;

import at.ssw.positionmanager.Cluster;
import at.ssw.positionmanager.Port;
import at.ssw.positionmanager.Vertex;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ClusterInputSlotNode implements Vertex {

    private final int SIZE = 0;
    private Point position;
    private Port inputSlot;
    private Port outputSlot;
    private ClusterNode blockNode;
    private InterClusterConnection interBlockConnection;
    private Cluster cluster;
    private boolean dirty;
    private ClusterIngoingConnection conn;

    public void setIngoingConnection(ClusterIngoingConnection c) {
        conn = c;
    }

    public ClusterIngoingConnection getIngoingConnection() {
        return conn;
    }

    private String id;

    public String toString() {
        return id;
    }

    public ClusterInputSlotNode(ClusterNode n, String id) {
         this.blockNode = n;
         this.id = id;

         n.addSubNode(this);

        final Vertex thisNode = this;
        final ClusterNode thisBlockNode = blockNode;

        outputSlot = new Port() {

            public Point getRelativePosition() {
                return new Point(0, 0);
            }

            public Vertex getVertex() {
                return thisNode;
            }

            public String toString() {
                return "OutPort of " + thisNode.toString();
            }
        };

        inputSlot = new Port() {

            public Point getRelativePosition() {
                Point p = new Point(thisNode.getPosition());
                Point blockPos = thisBlockNode.getPosition();
                p.x -= blockPos.x;
                p.y -= blockPos.y;
                return p;
            }

            public Vertex getVertex() {
                return thisBlockNode;
            }
            public String toString() {
                return "InPort of " + thisNode.toString();
            }
        };
    }

    public Port getInputSlot() {
        return inputSlot;
    }

    public InterClusterConnection getInterBlockConnection() {
        return interBlockConnection;
    }

    public Port getOutputSlot() {
        return outputSlot;
    }


    public Dimension getSize() {
        return new Dimension(SIZE, SIZE);
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    public Point getPosition() {
        return position;
    }

    public void setInterBlockConnection(InterClusterConnection interBlockConnection) {
        this.interBlockConnection = interBlockConnection;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public boolean isDirty() {
        return dirty;
    }

    public boolean isRoot() {
        return true;
    }

    public int compareTo(Vertex o) {
        return toString().compareTo(o.toString());
    }

    public boolean isExpanded() {
        return false;
    }

    public boolean isFixed() {
        return false;
    }

    public boolean isMarked() {
        return false;
    }
}
