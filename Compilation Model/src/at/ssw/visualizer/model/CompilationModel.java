package at.ssw.visualizer.model;

import java.util.List;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Christian Wimmer
 */
public interface CompilationModel {
    public List<Compilation> getCompilations();

    public String parseInputFile(String fileName);
    
    public void removeCompilation(Compilation compilation);
    
    public void clear();

    public void addChangedListener(ChangeListener listener);
    
    public void removeChangedListener(ChangeListener listener);
}
