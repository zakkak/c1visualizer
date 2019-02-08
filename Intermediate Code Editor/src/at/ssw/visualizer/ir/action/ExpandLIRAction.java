package at.ssw.visualizer.ir.action;

import at.ssw.visualizer.ir.icons.Icons;
import at.ssw.visualizer.ir.model.IRTextBuilder;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.fold.FoldHierarchy;
import org.netbeans.api.editor.fold.FoldUtilities;
import org.netbeans.editor.Utilities;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CallableSystemAction;

public final class ExpandLIRAction extends CallableSystemAction {
    public void performAction() {
        JTextComponent comp = Utilities.getFocusedComponent();
        FoldHierarchy hierarchy = FoldHierarchy.get(comp);
        FoldUtilities.collapseAll(hierarchy);
        FoldUtilities.expand(hierarchy, IRTextBuilder.KIND_LIR);
        FoldUtilities.expand(hierarchy, IRTextBuilder.KIND_BLOCK);
    }

    public String getName() {
        return "Expand LIR";
    }

    @Override
    protected String iconResource() {
        return Icons.EXPAND_LIR;
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

}
