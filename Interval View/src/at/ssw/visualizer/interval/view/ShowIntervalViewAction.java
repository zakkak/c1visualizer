package at.ssw.visualizer.interval.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows IntervalView component.
 *
 * @author Bernhard Stiftner
 */
public class ShowIntervalViewAction extends AbstractAction {
    public ShowIntervalViewAction() {
        super("Intervals View");
    }

    public void actionPerformed(ActionEvent event) {
        TopComponent win = IntervalViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
