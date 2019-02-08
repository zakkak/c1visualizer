package at.ssw.visualizer.dataflow.attributes;

import javax.swing.JToggleButton;

/**
 *
 * @author Stefan Loidl
 */
public class TBInvisibilityAttribute implements IInvisibilityAttribute{

    private JToggleButton button;

    /** Creates a new instance of TBVisibilityAttribute */
    public TBInvisibilityAttribute(JToggleButton tb) {
        button=tb;
    }

    public boolean validate() {
        return button.isSelected();
    }

    public boolean removeable() {
        return false;
    }

}
