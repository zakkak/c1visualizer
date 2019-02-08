package at.ssw.dataflow.options;

/**
 * Interface for classes that want to publish options.
 *
 * @author Stefan Loidl
 */
public interface OptionProvider {

    /**
     * Returns all keys for existing options.
     */
    public String[] getOptionKeys();

    /**
     * Sets the option defined by key.
     */
    public boolean setOption(String key, Object value);

    /**
     * Gets the option defined by key.
     */
    public Object getOption(String key);

    /**
     * Returns the class the option should passed as.
     */
    public Class getOptionClass(String key);

    /**
     * Returns a validator for a option.
     */
    public Validator getOptionValidator(String key);

    /**
     * Return the description for the option.
     */
    public String getOptionDescription(String key);
}
