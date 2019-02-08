package at.ssw.positionmanager;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface LayoutManager {

    public void doLayout(LayoutGraph graph);
    public void doRouting(LayoutGraph graph);

}
