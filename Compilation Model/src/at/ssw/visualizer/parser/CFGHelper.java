package at.ssw.visualizer.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import at.ssw.visualizer.model.cfg.BasicBlock;
import at.ssw.visualizer.model.cfg.IRInstruction;
import at.ssw.visualizer.model.cfg.State;
import at.ssw.visualizer.modelimpl.cfg.BasicBlockImpl;
import at.ssw.visualizer.modelimpl.cfg.ControlFlowGraphImpl;

/**
 *
 * @author Christian Wimmer
 */
public class CFGHelper {

    protected String shortName;
    protected String name;
    protected int id;
    protected int parentId;
    private Map<String, BBHelper> helpers = new HashMap<String, BBHelper>();
    private List<BBHelper> helpersList = new ArrayList<BBHelper>();
    protected List<ControlFlowGraphImpl> elements = new ArrayList<ControlFlowGraphImpl>();
    protected ControlFlowGraphImpl resolved;

    public void add(BBHelper helper) {
        helpers.put(helper.name, helper);
        helpersList.add(helper);
    }

    public ControlFlowGraphImpl resolve(CompilationHelper lastComp, Parser parser) {
        for (BBHelper helper : helpersList) {
            for (String predecessorName : helper.predecessors) {
                BBHelper predecessor = helpers.get(predecessorName);
                if (predecessor != null) {
                    helper.defPredecessorsList.add(predecessor.basicBlock);
                } else {
                    // ignore
                    // parser.SemErr("Undefined predecessor: " + predecessorName);
                }
            }

            for (String successorName : helper.successors) {
                BBHelper successor = helpers.get(successorName);
                if (successor != null) {
                    helper.successorsList.add(successor.basicBlock);
                    successor.calcPredecessorsList.add(helper.basicBlock);
                } else {
                    // ignore
                    // parser.SemErr("Undefined successor: " + successorName);
                }
            }

            for (String xhandlerName : helper.xhandlers) {
                BBHelper xhandler = helpers.get(xhandlerName);
                if (xhandler != null) {
                    helper.xhandlersList.add(xhandler.basicBlock);
                    xhandler.calcPredecessorsList.add(helper.basicBlock);
                } else {
                    // ignore
                    // parser.SemErr("Undefined xhandler: " + xhandlerName);
                }
            }
        }

        BasicBlockImpl[] basicBlocks = new BasicBlockImpl[helpersList.size()];
        int idx = 0;
        for (BBHelper helper : helpersList) {
            basicBlocks[idx++] = helper.basicBlock;

            List<BasicBlock> predecessorsList;
            if (helper.defPredecessorsList.size() > 0) {
                // check if defined and calculated predecessors are equal
                if (helper.defPredecessorsList.size() != helper.calcPredecessorsList.size()) {
                    parser.SemErr("Defined and calculated predecessors size different: " + helper.name);
                } else {
                    for (BasicBlock block : helper.defPredecessorsList) {
                        if (!helper.calcPredecessorsList.remove(block)) {
                            parser.SemErr("Defined and calculated predecessors not matching: " + helper.name);
                        }
                    }
                    if (helper.calcPredecessorsList.size() > 0) {
                        // should never come here, but just checking...
                        parser.SemErr("Defined and calculated predecessors not matching: " + helper.name);
                    }
                }
                predecessorsList = helper.defPredecessorsList;
            } else {
                predecessorsList = helper.calcPredecessorsList;
            }

            BasicBlock[] predecessors = predecessorsList.toArray(new BasicBlock[predecessorsList.size()]);
            BasicBlock[] successors = helper.successorsList.toArray(new BasicBlock[helper.successorsList.size()]);
            BasicBlock[] xhandlers = helper.xhandlersList.toArray(new BasicBlock[helper.xhandlersList.size()]);

            BasicBlock dominator = null;
            if (helpers.get(helper.dominator) != null) {
                dominator = helpers.get(helper.dominator).basicBlock;
            }

            State[] states = null;
            if (helper.states.size() > 0) {
                states = helper.states.toArray(new State[helper.states.size()]);
            }

            IRInstruction[] hirInstructions = null;
            if (helper.hirInstructions.size() > 0) {
                hirInstructions = helper.hirInstructions.toArray(new IRInstruction[helper.hirInstructions.size()]);
            }
            IRInstruction[] lirOperations = null;
            if (helper.lirOperations.size() > 0) {
                lirOperations = helper.lirOperations.toArray(new IRInstruction[helper.lirOperations.size()]);

                if (helper.firstLirId == 0) {
                    try {
                        helper.firstLirId = Integer.parseInt(lirOperations[0].getValue(IRInstruction.LIR_NUMBER));
                    } catch (NumberFormatException ex) {
                        // Silently ignore invalid numbers.
                    }
                }
                if (helper.lastLirId == 0) {
                    try {
                        helper.lastLirId = Integer.parseInt(lirOperations[lirOperations.length - 1].getValue(IRInstruction.LIR_NUMBER));
                    } catch (NumberFormatException ex) {
                        // Silently ignore invalid numbers.
                    }
                }
            }

            helper.basicBlock.setValues(helper.name, helper.fromBci, helper.toBci, predecessors, successors, xhandlers, helper.flags, dominator, helper.loopIndex, helper.loopDepth, helper.firstLirId, helper.lastLirId, helper.probability, states, hirInstructions, lirOperations);
        }

        resolved = new ControlFlowGraphImpl(shortName, name, basicBlocks);

        if (parentId == 0) {
            lastComp.elements.add(resolved);
        } else {
            CFGHelper parent = lastComp.idToCFG.get(parentId);
            if (parent != null) {
                parent.elements.add(resolved);
            } else {
                parser.SemErr("Undefined compilation id: " + parentId);
            }
        }
        if (id != 0) {
            lastComp.idToCFG.put(id, this);
        }

        return resolved;
    }
}
