package at.ssw.visualizer.nc;

import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.nc.icons.Icons;
import at.ssw.visualizer.nc.model.NCTextBuilder;
import at.ssw.visualizer.texteditor.EditorSupport;
import org.openide.text.CloneableEditor;
import org.openide.util.ImageUtilities;


/**
 *
 * @author Alexander Reder
 */
public class NCEditorSupport extends EditorSupport {
    
    public static final String MIME_TYPE = "text/x-compilation-nc";
    
    public NCEditorSupport(ControlFlowGraph cfg) {
        super(cfg);   
        this.text = new NCTextBuilder().buildDocument(cfg);
    }

    public String getMimeType() {
        return MIME_TYPE;
    }
    
    @Override
    protected CloneableEditor createCloneableEditor() {
        return new NCEditor(this);
    }
    
    @Override
    protected void initializeCloneableEditor(CloneableEditor editor) {
        super.initializeCloneableEditor(editor);
        editor.setIcon(ImageUtilities.loadImage(Icons.NATIVECODE));
    }
    
}
