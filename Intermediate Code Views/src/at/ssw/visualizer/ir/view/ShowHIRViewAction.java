package at.ssw.visualizer.ir.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows the HIRView component.
 *
 * @author Bernhard Stiftner
 */
public class ShowHIRViewAction extends AbstractAction {
    public ShowHIRViewAction() {
        super("HIR View");
    }

    public void actionPerformed(ActionEvent event) {
        TopComponent win = HIRViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
