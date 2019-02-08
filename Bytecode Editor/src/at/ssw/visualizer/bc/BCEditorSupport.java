package at.ssw.visualizer.bc;

import at.ssw.visualizer.bc.icons.Icons;
import at.ssw.visualizer.bc.model.BCTextBuilder;
import at.ssw.visualizer.model.bc.Bytecodes;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.texteditor.EditorSupport;
import javax.swing.text.EditorKit;
import javax.swing.text.StyledDocument;
import org.openide.text.CloneableEditor;
import org.openide.util.ImageUtilities;

/**
 * Associates an Editor to a StyledDocument.
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
public class BCEditorSupport extends EditorSupport {

    public static final String MIME_TYPE = "text/x-compilation-bc";

    private Bytecodes bytecodes;

    public BCEditorSupport(Bytecodes bytecodes) {
        super(bytecodes.getControlFlowGraph());
        this.bytecodes = bytecodes;
        this.text = new BCTextBuilder().buildDocument(cfg);
    }

    public String getMimeType() {
        return MIME_TYPE;
    }
    
    public Bytecodes getBytecodes() {
        return bytecodes;
    }
    
    /**
     * Returns the Editor associated to the EditorSupport.
     *
     * @return      a new BCEditor
     */
    @Override
    protected CloneableEditor createCloneableEditor() {
        return new BCEditor(this);
    }

    /**
     * Returns the document associated to the EditorSupport and the EditorKit.
     *
     * @param   kit     the EditorKit
     * @return          the new created styled document
     */
    @Override
    protected StyledDocument createStyledDocument(EditorKit kit) {
        StyledDocument doc = super.createStyledDocument(kit);
        doc.putProperty(Bytecodes.class, bytecodes);
        doc.putProperty(ControlFlowGraph.class, bytecodes.getControlFlowGraph());
        return doc;
    }

    /**
     * Returns the name of the method which will be displayed on top of the
     * editor window.
     *
     * @return      the method name
     */
    @Override
    public String messageName() {
        return bytecodes.getControlFlowGraph().getShortName();
    }

    @Override
    protected String messageToolTip() {
        return bytecodes.getControlFlowGraph().getName();
    }
    
    @Override
    protected void initializeCloneableEditor(CloneableEditor editor) {
        super.initializeCloneableEditor(editor);
        editor.setIcon(ImageUtilities.loadImage(Icons.BYTECODE));
    }

}
