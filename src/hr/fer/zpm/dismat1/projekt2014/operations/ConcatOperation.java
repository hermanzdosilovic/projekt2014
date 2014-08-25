package hr.fer.zpm.dismat1.projekt2014.operations;

import hr.fer.zpm.dismat1.projekt2014.listeners.OperationListener;

/**
 * Represents concat operation.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public class ConcatOperation implements Operation {

    /** Listener of this operation. */
    private OperationListener listener;

    @Override
    public Integer execute(Integer a, Integer b) {
        Integer result = 0;
        try {
            result = Integer.parseInt(a.toString() + b.toString());
        } catch (NumberFormatException e) {
            listener.setValue(false);
        }
        return result;
    }

    @Override
    public void setListener(OperationListener invalidExpression) {
        listener = invalidExpression;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean isPrivileged() {
        return true;
    }
}
