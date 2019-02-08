package at.ssw.visualizer.nc.model;

import org.netbeans.editor.BaseTokenID;
import org.netbeans.editor.TokenContext;
import org.netbeans.editor.TokenContextPath;
import org.netbeans.editor.TokenID;

/**
 *
 * @author Alexander Reder
 */
public class NCTokenContext extends TokenContext {
    
    public static final int EOF_TOKEN_ID = -1;
    public static final int WHITESPACE_TOKEN_ID = -2;
    public static final int OTHER_TOKEN_ID = -3;
    public static final int ADDRESS_TOKEN_ID = 4;
    public static final int INSTRUCTION_TOKEN_ID = 5;
    public static final int REGISTER_TOKEN_ID = 6;
    public static final int BLOCK_TOKEN_ID = 7;
    public static final int COMMENT_TOKEN_ID = 8;
    
    public static final TokenID EOF_TOKEN = new BaseTokenID("eof", EOF_TOKEN_ID);
    public static final TokenID WHITESPACE_TOKEN = new BaseTokenID("whitespace", WHITESPACE_TOKEN_ID);
    public static final TokenID OTHER_TOKEN = new BaseTokenID("other", OTHER_TOKEN_ID);
    public static final TokenID ADDRESS_TOKEN = new BaseTokenID("address", ADDRESS_TOKEN_ID);
    public static final TokenID INSTRUCTION_TOKEN = new BaseTokenID("instruction", INSTRUCTION_TOKEN_ID);
    public static final TokenID REGISTER_TOKEN = new BaseTokenID("register", REGISTER_TOKEN_ID);
    public static final TokenID BLOCK_TOKEN = new BaseTokenID("block", BLOCK_TOKEN_ID);
    public static final TokenID COMMENT_TOKEN = new BaseTokenID("comment", COMMENT_TOKEN_ID);
    
    public static final NCTokenContext context = new NCTokenContext();
    public static final TokenContextPath contextPath = context.getContextPath();
    
    private NCTokenContext() {
        super("nc-");
        addTokenID(WHITESPACE_TOKEN);
        addTokenID(OTHER_TOKEN);
        addTokenID(ADDRESS_TOKEN);
        addTokenID(INSTRUCTION_TOKEN);
        addTokenID(REGISTER_TOKEN);
        addTokenID(BLOCK_TOKEN);
        addTokenID(COMMENT_TOKEN);
    }

}
