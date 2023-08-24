package sh.miles.suketto.core.utils;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

/**
 * Utilities for arrays
 */
public final class ArrayUtils {


    /**
     * Checks if the given object is found within the array
     *
     * @param array  the array to check
     * @param object the object
     * @param <T>    type of object and array
     * @return true if the item is contained otherwise false
     */
    public static <T> boolean contains(@NotNull final T[] array, @NotNull final T object) {
        return indexOf(array, object) != -1;
    }

    /**
     * Gets the index of the specified element in the specified array
     *
     * @param array  the array to check for the item
     * @param object the object
     * @param <T>    type of object an array
     * @return the index of the item in the array or -1 if it was not found
     */
    public static <T> int indexOf(@NotNull final T[] array, @NotNull final T object) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Increases the size of the provided array by the specified amount
     *
     * @param original the original array
     * @param amount   the amount to increase the size by
     * @param <T>      type of array
     * @return the new array
     */
    public static <T> T[] grow(@NotNull final T[] original, final int amount) {
        @SuppressWarnings("unchecked")
        T[] newArray = ArrayUtils.newArray((Class<T[]>) original.getClass(), original.length + amount);
        System.arraycopy(original, 0, newArray, 0, original.length);
        return newArray;
    }

    /**
     * Shrinks the array removing the specified amount of slots, you can also specify specific indexes to be removes
     * otherwise the method will cut off the tail
     *
     * @param original the original array
     * @param amount the amount of slots to remove
     * @param removeIndexes the specific indexes to remove
     * @param <T> the type of array
     * @return the new array
     */
    public static <T> T[] shrink(@NotNull final T[] original, final int amount, @NotNull final Integer[] removeIndexes) {
        final int length = original.length - amount;
        if (length < 0) {
            throw new IllegalStateException("The specified amount to be cut from the array results in the array's size being less than 0");
        }
        @SuppressWarnings("unchecked")
        T[] newArray = ArrayUtils.newArray((Class<T[]>) original.getClass(), length);
        int offset = 0;
        for (int i = 0; i < newArray.length; i++) {
            while (ArrayUtils.contains(removeIndexes, i + offset)) {
                offset += 1;
            }
            newArray[i] = original[i + offset];
        }
        return newArray;
    }

    /**
     * Appends an item to an array
     *
     * @param array  the input array
     * @param append the item to append
     * @param <T>    the type
     * @return the new array with the appended element
     */
    public static <T> T[] append(T[] array, T append) {
        array = grow(array, 1);
        array[array.length - 1] = append;
        return array;
    }

    /**
     * Creates a new array with the known generic type
     *
     * @param type   the class type of the array
     * @param length the length of the array
     * @param <T>    the type of array
     * @return an empty array with the new asked size
     */
    public static <T> T[] newArray(@NotNull final Class<T[]> type, final int length) {
        return type.cast(Array.newInstance(type.getComponentType(), length));
    }

}
