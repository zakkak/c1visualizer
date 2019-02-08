package at.ssw.visualizer.dataflow.attributes;

/**
 *
 * @author Stefan Loidl
 */
public interface IExpandNodeAttribute extends INodeAttribute {
    public boolean showBlock();
    public boolean showInstruction();
}
