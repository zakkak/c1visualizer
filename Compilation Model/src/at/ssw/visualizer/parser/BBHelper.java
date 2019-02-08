package at.ssw.visualizer.parser;

import java.util.ArrayList;
import java.util.List;
import at.ssw.visualizer.model.cfg.BasicBlock;
import at.ssw.visualizer.model.cfg.IRInstruction;
import at.ssw.visualizer.model.cfg.State;
import at.ssw.visualizer.modelimpl.cfg.BasicBlockImpl;

/**
 *
 * @author Christian Wimmer
 */
public class BBHelper {

    protected String name;
    protected int fromBci;
    protected int toBci;
    protected String[] predecessors;
    protected String[] successors;
    protected String[] xhandlers;
    protected String[] flags;
    protected String dominator;
    protected int loopIndex;
    protected int loopDepth;
    protected int firstLirId;
    protected int lastLirId;
    protected double probability = Double.NaN;
    protected List<State> states = new ArrayList<State>();
    protected List<IRInstruction> hirInstructions = new ArrayList<IRInstruction>();
    protected List<IRInstruction> lirOperations = new ArrayList<IRInstruction>();
    protected BasicBlockImpl basicBlock = new BasicBlockImpl();
    protected List<BasicBlock> defPredecessorsList = new ArrayList<BasicBlock>();
    protected List<BasicBlock> calcPredecessorsList = new ArrayList<BasicBlock>();
    protected List<BasicBlock> successorsList = new ArrayList<BasicBlock>();
    protected List<BasicBlock> xhandlersList = new ArrayList<BasicBlock>();
}
