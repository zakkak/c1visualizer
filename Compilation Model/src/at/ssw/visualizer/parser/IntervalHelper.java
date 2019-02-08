package at.ssw.visualizer.parser;

import java.util.SortedSet;
import java.util.TreeSet;
import at.ssw.visualizer.model.interval.Range;
import at.ssw.visualizer.model.interval.UsePosition;
import at.ssw.visualizer.modelimpl.interval.ChildIntervalImpl;

/**
 *
 * @author Christian Wimmer
 */
public class IntervalHelper implements Comparable<IntervalHelper> {
    protected String regNum;
    protected String type;
    protected String operand = ""; // avoid null values if not defined
    protected String splitParent;
    protected String spillState;
    protected String registerHint;

    protected SortedSet<Range> ranges = new TreeSet<Range>();
    protected SortedSet<UsePosition> usePositions = new TreeSet<UsePosition>();

    protected ChildIntervalImpl childInterval = new ChildIntervalImpl();
    protected SortedSet<ChildIntervalImpl> splitChildren = new TreeSet<ChildIntervalImpl>();

    public int compareTo(IntervalHelper other) {
        return ranges.first().getFrom() - other.ranges.first().getFrom();
    }
}
