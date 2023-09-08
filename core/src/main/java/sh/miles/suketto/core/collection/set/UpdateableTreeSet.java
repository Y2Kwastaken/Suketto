package sh.miles.suketto.core.collection.set;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A TreeSet Extension that can update values as you go with ease using the
 *
 * @param <E> the type of elements maintained by this set
 */
public class UpdateableTreeSet<E> extends TreeSet<E> {

    /**
     * Creates a new empty UpdatableSet
     */
    public UpdateableTreeSet() {
        super();
    }

    /**
     * Creates a new UpdatableSet by using the collections elements
     *
     * @param c the collection to use
     */
    public UpdateableTreeSet(Collection<? extends E> c) {
        super(c);
    }

    /**
     * Creates a new empty Updatable using the comparator to sort elements
     *
     * @param comparator the comparator to sort elements with
     */
    public UpdateableTreeSet(Comparator<? super E> comparator) {
        super(comparator);
    }

    /**
     * Creates a new UpdatableTreeSet from the sorted set elements
     *
     * @param s the sorted set
     */
    public UpdateableTreeSet(SortedSet<E> s) {
        super(s);
    }

    /**
     * Update method updates the value at the specified position by removing it than re-inserting it so the value can be
     * resorted
     *
     * @param value the value to re-add
     * @return true if the value was resorted, false if the removal failed and the object could not be re-inserted
     */
    public boolean update(E value) {
        if (remove(value)) {
            return add(value);
        }
        return false;
    }
}
