package at.ssw.visualizer.dataflow.attributes;

/**
 * This is the interface for attributes that can be applied
 * to a node. Such attributes could be: visibility or
 * full information...
 *
 * @author Stefan Loidl
 */
public interface INodeAttribute {

    /**
     * This methode is meant to return if the attribute
     * is active (from its own point of view). If this
     * method returns false and removable return true the
     * attribute can savely be removed from the attribute list.
     */
    public boolean validate();

    /**
     * Returns if the attribute can be removed from the attributelist
     * after validate returns true.
     * Some attributes are chained to nodes for lifetime. In this
     * case removable always returns false.
     */
    public boolean removeable();
}
