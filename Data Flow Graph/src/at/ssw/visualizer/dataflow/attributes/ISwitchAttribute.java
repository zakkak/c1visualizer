package at.ssw.visualizer.dataflow.attributes;

/**
 *
 * @author Stefan Loidl
 */
public interface ISwitchAttribute extends INodeAttribute{

    public void setSwitch(boolean s);

    public boolean getSwitch();

    /**
     * Returns a string as description for the switch.
     */
    public String getSwitchString();
}
