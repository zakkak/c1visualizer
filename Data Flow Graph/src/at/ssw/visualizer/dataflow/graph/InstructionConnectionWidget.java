package at.ssw.visualizer.dataflow.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Stefan Loidl
 */
public class InstructionConnectionWidget extends ConnectionWidget{

    private static final BasicStroke EXPANDEDSTROKE=new BasicStroke(2.0f);
    private static final BasicStroke UNEXPANDEDSTROKE=new BasicStroke(1.0f);
    private static final BasicStroke SELECTEDSTROKE=new BasicStroke(3.0f);


    private static final Color EXPANDEDCOLOR=new Color(200,0,0);
    private static final Color UNEXPANDEDCOLOR=Color.BLACK;
    private static final Color INTERCLUSTERCOLOR=Color.GRAY;

    //If one if the endpoints is selected
    private static final Color SELECTEDCOLOR=Color.BLACK;
    private static final Color SELECTEDBACKWARDCOLOR=Color.BLUE;
    private static final Color INTERSELECTIONCOLOR=new Color(0,100,0);





    /** Creates a new instance of InstructionConnectionWidget */
    public InstructionConnectionWidget(Scene s) {
        super(s);
    }


    protected void paintWidget(){

        boolean expanded=false;
        String cluster=null;
        //is the link crossing cluster borders?
        boolean interClusterLink=false;
        ClusterWidget cw1=null,cw2=null;
        boolean sourceSelected=false;
        boolean targetSelected=false;


        Scene scene=this.getScene();
        if(scene instanceof InstructionNodeGraphScene)
            interClusterLink=((InstructionNodeGraphScene)scene).isInterClusterLinkGrayed();



        Widget w=getSourceAnchor().getRelatedWidget();
        if(w instanceof InstructionNodeWidget){
            if(!((InstructionNodeWidget)w).isWidgetVisible()) return;
            expanded=((InstructionNodeWidget)w).isPathHighlighted();
            cw1=((InstructionNodeWidget)w).getClusterWidget();
        }
        sourceSelected=w.getState().isSelected();


        w=getTargetAnchor().getRelatedWidget();
        if(w instanceof InstructionNodeWidget){
            if(!((InstructionNodeWidget)w).isWidgetVisible()) return;
            expanded = expanded && ((InstructionNodeWidget)w).isPathHighlighted();
            cw2=((InstructionNodeWidget)w).getClusterWidget();
            interClusterLink= interClusterLink && (cw1!=cw2);
        }
        targetSelected=w.getState().isSelected();

        if(expanded) {
            this.setStroke(EXPANDEDSTROKE);
            this.setForeground(EXPANDEDCOLOR);
        }
        else if(sourceSelected && targetSelected){
            this.setStroke(SELECTEDSTROKE);
            this.setForeground(INTERSELECTIONCOLOR);
        }
        else if(sourceSelected){
            this.setStroke(SELECTEDSTROKE);
            this.setForeground(SELECTEDCOLOR);
        }
        else if(targetSelected){
            this.setStroke(SELECTEDSTROKE);
            this.setForeground(SELECTEDBACKWARDCOLOR);
        }
        else {
            this.setStroke(UNEXPANDEDSTROKE);
            if(interClusterLink) this.setForeground(INTERCLUSTERCOLOR);
            else this.setForeground(UNEXPANDEDCOLOR);
        }

        super.paintWidget();
    }

}
