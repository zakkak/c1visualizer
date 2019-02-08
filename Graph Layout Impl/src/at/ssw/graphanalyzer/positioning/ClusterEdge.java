package at.ssw.graphanalyzer.positioning;

import at.ssw.positionmanager.Link;
import at.ssw.positionmanager.Port;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class ClusterEdge implements Link{

    private ClusterNode from;
    private ClusterNode to;
    private List<Point> points;

    public ClusterEdge(ClusterNode from, ClusterNode to) {
        assert from != null;
        assert to != null;
        this.from = from;
        this.to = to;
    }

    public Port getTo() {
        return to.getInputSlot();
    }

    public Port getFrom() {
        return from.getInputSlot();
    }

    public void setControlPoints(List<Point> p) {
        this.points = p;
    }

    public List<Point> getControlPoints() {
        return points;
    }

}
