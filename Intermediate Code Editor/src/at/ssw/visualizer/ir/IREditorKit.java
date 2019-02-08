package at.ssw.visualizer.ir;

import at.ssw.visualizer.ir.model.IRScanner;
import at.ssw.visualizer.texteditor.EditorKit;
import java.util.Collection;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import org.netbeans.editor.Syntax;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.util.Lookup;
import org.openide.util.actions.CallableSystemAction;
import org.openide.util.lookup.Lookups;

/**
 * The IR Editor Kit, providing the syntax support.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
public class IREditorKit extends EditorKit {

    @Override
    public String getContentType() {
        return IREditorSupport.MIME_TYPE;
    }

    @Override
    public Syntax createSyntax(Document doc) {
        return new IRScanner();
    }

    @Override
    protected Action[] getCustomActions() {
        return TextAction.augmentList(super.getCustomActions(), new Action[]{
                    new GenerateFoldPopupAction()
                });
    }

    public static class GenerateFoldPopupAction extends NbEditorKit.GenerateFoldPopupAction {

        @Override
        public JMenuItem getPopupMenuItem(JTextComponent target) {
            JMenuItem menu = super.getPopupMenuItem(target);
            menu.add(new JPopupMenu.Separator());
            Lookup lookup = Lookups.forPath("IREditorFolding");
            Collection<? extends CallableSystemAction> foldingActions = lookup.lookupAll(CallableSystemAction.class);
            for (CallableSystemAction csa : foldingActions) {
                menu.add(csa.getMenuPresenter());
            }
            return menu;
        }
    }
}
