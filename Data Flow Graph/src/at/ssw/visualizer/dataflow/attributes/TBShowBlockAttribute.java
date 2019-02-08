package at.ssw.visualizer.dataflow.attributes;

import javax.swing.JToggleButton;

/**
 *
 * @author Stefan Loidl
 */
public class TBShowBlockAttribute implements IExpandNodeAttribute{

    private JToggleButton button;

    /** Creates a new instance of TBShowBlockAttribute */
    public TBShowBlockAttribute(JToggleButton tb) {
        button=tb;
    }

    public boolean showBlock() {
        return true;
    }

    public boolean showInstruction() {
        return false;
    }

    public boolean validate() {
        return button.isSelected();
    }

    public boolean removeable() {
        return false;
    }

}
