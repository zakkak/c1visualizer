package at.ssw.visualizer.interval.view;

import at.ssw.visualizer.model.interval.ChildInterval;
import at.ssw.visualizer.model.interval.Interval;
import at.ssw.visualizer.model.interval.IntervalList;
import at.ssw.visualizer.model.interval.Range;
import at.ssw.visualizer.model.interval.UsePosition;
import java.util.Collections;
import java.util.List;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 * TreeTableModel containing the interval items.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
public class IntervalTableModel extends AbstractTreeTableModel {
    public static final String[] COLUMN_NAMES = {"Number", "From", "To", "Type", "Register", "Spill State", "Hint", "Ranges", "Use Positions"};
    public static final Class[] COLUMN_TYPES = {TreeTableModel.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};
    public static final int[] COLUMN_WIDTHS = {60, 40, 40, 60, 60, 80, 40, 120, 120};

    private List<Interval> intervals;

    public IntervalTableModel() {
        super(new DefaultMutableTreeTableNode());
        intervals = Collections.emptyList();
    }

    public void setIntervals(IntervalList intervalList) {
        if (intervalList == null) {
            intervals = Collections.emptyList();
        } else {
            intervals = intervalList.getIntervals();
        }
        modelSupport.fireNewRoot();//fireTreeStructureChanged(root, new Object[]{root}, null, null);
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent == root) {
            return intervals.size();
        } else if (parent instanceof Interval) {
            Interval interval = (Interval) parent;
            return interval.getChildren().size();
        } else {
            throw new Error("invalid parent");
        }
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent == root) {
            return intervals.get(index);
        } else if (parent instanceof Interval) {
            Interval interval = (Interval) parent;
            return interval.getChildren().get(index);
        } else {
            throw new Error("invalid parent");
        }
    }

    public int getIndexOfChild(Object parent, Object child) {
        if (parent == root) {
            return intervals.indexOf(child);
        } else if (parent instanceof Interval) {
            Interval interval = (Interval) parent;
            return interval.getChildren().indexOf(child);
        } else {
            return -1;
        }
     }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof ChildInterval;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class getColumnClass(int column) {
        return COLUMN_TYPES[column];
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof Interval) {
            return getColumnText((Interval) node, column);
        } else if (node instanceof ChildInterval) {
            return getColumnText((ChildInterval) node, column);
        } else {
            return null;
        }
    }

    private String formatRegs(Interval interval) {
        StringBuilder sb = new StringBuilder();
        String last = null;
        for (ChildInterval child : interval.getChildren()) {
            String reg = child.getOperand();
            if (!reg.equals(last)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(reg);
                last = reg;
            }
        }
        return sb.toString();
    }

    private void appendRange(StringBuilder sb, Range range) {
        if (sb.length() > 0) {
            sb.append(", ");
        }
        sb.append("[");
        sb.append(range.getFrom());
        sb.append(", ");
        sb.append(range.getTo());
        sb.append("]");
    }

    private String formatRanges(Interval interval) {
        StringBuilder sb = new StringBuilder();
        for (ChildInterval child : interval.getChildren()) {
            for (Range range : child.getRanges()) {
                appendRange(sb, range);
            }
        }
        return sb.toString();
    }

    private String formatRanges(ChildInterval child) {
        StringBuilder sb = new StringBuilder();
        for (Range range : child.getRanges()) {
            appendRange(sb, range);
        }
        return sb.toString();
    }

    private void appendUsePosition(StringBuilder sb, UsePosition usePosition) {
        if (sb.length() > 0) {
            sb.append(", ");
        }
        sb.append(usePosition.getPosition());
        sb.append(" ");
        sb.append(usePosition.getKind());
    }

    private String formatUsePositions(Interval interval) {
        StringBuilder sb = new StringBuilder();
        for (ChildInterval child : interval.getChildren()) {
            for (UsePosition usePosition : child.getUsePositions()) {
                appendUsePosition(sb, usePosition);
            }
        }
        return sb.toString();
    }

    private String formatUsePositions(ChildInterval child) {
        StringBuilder sb = new StringBuilder();
        for (UsePosition usePosition : child.getUsePositions()) {
            appendUsePosition(sb, usePosition);
        }
        return sb.toString();
    }

    private String getColumnText(Interval interval, int index) {
        switch (index) {
            case 1:
                return Integer.toString(interval.getFrom());
            case 2:
                return Integer.toString(interval.getTo());
            case 4:
                return formatRegs(interval);
            case 7:
                return formatRanges(interval);
            case 8:
                return formatUsePositions(interval);
            default:
                return getColumnText(interval.getChildren().get(0), index);
        }
    }

    private String getColumnText(ChildInterval child, int index) {
        switch (index) {
            case 0:
                return child.getRegNum();
            case 1:
                return Integer.toString(child.getFrom());
            case 2:
                return Integer.toString(child.getTo());
            case 3:
                return child.getType();
            case 4:
                return child.getOperand();
            case 5:
                return child.getSpillState();
            case 6:
                return child.getRegisterHint() != null ? child.getRegisterHint().getRegNum() : "";
            case 7:
                return formatRanges(child);
            case 8:
                return formatUsePositions(child);
            default:
                throw new Error("invalid column");
        }
    }
}
