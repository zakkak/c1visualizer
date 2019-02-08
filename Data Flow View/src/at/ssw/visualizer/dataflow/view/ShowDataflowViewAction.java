package at.ssw.visualizer.dataflow.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows DataflowView component.
 *
 * @author Stefan Loidl
 */
public class ShowDataflowViewAction extends AbstractAction {
    public ShowDataflowViewAction() {
        super("Data Flow View");
    }

    public void actionPerformed(ActionEvent event) {
        TopComponent win = DataflowViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
