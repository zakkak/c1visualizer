package at.ssw.visualizer.dataflow.attributes;

import javax.swing.JToggleButton;

/**
 *
 * @author Stefan Loidl
 */
public class TBExpandAllAttribute implements IExpandNodeAttribute{

    private JToggleButton button;

    /** Creates a new instance of TBExpandAllAttribute */
    public TBExpandAllAttribute(JToggleButton tb) {
        button=tb;
    }

    public boolean showBlock() {
        return false;
    }

    public boolean showInstruction() {
        return true;
    }

    public boolean validate() {
        return button.isSelected();
    }

    public boolean removeable() {
        return false;
    }

}
