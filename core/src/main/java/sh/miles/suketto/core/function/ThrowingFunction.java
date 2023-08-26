package sh.miles.suketto.core.function;

/**
 * A function that can throw an unchecked exception
 *
 * @param <T> the parameter type
 * @param <R> the return type
 */
public interface ThrowingFunction<T, R> {

    /**
     * Applies the function
     *
     * @param t the type being provided
     * @return the returning type
     * @throws Exception under circumstances where a checked exception is thrown
     */
    R apply(T t) throws Exception;
}
