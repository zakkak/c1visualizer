package at.ssw.visualizer.dataflow.graph;

import java.util.Set;

/**
 * Listener interface for the Node Graph Scene. Listeners are notified on changes
 * within the Scene.
 *
 * @author Stefan Loidl
 */
public interface InstructionSceneListener {
    public void doubleClicked(InstructionNodeWidget w);
    public void updateNodeData();
    public void selectionChanged(Set<InstructionNodeWidget> w);
}
