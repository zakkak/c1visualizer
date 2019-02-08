package at.ssw.positionmanager;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Link {
    public Port getFrom();
    public Port getTo();
    public List<Point> getControlPoints();
    public void setControlPoints(List<Point> list);
}
