package at.ssw.visualizer.block.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows BlockView component.
 *
 * @author Bernhard Stiftner
 */
public class ShowBlockViewAction extends AbstractAction {
    public ShowBlockViewAction() {
        super("Blocks View");
    }

    public void actionPerformed(ActionEvent event) {
        TopComponent win = BlockViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
