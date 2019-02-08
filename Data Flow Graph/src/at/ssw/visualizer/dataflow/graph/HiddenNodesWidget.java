package at.ssw.visualizer.dataflow.graph;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.JPopupMenu;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Stefan Loidl
 */
public class HiddenNodesWidget extends Widget{

    //true if arrow points upward
    private boolean upward;
    private final int ARROWHIGHT=4;

     /**
     * Creates a hidden-nodes widget.
     * @param up is true if arrow shall point upwards.
     */
    public HiddenNodesWidget (Scene scene, boolean up) {
        super (scene);
        upward=up;
    }

    /**
     * Calculates a client area of the widget. (The width is 0, as
     * it should be defined by parent widget width)
     * @return the calculated client area
     */
    protected Rectangle calculateClientArea () {
            return new Rectangle (0, 0, 0, ARROWHIGHT);
    }

    /**
     * Paints the separator widget.
     */
    protected void paintWidget() {
        Graphics2D gr = getGraphics();
        gr.setColor (getForeground());
        Rectangle bounds = getBounds ();
        Insets insets = getBorder ().getInsets ();
        int x[]=new int[4];
        int y[]=new int[4];

        x[0]=0;
        x[1]=bounds.width -insets.left -insets.right;
        x[2]=(bounds.width - insets.left - insets.right)/2;
        x[3]=0;

        if(upward){
            y[0]=ARROWHIGHT;
            y[1]=ARROWHIGHT;
            y[2]=0;
            y[3]=ARROWHIGHT;
        }
        else{
            y[0]=0;
            y[1]=0;
            y[2]=ARROWHIGHT;
            y[3]=0;
        }

        Polygon p=new Polygon(x,y,4);

        gr.drawPolygon(p);
        gr.fill(p);
    }


}
