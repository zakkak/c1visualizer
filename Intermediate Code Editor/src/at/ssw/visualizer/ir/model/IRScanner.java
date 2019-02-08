package at.ssw.visualizer.ir.model;

import at.ssw.visualizer.texteditor.model.Scanner;
import org.netbeans.editor.TokenID;

/**
 * Splits the textual intermediate representation into tokens for HIR and LIR operands.
 *
 * @author Christian Wimmer
 */
public class IRScanner extends Scanner {
    public IRScanner() {
        super("\n\r\t .,;()[]", IRTokenContext.contextPath);
    }

    private boolean isBlock() {
        return expectChar('B') && expectChar(DIGIT) && expectChars(DIGIT) && expectEnd();
    }

    private boolean isHir() {
        return expectChar(LETTER) && expectChar(DIGIT) && expectChars(DIGIT) && expectEnd();
    }

    private boolean isLir() {
        return expectChar(LETTER) && skipUntil('|') && expectChars(LETTER)
                && isReferenceOrEmpty() && expectEnd();
    }

    private boolean isReferenceOrEmpty() {
        if (ch != '[')
            return true;
        readNext();
        return expectChars(REFERENCE_CHARS) && expectChar(']');
    }

    @Override
    protected TokenID parseToken() {
        findTokenBegin();
        if (ch == EOF && offset + 1 >= stopOffset) {
            // only except EOF if we are at the end of the buffer
            return IRTokenContext.EOF_TOKEN;
        } else if (isWhitespace()) {
            return IRTokenContext.WHITESPACE_TOKEN;
        } else if (isBlock()) {
            return IRTokenContext.BLOCK_TOKEN;
        } else if (isHir()) {
            return IRTokenContext.HIR_TOKEN;
        } else if (isLir()) {
            return IRTokenContext.LIR_TOKEN;
        } else {
            readToWhitespace();
            return IRTokenContext.OTHER_TOKEN;
        }
    }

}
