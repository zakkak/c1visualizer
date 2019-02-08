package at.ssw.visualizer.nc.action;

import at.ssw.visualizer.core.focus.Focus;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.nc.NCEditor;
import at.ssw.visualizer.nc.NCEditorSupport;
import at.ssw.visualizer.nc.icons.Icons;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;

/**
 *
 * @author Alexander Reder
 */
public final class ShowNCEditorAction extends CookieAction {

    protected void performAction(Node[] activatedNodes) {
        ControlFlowGraph cfg = activatedNodes[0].getLookup().lookup(ControlFlowGraph.class);
        if (!Focus.findEditor(NCEditor.class, cfg)) {
            NCEditorSupport support = new NCEditorSupport(cfg);
            support.open();
        }
    }

    @Override
    protected boolean enable(Node[] activatedNodes) {
        if(activatedNodes == null || activatedNodes.length == 0) {
            return false;
        }
        ControlFlowGraph cfg = activatedNodes[0].getLookup().lookup(ControlFlowGraph.class);
        if(cfg == null) {
            return false;
        }
        return cfg.getNativeMethod() != null;
    }
    
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    public String getName() {
        return "Open Native code";
    }

    protected Class[] cookieClasses() {
        return new Class[]{ControlFlowGraph.class};
    }

    @Override
    protected String iconResource() {
        return Icons.NATIVECODE;
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    protected @Override
    boolean asynchronous() {
        return false;
    }
}

