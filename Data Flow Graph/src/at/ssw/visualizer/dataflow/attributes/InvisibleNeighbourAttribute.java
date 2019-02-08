package at.ssw.visualizer.dataflow.attributes;

import at.ssw.visualizer.dataflow.graph.InstructionNodeWidget;
import javax.swing.JToggleButton;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Stefan Loidl
 */
public class InvisibleNeighbourAttribute implements IAdditionalWidgetAttribute{

    private Widget widget;
    private InstructionNodeWidget neighbour;
    private boolean removeable;

    /** Creates a new instance of TBAdditionalWidgetAttribute */
    public InvisibleNeighbourAttribute(Widget w, InstructionNodeWidget n, boolean removeable) {
        widget=w;
        neighbour=n;
        this.removeable=removeable;
    }

    public Widget getWidget() {
        return widget;
    }

    public boolean validate() {
        return !neighbour.isWidgetVisible();
    }

    public boolean removeable() {
        return removeable;
    }

}
