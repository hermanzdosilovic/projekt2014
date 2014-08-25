package hr.fer.zpm.dismat1.projekt2014;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents binary tree data structure. Every node of this data structure is type of <code>Node</code> class.
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public class BinaryTree {

    /** Represents root of this tree. */
    private Node root;

    /** Stores intern nodes of tree in linear order. */
    private Node[] linearInternNodes;

    /** Length of <code>linearInternNodes</code>. */
    private Integer countSequenceLength;

    /** Node marker. */
    private int nodeMarker;

    /**
     * Constructs tree from given preorder list of elements.
     * @param elements
     *            preorder list of elements.
     */
    public BinaryTree(List<Node> elements) {
        if (elements.size() == 0) {
            throw new RuntimeException("elements size should be greater than 0");
        }

        linearInternNodes = new Node[1 << (elements.size() + 1)];
        countSequenceLength = 0;

        nodeMarker = 1;
        this.root = createNode(elements, 1);
        root.setValue(null);
    }

    /**
     * Creates node from given preorder list of elements.
     * @param elements
     *            preorder list of elements.
     * @param name
     *            of new node
     * @return returns created node
     */
    private Node createNode(List<Node> elements, Integer name) {
        if (elements.isEmpty()) {
            return null;
        }

        Node node = new Node(elements.get(0));
        node.setName(name);
        elements.remove(0);

        int i;
        for (i = 0; i < elements.size(); i++) {
            if (node.compareTo(elements.get(i)) < 0) {
                break;
            }
        }

        Node leftChild = createNode(new ArrayList<>(elements.subList(0, i)), 2 * name);
        if (leftChild == null) {
            leftChild = new Node(2 * name, null);
            leftChild.setValue(nodeMarker++);
        }

        Node rightChild = createNode(new ArrayList<>(elements.subList(i, elements.size())), 2 * name + 1);
        if (rightChild == null) {
            rightChild = new Node(2 * name + 1, null);
            rightChild.setValue(nodeMarker++);
        }

        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);

        node.setValue(null);

        linearInternNodes[countSequenceLength++] = node;

        return node;
    }

    /**
     * From given order of <code>Comparable</code> elements this method checks if this order of elements is tree
     * permutation.
     * <p>
     * A permutation <i>P = P1, P2, ..., Pn</i> of comparable objects is a tree permutation if it is
     * <b>231-avoiding</b>. There are no such indices <i>i < j < k</i> such that <i>Pk < Pi < Pj</i>.
     * </p>
     * This check runs in <code>O(n^3)</code>.
     * @param elements
     *            collection of comparable elements
     * @return <code>true</code> if order of elements is tree permutation.
     */
    public static <T extends Comparable<T>> boolean isTreePermutation(Collection<T> elements) {
        List<T> list = new ArrayList<>(elements);
        for (int k = list.size() - 1; k >= 0; k--) {
            for (int j = k - 1; j >= 0; j--) {
                for (int i = j - 1; i >= 0; i--) {
                    if (list.get(k).compareTo(list.get(i)) < 0 && list.get(i).compareTo(list.get(j)) < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns node from linear orderer list of nodes.
     * @param index
     *            of node
     * @return node at specified index in linear ordered list of nodes.
     */
    public Node getLinearNode(int index) {
        return linearInternNodes[index];
    }

    /**
     * Returns root of this tree.
     * @return root of this tree
     */
    public Node getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return visitTreeString(root, new StringBuilder("Root: "));
    }

    /**
     * Returns string representation of this tree.
     * @param node
     *            root of tree
     * @param sb
     *            current tree representation
     * @return string representation of this tree
     */
    private String visitTreeString(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append("null\n");
            return sb.toString();
        }
        sb.append(node + "\n" + node + " [L]-> ");
        visitTreeString(node.getLeftChild(), sb);
        sb.append(node + " [R]-> ");
        visitTreeString(node.getRightChild(), sb);
        return sb.toString();
    }
}
