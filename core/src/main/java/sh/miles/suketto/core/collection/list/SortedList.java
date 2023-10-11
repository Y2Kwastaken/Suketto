package sh.miles.suketto.core.collection.list;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * List that maintains a sorted order
 *
 * @param <E> the type
 */
public class SortedList<E> implements Iterable<E>, Collection<E> {

    public static final double EXPANSION_CONSTANT = 1.5;
    public static final double SHRINK_CONSTANT = 0.75;

    private final ArrayList<E> list;
    private final Comparator<? super E> comparator;

    public SortedList(final Collection<E> collection, final Comparator<? super E> comparator) {
        this.list = new ArrayList<>(collection);
        resortAll();
        this.comparator = comparator;
    }

    public SortedList(final Comparator<? super E> comparator) {
        this.list = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * Inserts an element into a list in a sorted manner
     *
     * @param element the element to insert
     * @return true
     */
    public boolean add(final E element) {
        final int insertionIndex = Collections.binarySearch(this.list, element, this.comparator);
        list.add(insertionIndex, element);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    /**
     * @param index an index
     * @return the element at the index
     */
    public E get(final int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        iterator().forEachRemaining(action);
        resortAll();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SortedList<?> that)) return false;
        return Objects.equals(list, that.list) && Objects.equals(comparator, that.comparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, comparator);
    }

    @Override
    public String toString() {
        return "SortedList{" + "list=" + list + ", comparator=" + comparator + '}';
    }

    private void resortAll() {
        this.list.sort(this.comparator);
    }
}
