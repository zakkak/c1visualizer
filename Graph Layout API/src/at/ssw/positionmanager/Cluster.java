package at.ssw.positionmanager;

import java.util.Set;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface Cluster {
    public Cluster getOuter();
    public Set<? extends Cluster> getSuccessors();
    public Set<? extends Cluster> getPredecessors();
}
