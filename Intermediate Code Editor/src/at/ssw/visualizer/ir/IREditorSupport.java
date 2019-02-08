package at.ssw.visualizer.ir;

import at.ssw.visualizer.ir.icons.Icons;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.ir.model.IRTextBuilder;
import at.ssw.visualizer.texteditor.EditorSupport;
import org.openide.text.CloneableEditor;
import org.openide.util.ImageUtilities;

/**
 * Connects a ControlFlowGraph to a NetBeans text editor.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 * @author Alexander Reder
 */
public class IREditorSupport extends EditorSupport {
    
    public static final String MIME_TYPE = "text/x-compilation-ir";

    public IREditorSupport(ControlFlowGraph cfg) {
        super(cfg);
        this.text = new IRTextBuilder().buildDocument(cfg);
    }

    @Override
    public String getMimeType() {
        return MIME_TYPE;
    }
    
    @Override
    protected CloneableEditor createCloneableEditor() {
        return new IREditor(this);
    }

    
    @Override
    protected void initializeCloneableEditor(CloneableEditor editor) {
        super.initializeCloneableEditor(editor);
        editor.setIcon(ImageUtilities.loadImage(Icons.IR));
    }

}
