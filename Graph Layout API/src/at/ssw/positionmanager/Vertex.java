package at.ssw.positionmanager;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Vertex extends Comparable<Vertex>{

    public Cluster getCluster();
    public Dimension getSize();
    public Point getPosition();
    public void setPosition(Point p);
    public boolean isDirty();
    public boolean isRoot();

    public boolean isExpanded();
    public boolean isFixed();
    public boolean isMarked();
}
