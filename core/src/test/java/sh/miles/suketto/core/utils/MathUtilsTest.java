package sh.miles.suketto.core.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {

    @Test
    public void testGreaterThan() {
        assertTrue(MathUtils.greaterThan(BigInteger.TEN, BigInteger.ONE));
        assertFalse(MathUtils.greaterThan(BigInteger.ONE, BigInteger.ONE));
        assertFalse(MathUtils.greaterThan(BigInteger.ONE, BigInteger.TWO));
    }

    @Test
    public void testGreaterThanOrEqualTo() {
        assertTrue(MathUtils.greaterThanOrEqualTo(BigInteger.ONE, BigInteger.ONE));
        assertTrue(MathUtils.greaterThanOrEqualTo(BigInteger.TEN, BigInteger.ONE));
        assertFalse(MathUtils.greaterThanOrEqualTo(BigInteger.ONE, BigInteger.TWO));
    }

    @Test
    public void testLessThan() {
        assertFalse(MathUtils.lessThan(BigInteger.TEN, BigInteger.ONE));
        assertFalse(MathUtils.lessThan(BigInteger.ONE, BigInteger.ONE));
        assertTrue(MathUtils.lessThan(BigInteger.ONE, BigInteger.TWO));
    }

    @Test
    public void testLessThanOrEqualTo() {
        assertTrue(MathUtils.lessThanOrEqualTo(BigInteger.ONE, BigInteger.ONE));
        assertFalse(MathUtils.lessThanOrEqualTo(BigInteger.TEN, BigInteger.ONE));
        assertTrue(MathUtils.lessThanOrEqualTo(BigInteger.ONE, BigInteger.TWO));
    }

}
