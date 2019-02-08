package at.ssw.visualizer.bc.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows BCView component.
 *
 * @author Alexander Reder
 */
public class ShowBCViewAction extends AbstractAction {
    public ShowBCViewAction() {
        super("Bytecodes");
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = BCViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
