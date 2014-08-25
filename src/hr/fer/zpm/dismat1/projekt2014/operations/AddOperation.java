package hr.fer.zpm.dismat1.projekt2014.operations;

import hr.fer.zpm.dismat1.projekt2014.listeners.OperationListener;

/**
 * Represents add operation.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public class AddOperation implements Operation {

    @Override
    public Integer execute(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public void setListener(OperationListener invalidExpression) {
    }

    @Override
    public String toString() {
        return "+";
    }

    @Override
    public boolean isPrivileged() {
        return true;
    }
}
