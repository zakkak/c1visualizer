package at.ssw.visualizer.model;

import java.util.Date;

/**
 *
 * @author Christian Wimmer
 */
public interface Compilation extends CompilationElement {
    public CompilationModel getCompilationModel();

    public String getMethod();

    public Date getDate();
}
