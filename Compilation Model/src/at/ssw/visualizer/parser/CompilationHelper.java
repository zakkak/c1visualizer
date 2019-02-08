package at.ssw.visualizer.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import at.ssw.visualizer.modelimpl.CompilationElementImpl;
import at.ssw.visualizer.modelimpl.CompilationImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Christian Wimmer
 */
public class CompilationHelper {
    protected String shortName;
    protected String name;
    protected String method;
    protected Date date;
    protected List<CompilationElementImpl> elements = new ArrayList<CompilationElementImpl>();

    protected Map<Integer, CFGHelper> idToCFG = new HashMap<Integer, CFGHelper>();
    
    protected CompilationImpl resolve() {
        CompilationImpl compilation = new CompilationImpl(shortName, name, method, date);
        
        compilation.setElements(elements.toArray(new CompilationElementImpl[elements.size()]), compilation);
        for (CFGHelper cfg : idToCFG.values()) {
            cfg.resolved.setElements(cfg.elements.toArray(new CompilationElementImpl[cfg.elements.size()]), compilation);
        }
        
        return compilation;
    }
}
