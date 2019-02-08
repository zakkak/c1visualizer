package at.ssw.visualizer.dataflow.graph;

import at.ssw.positionmanager.LayoutGraph;
import at.ssw.positionmanager.Link;
import at.ssw.positionmanager.Port;
import at.ssw.positionmanager.Vertex;
import at.ssw.positionmanager.impl.GraphVertex;
import java.awt.Point;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import org.netbeans.api.visual.router.Router;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Stefan Loidl
 */
public class DirectLineRouter implements Router{

    private InstructionNodeGraphScene scene;

    /** Creates a new instance of DirectLineRouter */
    public DirectLineRouter(InstructionNodeGraphScene scene) {
        this.scene=scene;
    }

    public List<Point> routeConnection(ConnectionWidget widget) {
        List<Point> cp=new LinkedList<Point>();

        LayoutGraph graph=scene.getLayoutGraph();
        Hashtable<Widget, Vertex> WidgetToVertex=scene.getWidgetToVertex();
        if(graph==null || WidgetToVertex==null) return cp;

        Vertex v1=WidgetToVertex.get(widget.getSourceAnchor().getRelatedWidget());
        Vertex v2=WidgetToVertex.get(widget.getTargetAnchor().getRelatedWidget());

        if(v1==null || v2==null) return cp;

        //do the routing
        scene.getExternalLayouter().doRouting(graph);

        //Reset dirty state
        for(Vertex v: graph.getVertices()){
            if(v instanceof GraphVertex) ((GraphVertex)v).setDirty(false);
        }

        for(Port p: graph.getOutputPorts(v1)){
            for(Link l: graph.getPortLinks(p)){
                if(l.getTo().getVertex()==v2){
                    cp=l.getControlPoints();
                }
            }
        }


        return cp;
    }

}
