package at.ssw.visualizer.bc.modelimpl;

import at.ssw.visualizer.model.bc.Bytecodes;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import java.util.SortedMap;

/**
 * This class holds the bytecode of a method and provides severel methods
 * accessing the details.
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
public class BytecodesImpl implements Bytecodes {

    private ControlFlowGraph controlFlowGraph;
    private String name;
    private String shortName;

    private String prolog;
    private String attributes;
    private SortedMap<Integer, String> byteCodes;

    public BytecodesImpl(ControlFlowGraph controlFlowGraph, String name, String shortName, String prolog, String attributes, SortedMap<Integer, String> byteCodes) {
        this.controlFlowGraph = controlFlowGraph;
        this.name = name;
        this.shortName = shortName;
        this.prolog = prolog;
        this.attributes = attributes;
        this.byteCodes = byteCodes;
    }

    @Override
    public ControlFlowGraph getControlFlowGraph() {
        return controlFlowGraph;
    }

    @Override
    public void parseBytecodes() {
        // Nothing to parse
    }
    
    @Override
    public String getBytecodes(int fromBCI, int toBCI) {
        StringBuilder sb = new StringBuilder();
        for (Integer key : byteCodes.subMap(fromBCI, toBCI).keySet()) {
            String keyString = Integer.toString(key);
            sb.append(keyString).append(":");
            sb.append("     ".substring(Math.min(keyString.length(), 4)));
            sb.append(byteCodes.get(key));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getEpilogue() {
        return attributes;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
