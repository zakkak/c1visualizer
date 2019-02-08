package at.ssw.dataflow.layout;

import at.ssw.positionmanager.LayoutManager;
import at.ssw.dataflow.options.OptionProvider;

/**
 * Extended Interface for LayoutManager supporting options and advanced
 * features like clustering and movemement.
 *
 * @author Stefan Loidl
 */
public interface ExternalGraphLayouter extends OptionProvider, LayoutManager{

    /** Does the algorithm support clustering? */
    public boolean isClusteringSupported();
    /** Is the routing algorithms fast enough to handle an animation task? */
    public boolean isAnimationSupported();
    /** Is routing possible independet from the layout task? */
    public boolean isMovementSupported();

    /**Defines if the layouter should build on the current node positions*/
    public void setUseCurrentNodePositions(boolean b);

}
