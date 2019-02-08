package at.ssw.visualizer.model;

import java.util.List;

/**
 *
 * @author Christian Wimmer
 */
public interface CompilationElement {
    public Compilation getCompilation();

    public CompilationElement getParent();

    public List<CompilationElement> getElements();

    public String getShortName();

    public String getName();
}
