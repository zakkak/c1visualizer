package at.ssw.visualizer.ir.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows the StateView component.
 *
 * @author Bernhard Stiftner
 */
public class ShowStateViewAction extends AbstractAction {
    public ShowStateViewAction() {
        super("State View");
    }

    public void actionPerformed(ActionEvent event) {
        TopComponent win = StateViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
