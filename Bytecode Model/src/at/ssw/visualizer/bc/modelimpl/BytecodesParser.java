package at.ssw.visualizer.bc.modelimpl;

import at.ssw.visualizer.model.bc.Bytecodes;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Type;

/**
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
public class BytecodesParser {
    
    private BytecodesParser() {
    }
    
    /**
     * Trys to read the bytecode of the given method name.
     *
     * @param   method  full name of the method
     */
    public static List<Bytecodes> readMethod(ControlFlowGraph cfg, String method, String[] classPaths) {
        List<Bytecodes> result = new ArrayList<Bytecodes>();
        
        MethodName methodName = MethodName.parse(method);
        if (methodName == null) {
            return result;
        }
        
        for (String classPath : classPaths) {
            try {
                ClassParser cp = locateClass(methodName, classPath);
                if (cp != null) {
                    JavaClass c = cp.parse();
                    for (Method m : c.getMethods()) {
                        if (methodName.matches(m)) {
                            result.add(readBytecodes(cfg, methodName, m));
                        }
                    }
                }
            } catch (IOException ex) {
                Logger log = Logger.getLogger(BytecodesParser.class.getName());
                log.log(Level.INFO, ex.getMessage(), ex);
            }
        }
        return result;
    }
    
    private static ClassParser locateClass(MethodName methodName, String classPath) throws IOException {
        File baseFile = new File(classPath);
        if (!baseFile.exists()) {
            return null;
        }
        
        if (baseFile.isDirectory()) {
            String className = methodName.className.replace('.', File.separatorChar) + ".class";
            File classFile = new File(baseFile, className);
            if (classFile.exists()) {
                return new ClassParser(new FileInputStream(classFile), className);
            }
            
        } else {
            String className = methodName.className.replace('.', '/') + ".class";
            ZipFile zipFile = new ZipFile(baseFile);
            ZipEntry zipEntry = zipFile.getEntry(className);
            if (zipEntry != null) {
                return new ClassParser(zipFile.getInputStream(zipEntry), className);
            }
        }
        return null;
    }
    
    /**
     * Parses the code string and generates the prolog, attributeslist and the
     * hashmap <BCI, Code> for the bytecode.
     */
    private static Bytecodes readBytecodes(ControlFlowGraph cfg, MethodName methodName, Method method) {
        Code code = method.getCode();
        Scanner bcScanner = new Scanner(code.toString());

        String prolog = "";
        String attributes = "";
        SortedMap<Integer, String> byteCodes = new TreeMap<Integer, String>();
        
        bcScanner.useDelimiter("\n");
        while (bcScanner.hasNext()) {
            String codeLine = bcScanner.next();
            if (codeLine.startsWith("Code")) {
                prolog = codeLine;
            } else if (codeLine.length() > 0 && codeLine.charAt(0) >= '0' && codeLine.charAt(0) <= '9' && codeLine.indexOf(':') > 0) {
                Scanner codeLineScanner = new Scanner(codeLine);
                codeLineScanner.useDelimiter(":\\s*");
//                try {
                    int key = Integer.parseInt(codeLineScanner.next());
                    byteCodes.put(key, codeLineScanner.next());
/*                } catch (NumberFormatException ex) {
                    System.err.println(codeLine);
                } catch (NoSuchElementException ex) {
                    System.err.println(codeLine);
                }
 */ 
                codeLineScanner.close();
            } else if (codeLine.length() > 0 && codeLine.charAt(0) != ' ') {
                attributes = attributes + codeLine + "\n";
            }
        }
        bcScanner.close();
        
        StringBuilder sb = new StringBuilder();
        sb.append(method.getReturnType()).append(" ");
        sb.append(methodName.className);
        sb.append(".").append(method.getName()).append("(");
        boolean sep = false;
        for (Type t : method.getArgumentTypes()) {
            if (sep) {
                sb.append(", ");
            }
            sb.append(t);
            sep = true;
        }
        sb.append(")");
        String name = sb.toString();

        sb = new StringBuilder();
        int point = methodName.className.lastIndexOf('.');
        if (point > 0) {
            sb.append(methodName.className.substring(point + 1));
        } else {
            sb.append(methodName.className);
        }
        sb.append(".").append(method.getName());
        String shortName = sb.toString();
        
        return new BytecodesImpl(cfg, name, shortName, prolog, attributes, byteCodes);
    }
}
