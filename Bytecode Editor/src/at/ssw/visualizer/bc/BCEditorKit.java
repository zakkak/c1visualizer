package at.ssw.visualizer.bc;

import at.ssw.visualizer.bc.model.BCScanner;
import at.ssw.visualizer.texteditor.EditorKit;
import javax.swing.text.Document;
import org.netbeans.editor.Syntax;

/**
 * A EditorKit for the bytecode, mime type text/x-compilation-bc, providing
 * the syntax for the bytecode.
 *
 * @author Alexander Reder
 */
public class BCEditorKit extends EditorKit {

    @Override
    public Syntax createSyntax(Document document) {
        return new BCScanner();
    }

    /**
     * Returns the mime type which identifies the bytecode for this EditorKit.
     *
     * @return      the mime type from the EditorSupport (text/x-compilation-bc)
     */
    @Override
    public String getContentType() {
        return BCEditorSupport.MIME_TYPE;
    }
}
