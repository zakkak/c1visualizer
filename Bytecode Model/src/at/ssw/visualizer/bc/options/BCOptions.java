package at.ssw.visualizer.bc.options;

import at.ssw.visualizer.bc.options.icons.Icons;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.spi.options.OptionsCategory;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.ImageUtilities;

/**
 * This class configures the OptionPanel.
 *
 * @author Alexander Reder
 */
public final class BCOptions extends OptionsCategory {

    public OptionsPanelController create() {
        return new BCOptionsPanelController();
    }

    public String getCategoryName() {
        return "Bytecodes";
    }

    public String getTitle() {
        return "Bytecodes";
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(ImageUtilities.loadImage(Icons.BC_OPTION));
    }
}
