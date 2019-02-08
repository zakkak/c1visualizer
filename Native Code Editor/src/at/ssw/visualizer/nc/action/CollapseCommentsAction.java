package at.ssw.visualizer.nc.action;

import at.ssw.visualizer.nc.icons.Icons;
import at.ssw.visualizer.nc.model.NCTextBuilder;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.fold.FoldHierarchy;
import org.netbeans.api.editor.fold.FoldUtilities;
import org.netbeans.editor.Utilities;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CallableSystemAction;

public final class CollapseCommentsAction extends CallableSystemAction {

    public void performAction() {
        JTextComponent comp = Utilities.getFocusedComponent();
        FoldHierarchy hierarchy = FoldHierarchy.get(comp);
        FoldUtilities.collapse(hierarchy, NCTextBuilder.LIR_BLOCK);
    }
    
    public String getName() {
        return "Collapse LIR Comments";
    }

    @Override
    protected String iconResource() {
        return Icons.COLLAPSE_COMMENTS;
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
