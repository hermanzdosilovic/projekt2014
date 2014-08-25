package hr.fer.zpm.dismat1.projekt2014.operations;

import hr.fer.zpm.dismat1.projekt2014.listeners.OperationListener;

/**
 * Represents operation.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public interface Operation {

    /**
     * Executes operation.
     * @param a
     *            first operand
     * @param b
     *            second operand
     * @return result of this operation on given operands.
     */
    Integer execute(Integer a, Integer b);

    /**
     * Sets <code>OperationListener</code> to this operation.
     * @param invalidExpression
     */
    void setListener(OperationListener invalidExpression);

    /**
     * Returns <code>true</code> if this operation is privileged, <code>false</code> otherwise.
     * @return <code>true</code> if this operation is privileged, <code>false</code> otherwise
     */
    boolean isPrivileged();

}
