package at.ssw.visualizer.bc.model;

import at.ssw.visualizer.texteditor.model.Scanner;
import org.netbeans.editor.TokenID;

/**
 * This class is used for parsing the bytecode text.
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
public class BCScanner extends Scanner {
    public BCScanner() {
        super("\n\r\t ,:", BCTokenContext.contextPath);
    }

    private boolean isBlock() {
        return expectChar('B') && expectChar(DIGIT) && expectChars(DIGIT) && expectEnd();
    }

    private boolean isBciDef() {
        return expectChar(DIGIT) && expectChars(DIGIT) && expectEnd(':');
    }

    private boolean isBciRef() {
        return expectChar('#') && expectChar(DIGIT) && expectChars(DIGIT) && expectEnd();
    }

    private boolean isVarRef() {
        return expectChar('%') && expectChar(DIGIT) && expectChars(DIGIT) && expectEnd();
    }

    private boolean isBcDescription() {
        return beforeChar(':') && expectChar(LETTER) && expectChars(LETTER_DIGIT) && expectEnd();
    }

    @Override
    protected TokenID parseToken() {
        findTokenBegin();
        if (ch == EOF) {
            return BCTokenContext.EOF_TOKEN;
        } else if (isWhitespace()) {
            return BCTokenContext.WHITESPACE_TOKEN;
        } else if (isBlock()) {
            return BCTokenContext.BLOCK_TOKEN;
        } else if (isBciDef() || isBciRef()) {
            return BCTokenContext.BCI_TOKEN;
        } else if (isVarRef()) {
            return BCTokenContext.VAR_REFERENCE_TOKEN;
        } else if (isBcDescription()) {
            return BCTokenContext.BC_DESCRIPTION_TOKEN;
        } else {
            readToWhitespace();
            return BCTokenContext.OTHER_TOKEN;
        }
    }

}
