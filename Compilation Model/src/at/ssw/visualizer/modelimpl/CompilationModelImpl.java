package at.ssw.visualizer.modelimpl;

import at.ssw.visualizer.model.Compilation;
import at.ssw.visualizer.model.CompilationModel;
import at.ssw.visualizer.parser.CompilationParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Christian Wimmer
 */
public class CompilationModelImpl implements CompilationModel {
    private List<Compilation> compilations = new ArrayList<Compilation>();
    private List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    private boolean parsing;

    public List<Compilation> getCompilations() {
        return Collections.unmodifiableList(compilations);
    }

    public String parseInputFile(String fileName) {
        parsing = true;
        String result;
        try {
            result = CompilationParser.parseInputFile(fileName, this);
        } finally {
            parsing = false;
            notifyListeners();
        }
        return result;
    }

    public void addCompilation(CompilationImpl compilation) {
        this.compilations.add(compilation);
        compilation.setCompilationModel(this);
        
        if (!parsing || this.compilations.size() % 40 == 0) {
            notifyListeners();
        }
    }

    public void removeCompilation(Compilation compilation) {
        compilations.remove(compilation);
        notifyListeners();
    }

    public void clear() {
        compilations.clear();
        notifyListeners();
    }


    public void addChangedListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void removeChangedListener(ChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Compilations: ");
        result.append(compilations.size());
        result.append("\n");
        for (Compilation compilation : compilations) {
            result.append(compilation);
        }
        return result.toString();
    }
}
