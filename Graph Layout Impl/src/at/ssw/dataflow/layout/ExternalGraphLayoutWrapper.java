package at.ssw.dataflow.layout;

import at.ssw.positionmanager.LayoutGraph;
import at.ssw.positionmanager.LayoutManager;
import at.ssw.dataflow.options.Validator;

/**
 * Wrapper class for LayoutManager implementations that do not support the
 * ExternalGraphLayouter Interface used within the project.
 * This class can be used to test implementations of the LayoutManager interface
 * on data-flow graphs. In advanved cases it is better to implement the
 * ExternalGraphLayouter interface because then the full features of the
 * optionprovider are released.
 *
 * @author Stefan Loidl
 */
public class ExternalGraphLayoutWrapper implements ExternalGraphLayouter{

    private boolean clustering=false;
    private LayoutManager layout=null;
    private boolean movement=false;
    private boolean animation=false;

    /**
     * Creates a new instance of ExternalGraphLayoutWrapper
     * layout: the LayoutManager
     * clustering: is clustering supported?
     * movement: is node movement supported?
     * animation: is node animation supported?
     *
     * Note: Movement is supported if the routing can be done indepenent from
     *       the layout step.
     * Note: Node animation is supported if the routing consumes low time.
     */
    public ExternalGraphLayoutWrapper(LayoutManager layout, boolean clustering, boolean movement, boolean animation) {
        this.clustering=clustering;
        this.layout=layout;
        this.animation=animation;
        this.movement=movement;
    }

    public boolean isClusteringSupported() {
        return clustering;
    }

    public void doLayout(LayoutGraph graph) {
        if(layout!=null) layout.doLayout(graph);
    }

    public void doRouting(LayoutGraph graph) {
        if(layout!=null) layout.doRouting(graph);
    }


    public String[] getOptionKeys() {
        return new String[0];
    }

    public boolean setOption(String key, Object value) {
        return false;
    }

    public Object getOption(String key) {
        return null;
    }

    public Class getOptionClass(String key) {
        return null;
    }

    public Validator getOptionValidator(String key) {
        return null;
    }

    public String getOptionDescription(String key) {
        return null;
    }

    public boolean isAnimationSupported() {
        return animation;
    }

    public boolean isMovementSupported() {
        return movement;
    }

    public void setUseCurrentNodePositions(boolean b) {}

}
