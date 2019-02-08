package at.ssw.visualizer.dataflow.action;

import at.ssw.visualizer.core.focus.Focus;
import at.ssw.visualizer.dataflow.editor.DFEditorSupport;
import at.ssw.visualizer.dataflow.editor.DataFlowEditorTopComponent;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;

/**
 * This is the action that is hooked to an compilation-x-element within the
 * filesystem menu. Essentially it extracts the ControlFlowGraph from the
 * selected node and passes it to the DFEditorSupport bringing the
 * viewer to the front.
 *
 * @author Stefan Loidl
 * @author Christian Wimmer
 */
public final class ShowDataFlowEditorAction extends CookieAction {
    private static final String ICON_PATH = "at/ssw/visualizer/dataflow/icons/dfg.gif";

    /*
     * The Action extracts the Control- Flow- Graph- Data- Object from the nodes which
     * contain information for data flow too.
     */
    protected void performAction(Node[] activatedNodes) {
        ControlFlowGraph cfg = activatedNodes[0].getLookup().lookup(ControlFlowGraph.class);
        if (!Focus.findEditor(DataFlowEditorTopComponent.class, cfg)) {
            DFEditorSupport editor = new DFEditorSupport(cfg);
            editor.open();
        }
    }

    /*
     * Defines when the context menu item triggering this action should be
     * enabled (one element has to be chose and this elements has to have an
     * ControlFlowGraph Cookie!- moreover HIR Codes has to be present within
     * the node)
     */
    @Override
    protected boolean enable(Node[] activatedNodes) {
        if (!super.enable(activatedNodes)) {
            return false;
        }
        ControlFlowGraph cfg = activatedNodes[0].getLookup().lookup(ControlFlowGraph.class);
        return cfg.hasHir();
    }

    public String getName() {
        return "Open Data Flow Graph";
    }

    @Override
    protected String iconResource() {
        return ICON_PATH;
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
