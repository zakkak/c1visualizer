package at.ssw.visualizer.dataflow.attributes;

import at.ssw.visualizer.dataflow.graph.InstructionNodeGraphScene;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * An ExpandStructureAttribute is an IExpandNodeAttribute that is depending on
 * a ISwitchAttribute and is contribuing a Menuitem to a Popup Menu implementing
 * the IPopupContributer interface.
 *
 * @author Stefan Loidl
 */
public class ExpandStructureAttribute implements IExpandNodeAttribute, IPopupContributorAttribute, IPathHighlightAttribute{

    private ISwitchAttribute parentAttribute;
    private JMenuItem mitem;
    private InstructionNodeGraphScene scene;

    /** Creates a new instance of ExpandStructureAttribute */
    public ExpandStructureAttribute(ISwitchAttribute parent, InstructionNodeGraphScene scene) {
        parentAttribute=parent;
        mitem=new JMenuItem(parent.getSwitchString());
        mitem.addActionListener(this);
        this.scene=scene;
    }

    public boolean showBlock() {
        return false;
    }

    public boolean showInstruction() {
        return true;
    }

    public boolean validate() {
        return parentAttribute.getSwitch();
    }

    public boolean removeable() {
        return true;
    }

    public JMenuItem getMenuItem() {
        return mitem;
    }

    public void actionPerformed(ActionEvent e) {
        parentAttribute.setSwitch(false);
        scene.refreshAll();
        scene.validate();
        scene.autoLayout();
    }

}
