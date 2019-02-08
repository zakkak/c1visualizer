package at.ssw.visualizer.nc.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 *
 * @author Alexander Reder
 */
public class NCViewAction extends AbstractAction {

    public NCViewAction() {
        super(NbBundle.getMessage(NCViewAction.class, "CTL_NCViewAction"));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = NCViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
