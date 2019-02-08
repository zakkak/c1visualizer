package at.ssw.visualizer.nc;

import at.ssw.visualizer.nc.model.NCScanner;
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
 *
 * @author Alexander Reder
 */
public class NCEditorKit extends EditorKit {

    @Override
    public Syntax createSyntax(Document document) {
        return new NCScanner();
    }

    @Override
    public String getContentType() {
        return NCEditorSupport.MIME_TYPE;
    }

    /**
     * Add a costum CodeFoldingPopupAction to enable code folding of
     * the LIR Comments in the native code.
     */
    @Override
    protected Action[] getCustomActions() {
        return TextAction.augmentList(super.getCustomActions(), new Action[]{
                    new GenerateFoldPopupAction()
                });
    }

    /**
     * Extend the existing <code>GenerateFoldPopupAction</code> with the
     * expand and collapse lir comment actions.
     */
    public static class GenerateFoldPopupAction extends NbEditorKit.GenerateFoldPopupAction {

        @Override
        public JMenuItem getPopupMenuItem(JTextComponent target) {
            JMenuItem menu = super.getPopupMenuItem(target);
            menu.add(new JPopupMenu.Separator());
            Lookup lookup = Lookups.forPath("Actions/View/NCFolding");
            Collection<? extends CallableSystemAction> foldingActions = lookup.lookupAll(CallableSystemAction.class);
            for (CallableSystemAction csa : foldingActions) {
                menu.add(csa.getMenuPresenter());
            }
            return menu;
        }
    }
}
