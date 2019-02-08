package at.ssw.visualizer.compilation.view.action;

import at.ssw.visualizer.compilation.view.icons.Icons;
import at.ssw.visualizer.model.CompilationModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 * Action for removing all compilations from the workspace.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
public final class RemoveAllCompilationsAction extends AbstractAction {
    public RemoveAllCompilationsAction() {
        super("Remove All Methods", new ImageIcon(ImageUtilities.loadImage(Icons.REMOVE_ALL)));
        putValue(Action.SHORT_DESCRIPTION, "Remove All Methods");
    }

    public void actionPerformed(ActionEvent event) {
        CompilationModel model = Lookup.getDefault().lookup(CompilationModel.class);
        model.clear();
    }
}
