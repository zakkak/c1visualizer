package at.ssw.visualizer.ir.action;

import at.ssw.visualizer.core.focus.Focus;
import at.ssw.visualizer.ir.IREditor;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.ir.IREditorSupport;
import at.ssw.visualizer.ir.icons.Icons;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;

/**
 * Opens the IR editor.
 *
 * @author Bernhard Stiftner
 */
public final class ShowIREditorAction extends CookieAction {
    protected void performAction(Node[] activatedNodes) {
        ControlFlowGraph cfg = activatedNodes[0].getLookup().lookup(ControlFlowGraph.class);
        if (!Focus.findEditor(IREditor.class, cfg)) {
            IREditorSupport editor = new IREditorSupport(cfg);
            editor.open();
        }
    }

    @Override
    protected boolean enable(Node[] activatedNodes) {
        if (!super.enable(activatedNodes)) {
            return false;
        }
        ControlFlowGraph cfg = activatedNodes[0].getLookup().lookup(ControlFlowGraph.class);
        return cfg.hasHir() || cfg.hasState() || cfg.hasLir();
    }
    
    public String getName() {
        return "Open Intermediate Representation";
    }

    @Override
    protected String iconResource() {
        return Icons.IR;
    }

    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    protected Class[] cookieClasses() {
        return new Class[]{ControlFlowGraph.class};
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
