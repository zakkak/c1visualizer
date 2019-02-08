package at.ssw.dataflow.options;

/**
 * A Validator is used to verifiy if an option has the correct format.
 *
 * @author Stefan Loidl
 */
public interface Validator {

    /**
     * Validates if the option has correct format.
     */
    public boolean validate(Object option);

    /**
     * Returns the Error Message of the last validation or null
     * if it was okay.
     */
    public String getLastErrorMessage();
}
