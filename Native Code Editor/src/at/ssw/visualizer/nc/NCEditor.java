package at.ssw.visualizer.nc;

import at.ssw.visualizer.texteditor.Editor;
import org.openide.windows.CloneableTopComponent;

/**
 *
 * @author Alexander Reder
 */
public class NCEditor extends Editor {

    public NCEditor(NCEditorSupport support) {
        super(support);
    }
    
    @Override
    protected CloneableTopComponent createClonedObject() {
        NCEditor editor = new NCEditor((NCEditorSupport) cloneableEditorSupport());
        editor.setActivatedNodes(getActivatedNodes());
        return editor;
    }    
    
}
