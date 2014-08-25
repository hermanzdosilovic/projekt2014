package hr.fer.zpm.dismat1.projekt2014.listeners;

/**
 * Represents operation listener.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public class OperationListener implements IOperationListener {

    /** If <code>true</code> expression is valid. */
    private boolean valid;

    /**
     * Constructor.
     */
    public OperationListener() {
        this.valid = true;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValue(boolean value) {
        valid = value;
    }
}
