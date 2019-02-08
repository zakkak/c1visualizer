package at.ssw.visualizer.dataflow.attributes;

import at.ssw.visualizer.dataflow.graph.InstructionNodeGraphScene;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 * Expand Attribute that contributes a menuitem to the popupmenu which enables the user to
 * deactivate the attribute itself.
 *
 * @author Stefan Loidl
 */
public class SelfSwitchingExpandAttribute implements IPopupContributorAttribute, IExpandNodeAttribute, IPathHighlightAttribute{

    private boolean state;
    private JMenuItem mitem;
    private InstructionNodeGraphScene scene;

    /** Creates a new instance of SelfSwitchingExpandAttribute */
    public SelfSwitchingExpandAttribute(String description, InstructionNodeGraphScene scene) {
        state=true;
        mitem=new JMenuItem(description);
        mitem.addActionListener(this);
        this.scene=scene;
    }

    public JMenuItem getMenuItem() {
        return mitem;
    }

    public boolean validate() {
        return state;
    }

    public boolean removeable() {
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        state=false;
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
