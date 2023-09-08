package sh.miles.suketto.core.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Utilities for working with math and big numbers
 */
public final class MathUtils {

    private MathUtils() {
    }

    /**
     * Checks whether the first number is greater than the second number
     *
     * @param n0 the first number
     * @param n1 the second number
     * @return true if the first number is greater than the second number
     */
    public static <T extends Number> boolean greaterThan(@NotNull final Comparable<T> n0, @NotNull final T n1) {
        return n0.compareTo(n1) > 0;
    }

    /**
     * Checks whether the first number is greater than or equal to the second number
     *
     * @param n0 the first number
     * @param n1 the second number
     * @return true if the first number is greater than or equal to the second number
     */
    public static <T extends Number> boolean greaterThanOrEqualTo(@NotNull final Comparable<T> n0, @NotNull final T n1) {
        return n0.compareTo(n1) >= 0;
    }

    /**
     * Checks whether the first number is less than the second number
     *
     * @param n0 the first number
     * @param n1 the second number
     * @return true if the first number is less than the second number
     */
    public static <T extends Number> boolean lessThan(@NotNull final T n0, @NotNull final Comparable<T> n1) {
        return n1.compareTo(n0) > 0;
    }

    /**
     * Checks whether the first number is less than or equal to the second number
     *
     * @param n0 the first number
     * @param n1 the second number
     * @return true if the first number is less than or equal to the second number
     */
    public static <T extends Number> boolean lessThanOrEqualTo(@NotNull final T n0, @NotNull final Comparable<T> n1) {
        return n1.compareTo(n0) >= 0;
    }

    /**
     * Checks whether the provided numbers are equal
     *
     * @param n0  the first number
     * @param n1  the second number
     * @param <T> type of number
     * @return true if they are equal
     */
    public static <T extends Number> boolean equalTo(@NotNull final T n0, @NotNull final T n1) {
        return n0.equals(n1);
    }


}
