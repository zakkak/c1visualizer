package at.ssw.dataflow.options;

/**
 * Validates if the string option is parsable as double.
 * Optinally min and max value can be given.
 *
 * @author Stefan Loidl
 */
public class DoubleStringValidator implements Validator{

    private double min, max;
    String error=null;

    /**
     * Creates a new instance of IntValidator with min and max bound
     * for the option.
     */
    public DoubleStringValidator(double min, double max) {
        this.min=min;
        this.max=max;
    }

    /**
     * Creates a new instance of the validator without bounds.
     */
    public DoubleStringValidator(){
        this(Double.MIN_VALUE,Double.MAX_VALUE);
    }

    public boolean validate(Object option) {
        try{
            if(!(option instanceof String)){
                error="Option is not a string";
                return false;
            }
            double x=Double.parseDouble((String)option);
            if(x>=min && x <=max) {
                error=null;
                return true;
            }
            error="Value not within intervall: ["+min+","+max+"]";
            return false;
        }catch(Exception e){
            error="No double value.";
            return false;
        }
    }


    public String getLastErrorMessage() {
        return error;
    }

}
