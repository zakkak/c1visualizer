package at.ssw.visualizer.bc;

import at.ssw.visualizer.texteditor.Editor;
import org.openide.windows.CloneableTopComponent;

/**
 * Editor associated to the BCEditorSupport.
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
public class BCEditor extends Editor {

    /**
     * Creates a new CloneableEditor for the ClonableEditorSupport.
     *
     * @param   support   ClonableEditor to attach
     */
    public BCEditor(BCEditorSupport support) {
        super(support);
    }

    @Override
    protected CloneableTopComponent createClonedObject() {
        BCEditor editor = new BCEditor((BCEditorSupport) cloneableEditorSupport());
        editor.setActivatedNodes(getActivatedNodes());
        return editor;
    }
}
