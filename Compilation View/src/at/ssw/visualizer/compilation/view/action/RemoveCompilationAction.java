package at.ssw.visualizer.compilation.view.action;

import at.ssw.visualizer.compilation.view.icons.Icons;
import at.ssw.visualizer.model.Compilation;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;

/**
 * Action for removing the currently selected compilation from the workspace.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
public final class RemoveCompilationAction extends CookieAction {
    protected void performAction(Node[] activatedNodes) {
        Compilation compilation = activatedNodes[0].getLookup().lookup(Compilation.class);
        compilation.getCompilationModel().removeCompilation(compilation);
    }

    public String getName() {
        return "Remove Method";
    }

    @Override
    protected String iconResource() {
        return Icons.REMOVE;
    }

    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    protected Class[] cookieClasses() {
        return new Class[]{Compilation.class};
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
