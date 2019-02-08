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
public class InterClusterConnection implements Link{

    private Port inputSlot;
    private Port outputSlot;
    private List<Point> intermediatePoints;
    private ClusterInputSlotNode inputSlotNode;
    private ClusterOutputSlotNode outputSlotNode;

    public InterClusterConnection(ClusterOutputSlotNode outputSlotNode, ClusterInputSlotNode inputSlotNode) {
        this.outputSlotNode = outputSlotNode;
        this.inputSlotNode = inputSlotNode;
        this.inputSlot = inputSlotNode.getInputSlot();
        this.outputSlot = outputSlotNode.getOutputSlot();
        intermediatePoints = new ArrayList<Point>();
    }

    public ClusterOutputSlotNode getOutputSlotNode() {
        return outputSlotNode;
    }

    public Port getTo() {
        return inputSlot;
    }

    public Port getFrom() {
        return outputSlot;
    }

    public void setControlPoints(List<Point> p) {
        this.intermediatePoints = p;
    }

    public List<Point> getControlPoints() {
        return intermediatePoints;
    }

    public String toString() {
        return "InterClusterConnection[from=" + getFrom() + ", to=" + getTo() + "]";
    }
}
