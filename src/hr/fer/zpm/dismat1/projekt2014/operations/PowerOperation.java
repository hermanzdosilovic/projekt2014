package hr.fer.zpm.dismat1.projekt2014.operations;

import hr.fer.zpm.dismat1.projekt2014.listeners.OperationListener;

/**
 * Represents power operation.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public class PowerOperation implements Operation {

    /** Listener of this operation. */
    private OperationListener listener;

    @Override
    public Integer execute(Integer a, Integer b) {
        if (a == 0 && b == 0) {
            listener.setValue(false);
            return 0;
        }
        return (int) Math.pow(a, b);
    }

    @Override
    public void setListener(OperationListener invalidExpression) {
        listener = invalidExpression;
    }

    @Override
    public String toString() {
        return "^";
    }

    @Override
    public boolean isPrivileged() {
        return true;
    }
}
