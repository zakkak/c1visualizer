package at.ssw.visualizer.modelimpl;

import at.ssw.visualizer.model.Compilation;
import at.ssw.visualizer.model.CompilationElement;
import at.ssw.visualizer.model.CompilationModel;
import java.util.Date;

/**
 *
 * @author Christian Wimmer
 */
public class CompilationImpl extends CompilationElementImpl implements Compilation {
    private CompilationModel compilationModel;
    private String method;
    private Date date;

    public CompilationImpl(String shortName, String name, String method, Date date) {
        super(shortName, name);
        this.method = method;
        this.date = date;
    }

    public CompilationModel getCompilationModel() {
        return compilationModel;
    }

    public void setCompilationModel(CompilationModelImpl compilationModel) {
        this.compilationModel = compilationModel;
    }

    public String getMethod() {
        return method;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("  Name: ").append(getName());
        result.append("\n  Method: ").append(method);
        result.append("\n  Date: ").append(date);
        result.append("\n  Elements: ").append(getElements().size());
        result.append("\n");
        for (CompilationElement element : getElements()) {
            result.append(element);
        }
        return result.toString();
    }
}
