package at.ssw.visualizer.interval;

import at.ssw.visualizer.model.interval.IntervalList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.IOException;
import org.openide.cookies.OpenCookie;
import org.openide.windows.CloneableOpenSupport;
import org.openide.windows.CloneableTopComponent;

/**
 * Support class for opening interval editors.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
public class IntervalEditorSupport extends CloneableOpenSupport implements OpenCookie {
    private IntervalList intervalList;

    public IntervalEditorSupport(IntervalList intervalList) {
        super(new Env());
        ((Env) env).editorSupport = this;
        this.intervalList = intervalList;
    }

    protected CloneableTopComponent createCloneableTopComponent() {
        return new IntervalEditorTopComponent(intervalList);
    }

    public String messageOpened() {
        return "Opened " + intervalList.getCompilation().getMethod() + " - " + intervalList.getName();
    }

    public String messageOpening() {
        return "Opening " + intervalList.getCompilation().getMethod() + " - " + intervalList.getName();
    }


    public static class Env implements CloneableOpenSupport.Env {
        private PropertyChangeSupport prop = new PropertyChangeSupport(this);
        private VetoableChangeSupport veto = new VetoableChangeSupport(this);
        private IntervalEditorSupport editorSupport;

        public boolean isValid() {
            return true;
        }

        public boolean isModified() {
            return false;
        }

        public void markModified() throws IOException {
            throw new IOException("Editor is readonly");
        }

        public void unmarkModified() {
            // Nothing to do.
        }

        public CloneableOpenSupport findCloneableOpenSupport() {
            return editorSupport;
        }

        public void addPropertyChangeListener(PropertyChangeListener l) {
            prop.addPropertyChangeListener(l);
        }

        public void removePropertyChangeListener(PropertyChangeListener l) {
            prop.removePropertyChangeListener(l);
        }

        public void addVetoableChangeListener(VetoableChangeListener l) {
            veto.addVetoableChangeListener(l);
        }

        public void removeVetoableChangeListener(VetoableChangeListener l) {
            veto.removeVetoableChangeListener(l);
        }
    }
}
