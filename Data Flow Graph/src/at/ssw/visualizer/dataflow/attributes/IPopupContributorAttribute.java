package at.ssw.visualizer.dataflow.attributes;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Stefan Loidl
 */
public interface IPopupContributorAttribute extends INodeAttribute, ActionListener{
    public JMenuItem getMenuItem();

}
