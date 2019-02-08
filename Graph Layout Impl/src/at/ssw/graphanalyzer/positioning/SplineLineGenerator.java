package at.ssw.graphanalyzer.positioning;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public class SplineLineGenerator implements LineGenerator{

    public SplineLineGenerator() {
    }

    public List<Point> createLine(List<Point> line, Point startRefPoint, Point endRefPoint) {
        return Curves.bsplines(line, startRefPoint, endRefPoint, 30);
    }

    public String iconResource() {
        return "at/ssw/graphanalyzer/coordinator/images/spline_lines.gif";
    }

    public String getName() {
        return "Splines";
    }
}
