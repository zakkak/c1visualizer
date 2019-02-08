package at.ssw.graphanalyzer.positioning;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class BasicLineGenerator implements LineGenerator {
    public List<Point> createLine(List<Point> line, Point startRefPoint, Point endRefPoint) {
        return line;
    }

    public String iconResource() {
        return "at/ssw/graphanalyzer/coordinator/images/polygon_lines.gif";
    }

    public String getName() {
        return "Polygon lines";
    }
}
