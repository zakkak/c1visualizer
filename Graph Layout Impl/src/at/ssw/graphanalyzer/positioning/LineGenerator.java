package at.ssw.graphanalyzer.positioning;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface LineGenerator {

    public List<Point> createLine(List<Point> line, Point startRefPoint, Point endRefPoint);
    public String iconResource();
    public String getName();

}
