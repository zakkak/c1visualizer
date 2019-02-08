package at.ssw.visualizer.ir;

import at.ssw.visualizer.texteditor.Editor;
import org.openide.windows.CloneableTopComponent;

/**
 * The actual editor component for displaying IR text.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 * @author Alexander Reder
 */
public class IREditor extends Editor {
    
    public IREditor(IREditorSupport support) {
        super(support);
    }
    
    @Override
    protected CloneableTopComponent createClonedObject() {
        IREditor editor = new IREditor((IREditorSupport) cloneableEditorSupport());
        editor.setActivatedNodes(getActivatedNodes());
        return editor;
    }
}
