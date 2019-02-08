package at.ssw.visualizer.interval;

import at.ssw.visualizer.core.focus.Focus;
import at.ssw.visualizer.model.interval.IntervalList;
import at.ssw.visualizer.interval.icons.Icons;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;

/**
 * Opens a new interval visualization window.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
public final class ShowIntervalEditorAction extends CookieAction {
    protected void performAction(Node[] activatedNodes) {
        IntervalList intervalList = activatedNodes[0].getLookup().lookup(IntervalList.class);
        if (!Focus.findEditor(IntervalEditorTopComponent.class, intervalList)) {
            IntervalEditorSupport editor = new IntervalEditorSupport(intervalList);
            editor.open();
        }
    }

    public String getName() {
        return "Open Intervals";
    }

    @Override
    protected String iconResource() {
        return Icons.INTERVALS;
    }

    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    protected Class[] cookieClasses() {
        return new Class[]{IntervalList.class};
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
