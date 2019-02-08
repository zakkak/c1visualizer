package at.ssw.visualizer.modelimpl;

import at.ssw.visualizer.model.Compilation;
import at.ssw.visualizer.model.CompilationElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Christian Wimmer
 */
public class CompilationElementImpl implements CompilationElement {
    private Compilation compilation;
    private CompilationElement parent;
    private CompilationElement[] elements;
    private String shortName;
    private String name;

    public CompilationElementImpl(String shortName, String name) {
        this.shortName = shortName;
        this.name = name;
        this.elements = new CompilationElement[0];
    }

    public Compilation getCompilation() {
        return compilation;
    }

    public CompilationElement getParent() {
        return parent;
    }
    
    public List<CompilationElement> getElements() {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }

    public void setElements(CompilationElementImpl[] elements, Compilation compilation) {
        this.elements = elements;
        for (CompilationElementImpl ce : elements) {
            ce.parent = this;
            ce.compilation = compilation;
        }
    }
    
    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }
}
