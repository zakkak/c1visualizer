package at.ssw.graphanalyzer.positioning;

import at.ssw.positionmanager.Link;
import at.ssw.positionmanager.Port;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ClusterIngoingConnection implements Link{

    private List<Point> controlPoints;
    private ClusterInputSlotNode inputSlotNode;
    private Link connection;
    private Port inputSlot;
    private Port outputSlot;

    public ClusterIngoingConnection(ClusterInputSlotNode inputSlotNode, Link c) {
        this.inputSlotNode = inputSlotNode;
        this.connection = c;
        this.controlPoints = new ArrayList<Point>();

        inputSlot = c.getTo();
        outputSlot = inputSlotNode.getOutputSlot();
    }

    public Link getConnection() {
        return connection;
    }

    public ClusterInputSlotNode getInputSlotNode() {
        return inputSlotNode;
    }

    public Port getTo() {
        return inputSlot;
    }

    public Port getFrom() {
        return outputSlot;
    }

    public void setControlPoints(List<Point> p) {
        this.controlPoints = p;
    }

    public List<Point> getControlPoints() {
        return controlPoints;
    }

}