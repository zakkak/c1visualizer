package at.ssw.visualizer.ir.model;

import org.netbeans.editor.BaseTokenID;
import org.netbeans.editor.TokenContext;
import org.netbeans.editor.TokenContextPath;
import org.netbeans.editor.TokenID;

/**
 * The one and only IR token context containing all possible tokens.
 *
 * @author Bernhard Stiftner
 */
public class IRTokenContext extends TokenContext {

    public static final int BLOCK_TOKEN_ID = 1;
    public static final int HIR_TOKEN_ID = 2;
    public static final int LIR_TOKEN_ID = 3;
    public static final int OTHER_TOKEN_ID = -1;
    public static final int WHITESPACE_TOKEN_ID = -2;
    public static final int EOF_TOKEN_ID = -3;

    public static final TokenID BLOCK_TOKEN = new BaseTokenID("block", BLOCK_TOKEN_ID);
    public static final TokenID HIR_TOKEN = new BaseTokenID("hir", HIR_TOKEN_ID);
    public static final TokenID LIR_TOKEN = new BaseTokenID("lir", LIR_TOKEN_ID);
    public static final TokenID OTHER_TOKEN = new BaseTokenID("other", OTHER_TOKEN_ID);
    public static final TokenID WHITESPACE_TOKEN = new BaseTokenID("whitespace", WHITESPACE_TOKEN_ID);
    public static final TokenID EOF_TOKEN = new BaseTokenID("eof", EOF_TOKEN_ID);

    public static final IRTokenContext context = new IRTokenContext();
    public static final TokenContextPath contextPath = context.getContextPath();

    private IRTokenContext() {
        super("ir-");
        addTokenID(BLOCK_TOKEN);
        addTokenID(HIR_TOKEN);
        addTokenID(LIR_TOKEN);
        addTokenID(OTHER_TOKEN);
        addTokenID(WHITESPACE_TOKEN);
        addTokenID(EOF_TOKEN);
    }
}
