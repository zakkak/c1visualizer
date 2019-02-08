package at.ssw.graphanalyzer.positioning;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class BezierLineGenerator implements LineGenerator{
    public List<Point> createLine(List<Point> lines, Point startRefPoint, Point endRefPoint) {
        return Curves.convertToBezier(lines, startRefPoint, endRefPoint, 0.2, 100);
    }

    public String iconResource() {
        return "at/ssw/graphanalyzer/coordinator/images/bezier_lines.gif";
    }

    public String getName() {
        return "Bezier lines";
    }
}
