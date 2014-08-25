package hr.fer.zpm.dismat1.projekt2014.listeners;

/**
 * Represents operation listener.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public interface IOperationListener {

    /** Return <code>true</code> if expression was valid, <code>false</code> otherwise. */
    boolean isValid();

    /**
     * Sets value that this listener holds.
     * @param value
     *            that this node will hold.
     */
    void setValue(boolean value);

}
