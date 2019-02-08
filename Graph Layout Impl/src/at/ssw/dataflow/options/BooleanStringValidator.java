package at.ssw.dataflow.options;

/**
 * Validates if the option is parsable as Boolean
 *
 * @author Stefan Loidl
 */
public class BooleanStringValidator implements Validator{

    private String error=null;

    public boolean validate(Object option) {
        try{
            Boolean.parseBoolean((String)option);
            return true;
        }catch(Exception e){
            error="Option not parseable as Boolean";
            return false;
        }
    }

    public String getLastErrorMessage() {
        return error;
    }

}
