package at.ssw.visualizer.compilation.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.windows.TopComponent;

/**
 * Action which shows Compilation component.
 *
 * @author Bernhard Stiftner
 */
public class ShowCompilationViewAction extends AbstractAction {
    public ShowCompilationViewAction() {
        super("Compiled Methods");
    }

    public void actionPerformed(ActionEvent event) {
        TopComponent win = CompilationViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
