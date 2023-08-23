package sh.miles.suketto.core.math;

/**
 * Represents operators in an enum format
 */
public enum Operator {

    /**
     * Addition
     */
    ADD('+') {
        @Override
        public double apply(double x1, double x2) {
            return x1 + x2;
        }
    },
    /**
     * Subtraction
     */
    SUBTRACT('-') {
        @Override
        public double apply(double x1, double x2) {
            return x1 - x2;
        }
    },
    /**
     * Multiplication
     */
    MULTIPLY('*') {
        @Override
        public double apply(double x1, double x2) {
            return x1 * x2;
        }
    },
    /**
     * Division
     */
    DIVIDE('/') {
        @Override
        public double apply(double x1, double x2) {
            return x1 / x2;
        }
    },
    /**
     * Modulo
     */
    MODULO('%') {
        @Override
        public double apply(double x1, double x2) {
            return x1 % x2;
        }
    };

    private final char character;

    Operator(final char character) {
        this.character = character;
    }

    /**
     * Get the character corresponding to the given operator
     *
     * @return the operator character
     */
    public char getCharacter() {
        return this.character;
    }

    /**
     * Applies the operator to the first and second element
     *
     * @param x1 the first element
     * @param x2 the second element
     * @return the new resulted elemented after the operation
     */
    public abstract double apply(double x1, double x2);


}
