package hr.fer.zpm.dismat1.projekt2014;

import hr.fer.zpm.dismat1.projekt2014.operations.Operation;

/**
 * Represents one node of binary tree.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public class Node implements Comparable<Node> {

    /** Name of node. */
    private Integer name;

    /** Value of node. */
    private Integer value;

    /** Left child of node. */
    private Node leftChild;

    /** Right child of node. */
    private Node rightChild;

    /** Operation that this node executes. */
    private Operation operation;

    /**
     * Constructs node from given name and value of node.
     * @param name
     *            of node
     * @param value
     *            of node
     */
    public Node(Integer name, Integer value) {
        if (name != null) {
            this.name = new Integer(name);
        }
        if (value != null) {
            this.value = new Integer(value);
        }
    }

    /**
     * Constructs node from given value. Name is set to <code>null</code>.
     * @param value
     *            of node
     */
    public Node(Integer value) {
        this(null, value);
    }

    /**
     * Constructs node from given node.
     * @param node
     *            whose parameters will be used.
     */
    public Node(Node node) {
        this(node.getName(), node.getValue());
    }

    /**
     * Sets node parameters.
     * @param name
     *            of node
     * @param value
     *            of node
     */
    public void set(Integer name, Integer value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Returns name of node.
     * @return name of node.
     */
    public Integer getName() {
        return name;
    }

    /**
     * Sets new name for this node.
     * @param name
     *            new name for this node
     */
    public void setName(Integer name) {
        this.name = name;
    }

    /**
     * Returns value of this node.
     * @return value of this node
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets new value for this node.
     * @param value
     *            new value for this node
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return value.compareTo(o.getValue());
    }

    /**
     * Returns left child of this node.
     * @return left child of this node
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Assigns left child to this node
     * @param leftChild
     *            left child for this node
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Returns right child of this node.
     * @return right child of this node
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Assigns left child to this node
     * @param rightChild
     *            left child for this node
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Checks if this node is a leaf - i.e. has no children
     * @return <code>true</code> if node is leaf, <code>false</code> otherwise.
     */
    public boolean isLeaf() {
        return (rightChild == null && leftChild == null);
    }

    /**
     * Returns operation that this node holds.
     * @return operation that this node holds.
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Sets operation that this node will represent.
     * @param operation
     *            that this node will represent.
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + value + ")";
    }
}
