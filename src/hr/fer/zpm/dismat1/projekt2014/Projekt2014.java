package hr.fer.zpm.dismat1.projekt2014;

import hr.fer.zpm.dismat1.projekt2014.listeners.OperationListener;
import hr.fer.zpm.dismat1.projekt2014.operations.AddOperation;
import hr.fer.zpm.dismat1.projekt2014.operations.ConcatOperation;
import hr.fer.zpm.dismat1.projekt2014.operations.DivideOperation;
import hr.fer.zpm.dismat1.projekt2014.operations.MultiplyOperation;
import hr.fer.zpm.dismat1.projekt2014.operations.Operation;
import hr.fer.zpm.dismat1.projekt2014.operations.PowerOperation;
import hr.fer.zpm.dismat1.projekt2014.operations.SubtractOperation;
import hr.fer.zpm.dismat1.projekt2014.util.Permutation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Solution of Projekt2014 from class "Diskontna matematika 1".
 * @author Herman Zvonimir Došilović
 * @version 1.0
 */
public class Projekt2014 {

    /** Number we want to get with expression. */
    private static final int wantedResult = 2014;

    /** Number of operators in expression. */
    private static int numberOfOperators = 8;

    /** Number of available operations. */
    private static final int numberOfOperations = 6; // +, -, *, /, ^, concatenation

    /** Number of all operator combinations. */
    private static final int numberOfOperatorCombinations = (int) Math.pow(numberOfOperations, numberOfOperators);

    /** Combinations of operators. */
    private static short[][] combinations;

    /** Counts how many times wanted result is achieved. */
    private static int resultCounter;

    /** Available operations. */
    private static Operation[] operations;

    static {
        operations = new Operation[numberOfOperations];
        operations[0] = new AddOperation();
        operations[1] = new SubtractOperation();
        operations[2] = new MultiplyOperation();
        operations[3] = new DivideOperation();
        operations[4] = new PowerOperation();
        operations[5] = new ConcatOperation();
    }

    /** Stream in which result will be written. */
    private static FileOutputStream fileOutputStream;

    /** Valid expression listener */
    private static OperationListener validExpression = new OperationListener();

    /**
     * Program entry. Program expects two arguments from command line. First is path to file in which results will be
     * written. Second is number of operations to use in calculating solution.
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        String path = "./output.txt";
        if (args.length == 2) {
            path = args[0];
            try {
                numberOfOperators = Integer.parseInt(args[1]);
                if (numberOfOperators < 1) {
                    System.out.println("Number must be greater than 0.");
                    System.exit(-1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please provide valid number greater than 0.");
                System.exit(-1);
            }
        } else if (args.length == 1) {
            path = args[0];
        }

        try {
            fileOutputStream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(-1);
        }

        System.out.println("Projekt2014");

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < operations.length; i++) {
            operations[i].setListener(validExpression);
        }

        Permutation<Node> permutation = new Permutation<>();
        for (int i = 1; i <= numberOfOperators; i++) {
            permutation.add(new Node(i));
        }

        combinations = new short[numberOfOperatorCombinations][numberOfOperators];
        for (int i = 0; i < numberOfOperatorCombinations; i++) {
            combinations[i] = convertCombination(i);
        }

        List<BinaryTree> trees = new ArrayList<>();
        do {
            if (BinaryTree.isTreePermutation(permutation)) {
                BinaryTree tree = new BinaryTree(new ArrayList<>(permutation));
                trees.add(tree);
            }
        } while (permutation.nextPermutation());

        for (BinaryTree tree : trees) {
            processTree(tree);
        }

        long endTime = System.currentTimeMillis();

        int duration = (int) (endTime - startTime);
        long second = (duration / 1000) % 60;
        long minute = (duration / (1000 * 60)) % 60;
        long hour = (duration / (1000 * 60 * 60)) % 24;

        String timeFormated = String.format("%02d:%02d:%02d", hour, minute, second);

        System.out.println("Time: " + timeFormated);
        System.out.println("Number of expressions found: " + resultCounter);
    }

    /**
     * Processes given tree. Runs all possible operations on it.
     * @param tree
     *            for which process is needed.
     */
    private static void processTree(BinaryTree tree) {
        int size = numberOfOperatorCombinations;
        short[] operationSet;
        Node node;
        Node root = tree.getRoot();
        for (int i = 0; i < size; i++) {
            operationSet = combinations[i];
            for (int j = 0; j < numberOfOperators; j++) {
                node = tree.getLinearNode(j);
                node.setOperation(operations[operationSet[j]]);
            }
            validExpression.setValue(true);
            Integer result = evaluateTree(root);
            if (result == wantedResult && validExpression.isValid()) {
                resultCounter++;
                String expression = getExpression(root, new StringBuilder("")) + "\r\n";
                try {
                    fileOutputStream.write(expression.getBytes());
                } catch (IOException e) {
                    System.out.println("Error while writing to file!");
                    System.exit(-1);
                }
            }
        }
    }

    /**
     * Returns expression represented by tree.
     * @param node
     *            root of tree
     * @param expression
     *            initial expression that this tree represents.
     * @return expression represented by tree.
     */
    private static String getExpression(Node node, StringBuilder expression) {
        if (node.isLeaf()) {
            return node.getValue().toString();
        }

        if (node.getOperation().isPrivileged()) {
            expression.append("(");
        }

        expression.append(getExpression(node.getLeftChild(), new StringBuilder("")));
        expression.append(node.getOperation());
        expression.append(getExpression(node.getRightChild(), new StringBuilder("")));

        if (node.getOperation().isPrivileged()) {
            expression.append(")");
        }

        return expression.toString();
    }

    /**
     * Evaluates tree based on the given root of tree.
     * @param node
     *            root of the tree to evaluate
     * @return result of expression represented by tree
     */
    private static Integer evaluateTree(Node node) {
        if (node.isLeaf()) {
            return node.getValue();
        }
        Operation operation = node.getOperation();
        return operation.execute(evaluateTree(node.getLeftChild()), evaluateTree(node.getRightChild()));
    }

    /**
     * Converts given number into base specified by <code>numberOfOperations</code>. Make sure that
     * <code>numberOfOperations</code> is not greater than 10. This method assumes that given number is positive.
     * @param number
     *            to convert
     * @return array of <code>short</code>s. Each element in this array represents one digit in base specified by
     *         <code>numberOfOperations</code>. Digit at index 0 has the smallest weight.
     */
    private static short[] convertCombination(int number) {
        short[] combination = new short[numberOfOperators];
        for (int i = numberOfOperators - 1; i >= 0; i--) {
            combination[i] = (short) (number % numberOfOperations);
            number /= numberOfOperations;
        }
        return combination;
    }
}
