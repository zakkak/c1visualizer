package at.ssw.visualizer.dataflow.attributes;

/**
 *
 * @author Stefan Loidl
 */
public class InvisibleAttribute implements IInvisibilityAttribute{

    public boolean validate() {
        return true;
    }

    public boolean removeable() {
        return true;
    }
}
