package at.ssw.positionmanager.export;

import at.ssw.positionmanager.LayoutGraph;

/**
 * Instances of this interface can be used to export the LayoutGraph
 * to different formats.
 *
 * @author Stefan Loidl
 */
public interface LayoutGraphExporter {
    /** Returns if the export was successful*/
    public boolean export(LayoutGraph lg);
}
