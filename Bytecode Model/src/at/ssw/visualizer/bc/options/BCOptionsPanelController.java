package at.ssw.visualizer.bc.options;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

/**
 * Controlls the communication.
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
final class BCOptionsPanelController extends OptionsPanelController {

    private BCOptionPanel panel;
    private boolean changed;
    private PropertyChangeSupport prop;

    public BCOptionsPanelController() {
        prop = new PropertyChangeSupport(this);
        panel = new BCOptionPanel(this);
        panel.update();
    }

    public void changed() {
        if (!changed) {
            changed = true;
            prop.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
    }

    public void update() {
        panel.update();
        changed = false;
    }

    public void applyChanges() {
        panel.applyChanges();
        changed = false;
    }

    public void cancel() {
        // Nothing to do.
    }

    public boolean isValid() {
        return true;
    }

    public boolean isChanged() {
        return changed;
    }

    public HelpCtx getHelpCtx() {
        return null;
    }

    public JComponent getComponent(Lookup masterLookup) {
        return panel;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        prop.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        prop.removePropertyChangeListener(l);
    }
}
