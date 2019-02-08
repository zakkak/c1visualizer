package at.ssw.positionmanager.impl;

import at.ssw.positionmanager.Cluster;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implements a cluster in the open layouter graph model of SSW.
 *
 * @author Stefan Loidl
 */
public class GraphCluster implements Cluster, Comparable<Cluster>{

    Set<? extends Cluster> successors;
    Set<? extends Cluster> predecessors;

    private int index;

    /** Creates a new instance of GraphCluster */
    public GraphCluster(int index, Set<? extends Cluster> successors, Set<? extends Cluster> predecessors) {
        this.index=index;
        if(successors!=null){
            this.successors=successors;
        }
        else this.successors=new TreeSet<Cluster>();

        if(predecessors!=null){
            this.predecessors=predecessors;
        }
        else this.predecessors=new TreeSet<Cluster>();
    }

    //Never used here!
    public Cluster getOuter() {
        return null;
    }

    public Set<? extends Cluster> getSuccessors() {
        return successors;
    }

    public Set<? extends Cluster> getPredecessors() {
        return predecessors;
    }


    public int compareTo(Cluster o) {
        if(o instanceof GraphCluster){
            return index-((GraphCluster)o).index;
        }
        return this.hashCode()-o.hashCode();
    }
}
