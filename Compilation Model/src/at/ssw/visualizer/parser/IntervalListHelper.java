package at.ssw.visualizer.parser;

import java.util.Collection;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.model.interval.ChildInterval;
import at.ssw.visualizer.model.interval.Range;
import at.ssw.visualizer.model.interval.UsePosition;
import at.ssw.visualizer.modelimpl.interval.ChildIntervalImpl;
import at.ssw.visualizer.modelimpl.interval.IntervalImpl;
import at.ssw.visualizer.modelimpl.interval.IntervalListImpl;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Christian Wimmer
 */
public class IntervalListHelper {
    protected String shortName;
    protected String name;
    protected LinkedHashMap<String, IntervalHelper> helpers = new LinkedHashMap<String, IntervalHelper>();

    public void add(IntervalHelper helper) {
        helpers.put(helper.regNum, helper);
    }


    public IntervalListImpl resolve(Parser parser, ControlFlowGraph controlFlowGraph) {
        for (IntervalHelper helper : helpers.values()) {
            ChildInterval registerHint = null;
            if (helpers.containsKey(helper.registerHint)) {
                registerHint = helpers.get(helper.registerHint).childInterval;
            }
            Range[] ranges = helper.ranges.toArray(new Range[helper.ranges.size()]);
            UsePosition[] usePositions = helper.usePositions.toArray(new UsePosition[helper.usePositions.size()]);

            helper.childInterval.setValues(helper.regNum, helper.type, helper.operand, helper.spillState, registerHint, ranges, usePositions);


            IntervalHelper parent = helpers.get(helper.splitParent);
            if (parent != null) {
                parent.splitChildren.add(helper.childInterval);
            } else {
                parser.SemErr("Unknown split parent: " + helper.splitParent);
            }
        }

        Collection<IntervalImpl> intervals = new ArrayList<IntervalImpl>();
        for (IntervalHelper helper : helpers.values()) {
            if (helper.splitChildren.size() > 0) {
                ChildIntervalImpl[] children = helper.splitChildren.toArray(new ChildIntervalImpl[helper.splitChildren.size()]);
                intervals.add(new IntervalImpl(children));
            }
        }

        return new IntervalListImpl(shortName, name, intervals.toArray(new IntervalImpl[intervals.size()]), controlFlowGraph);
    }
}
