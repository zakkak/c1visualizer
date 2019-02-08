package at.ssw.visualizer.bc.model;

import at.ssw.visualizer.model.bc.Bytecodes;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;

/**
 * Entry point for the public bytecodes data model.
 *
 * @author Christian Wimmer
 */
public interface BytecodeModel {
    /**
     * Checks if bytecodes can be available for a control flow graph, i.e. if
     * the control flow graph is either from the early bytecode parsing phase
     * of the compiler, or no method inlining was performed.
     * 
     * This does not imply that {link #getBytecodes()} returns true for this
     * control flow graph. It is possible that the class is not in the
     * classpath specified in the visualizer options.
     *
     * @return  returns true if a BlockListBuilder node is selected
     *  or a After Generation of HIR node is selected and there is
     *  only one BlockListerBuilder node, false otherwise.
     */
    public boolean hasBytecodes(ControlFlowGraph cfg);

    /**
     * Gets the bytecodes for a control flow graph.
     *
     * @return  the bytecodes, or null if not available.
     */
    public Bytecodes getBytecodes(ControlFlowGraph cfg);

    /**
     * Returns a human-readable message that explains why no bytecodes are
     * available for the specified control flow graph.
     */
    public String noBytecodesMsg(ControlFlowGraph cfg);
}
