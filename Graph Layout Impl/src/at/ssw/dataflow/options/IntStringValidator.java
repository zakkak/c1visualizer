package at.ssw.dataflow.options;

/**
 * Validates if the option given as string is parsable as integer.
 * Optinally min and max value can be given.
 *
 * @author Stefan Loidl
 */
public class IntStringValidator implements Validator{

    private int min, max;
    String error=null;

    /**
     * Creates a new instance of IntValidator with min and max bound
     * for the option.
     */
    public IntStringValidator(int min, int max) {
        this.min=min;
        this.max=max;
    }

    /**
     * Creates a new instance of the validator without bounds.
     */
    public IntStringValidator(){
        this(Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public boolean validate(Object option) {
        try{
            if(!(option instanceof String)){
                error="Option is not a string";
                return false;
            }
            int x=Integer.parseInt((String)option);
            if(x>=min && x <=max) {
                error=null;
                return true;
            }
            error="Value not within intervall: ["+min+","+max+"]";
            return false;
        }catch(Exception e){
            error="No integer value.";
            return false;
        }
    }

    public String getLastErrorMessage() {
        return error;
    }

}
