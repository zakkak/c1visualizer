package at.ssw.visualizer.nc.model;

import at.ssw.visualizer.texteditor.model.Scanner;
import java.util.HashSet;
import java.util.Set;
import org.netbeans.editor.TokenID;

/**
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
public class NCScanner extends Scanner {
    private Set<String> registerNames;

    public NCScanner() {
        super("\n\r\t ,;:()$[]", NCTokenContext.contextPath);

        registerNames = new HashSet<String>();
        registerNames.add("eax");
        registerNames.add("ebx");
        registerNames.add("ecx");
        registerNames.add("edx");
        registerNames.add("esi");
        registerNames.add("edi");
        registerNames.add("esp");
        registerNames.add("ebp");
        registerNames.add("rax");
        registerNames.add("rbx");
        registerNames.add("rcx");
        registerNames.add("rdx");
        registerNames.add("rsi");
        registerNames.add("rdi");
        registerNames.add("rsp");
        registerNames.add("rbp");
        registerNames.add("r8");
        registerNames.add("r9");
        registerNames.add("r10");
        registerNames.add("r11");
        registerNames.add("r12");
        registerNames.add("r13");
        registerNames.add("r14");
        registerNames.add("r15");
        registerNames.add("r8d");
        registerNames.add("r9d");
        registerNames.add("r10d");
        registerNames.add("r11d");
        registerNames.add("r12d");
        registerNames.add("r13d");
        registerNames.add("r14d");
        registerNames.add("r15d");
        registerNames.add("xmm0");
        registerNames.add("xmm1");
        registerNames.add("xmm2");
        registerNames.add("xmm3");
        registerNames.add("xmm4");
        registerNames.add("xmm5");
        registerNames.add("xmm6");
        registerNames.add("xmm7");
        registerNames.add("xmm8");
        registerNames.add("xmm9");
        registerNames.add("xmm10");
        registerNames.add("xmm11");
        registerNames.add("xmm12");
        registerNames.add("xmm13");
        registerNames.add("xmm14");
        registerNames.add("xmm15");
    }

    private boolean isBlock() {
        return (expectChar('B') && expectChar(DIGIT) && expectChars(DIGIT) && expectEnd()) ||
               (expectChar('L') && expectChar(DIGIT) && expectChars(DIGIT) && expectEnd());
    }

    private boolean isAddress() {
        return expectChar('0') && expectChar('x') && expectChar(HEX) && expectChars(HEX) && expectEnd();
    }

    private boolean isRegister() {
        return (expectChar('%') && expectChar(LETTER) && expectChars(LETTER_DIGIT) && expectEnd()) ||
                isKeyword(registerNames);
    }

    private boolean isInstruction() {
        boolean result = (beforeChar(':') || beforeChars(DIGIT)) && expectChar(LC_LETTER) && expectChars(LC_LETTER_DIGIT);
        while (result && ch == ' ' && offset + 1 < stopOffset && LC_LETTER.get(buffer[offset + 1])) {
            readNext();
            result = expectChars(LC_LETTER_DIGIT);
        }
        return result && expectEnd();
    }

    private boolean isComment() {
        int curOffset = offset;
        boolean startFound = false;
        while (curOffset >= 0 && buffer[curOffset] != '\n') {
            if (buffer[curOffset] == ';') {
                startFound = true;
                tokenOffset = curOffset;
            }
            curOffset--;
        }
        if (!startFound) {
            return false;
        }
        while (ch != '\n' && ch != EOF) {
            readNext();
        }
        return true;
    }

    @Override
    protected TokenID parseToken() {
        findTokenBegin();
        if (ch == EOF) {
            return NCTokenContext.EOF_TOKEN;
        } else if (isComment()) {
            return NCTokenContext.COMMENT_TOKEN;
        } else if (isWhitespace()) {
            return NCTokenContext.WHITESPACE_TOKEN;
        } else if (isBlock()) {
            return NCTokenContext.BLOCK_TOKEN;
        } else if (isAddress()) {
            return NCTokenContext.ADDRESS_TOKEN;
        } else if (isRegister()) {
            return NCTokenContext.REGISTER_TOKEN;
        } else if (isInstruction()) {
            return NCTokenContext.INSTRUCTION_TOKEN;
        } else {
            readToWhitespace();
            return NCTokenContext.OTHER_TOKEN;
        }
    }

}
