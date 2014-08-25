package hr.fer.zpm.dismat1.projekt2014.util;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <code>Permutation</code> collection is used as any other collection from Java Collection Framework except it works
 * with <code>Comparable</code> types. On this collection you can ask for <code>nextPermutation</code>. This function
 * will generate next permutation of elements in this collection and will return <code>true</code> if more permutations
 * are available.
 * @author Herman Zvonimir Došilović
 * @version Projekt2014
 * @param <T>
 *            type of <code>Comparable</code> value that this collection will store.
 */
public class Permutation<T extends Comparable<T>> extends AbstractList<T> {

    /** Working set of elements. **/
    private List<T> elements;

    /** Initial set of elements given by user. **/
    private List<T> initialElements;

    /** Sorted set of initial elements. **/
    private List<T> sortedElements;

    /** If <code>true</code> user gave sorted elements, <code>false</code> otherwise. **/
    private boolean fromBegin;

    /**
     * Constructs new <code>Permutation</code> collection.
     * @param elements
     *            array of elements.
     */
    @SafeVarargs
    public Permutation(T... elements) {
        this(Arrays.asList(elements));
    }

    /**
     * Constructs new <code>Permutation</code> collection. Will make its own copy of elements.
     * @param elements
     *            collection from which it needs to take over elements.
     */
    public Permutation(Collection<T> elements) {
        this.elements = new ArrayList<>(elements);
        this.initialElements = new ArrayList<>(elements);
        this.sortedElements = new ArrayList<>(elements);
        Collections.sort(sortedElements);
        fromBegin = this.elements.equals(this.sortedElements);
    }

    /**
     * Generates next permutation of elements it this collection.
     * @return <code>true</code> if more permutations are available, <code>false</code> otherwise.
     */
    public boolean nextPermutation() {
        int i, j;
        for (i = elements.size() - 2; i >= 0; i--) {
            if (elements.get(i).compareTo(elements.get(i + 1)) < 0) {
                break;
            }
        }

        if (i < 0) {
            if (fromBegin) {
                elements = new ArrayList<>(initialElements);
                return false;
            }
            elements = new ArrayList<>(sortedElements);
            return true;
        }

        int minn = i + 1;
        for (j = i + 1; j < elements.size(); j++) {
            if (elements.get(i).compareTo(elements.get(j)) < 0 && elements.get(j).compareTo(elements.get(minn)) < 0) {
                minn = j;
            }
        }

        T element = elements.get(minn);
        elements.set(minn, elements.get(i));
        elements.set(i, element);

        Collections.sort(elements.subList(i + 1, elements.size()));
        if (fromBegin == false && elements.equals(initialElements)) {
            return false;
        }
        return true;
    }

    /**
     * Resets this collection to its initial order.
     */
    public void reset() {
        this.elements = new ArrayList<>(initialElements);
    }

    @Override
    public T get(int index) {
        return elements.get(index);
    }

    @Override
    public boolean add(T e) {
        return elements.add(e);
    }

    @Override
    public int size() {
        return elements.size();
    }
}
