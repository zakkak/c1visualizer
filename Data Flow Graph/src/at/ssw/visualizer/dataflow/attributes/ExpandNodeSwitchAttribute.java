package at.ssw.visualizer.dataflow.attributes;

import at.ssw.visualizer.dataflow.graph.InstructionNodeGraphScene;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Stefan Loidl
 */
public class ExpandNodeSwitchAttribute implements ISwitchAttribute, IPopupContributorAttribute, IExpandNodeAttribute, IPathHighlightAttribute{

    private boolean value, remove;
    private String description;
    private JMenuItem mitem;
    private InstructionNodeGraphScene scene;

    /** Creates a new instance of ExpandInfluenceSwitchAttribute */
    public ExpandNodeSwitchAttribute(boolean initialValue, String desc, boolean remove, InstructionNodeGraphScene scene) {
        value=initialValue;
        description=desc;
        this.remove=remove;
        mitem=new JMenuItem(description);
        mitem.addActionListener(this);
        this.scene=scene;
    }

    public void setSwitch(boolean s) {
        value=s;
    }

    public boolean getSwitch() {
        return value;
    }

    public String getSwitchString() {
        return description;
    }

    public boolean validate() {
        return value;
    }

    public boolean removeable() {
        return remove;
    }

    public JMenuItem getMenuItem() {
        return mitem;
    }

    public void actionPerformed(ActionEvent e) {
        value=!value;
        scene.refreshAll();
        scene.validate();
        scene.autoLayout();

    }

    public boolean showBlock() {
        return false;
    }

    public boolean showInstruction() {
        return true;
    }
}
