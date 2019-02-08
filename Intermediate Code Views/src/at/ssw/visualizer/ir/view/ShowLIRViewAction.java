package at.ssw.visualizer.ir.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows the LIRView component.
 *
 * @author Bernhard Stiftner
 */
public class ShowLIRViewAction extends AbstractAction {
    public ShowLIRViewAction() {
        super("LIR View");
    }

    public void actionPerformed(ActionEvent event) {
        TopComponent win = LIRViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
