package sh.miles.suketto.bukkit.chat.translation.replacement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReplacerTest {

    private static Replacer replacer;
    private static String target;


    @BeforeAll
    public static void setup() {
        replacer = new Replacer("value");
        target = "the value is {value}";
    }

    @Test
    public void testReplacerEquality() {
        final Replacer replacerIdentical = new Replacer("value");
        assertEquals(replacer, replacer);
        assertEquals(replacerIdentical, replacer);

        final Replacement childClass = new Replacement("value", 5);
        assertEquals(replacer, childClass);
    }

    @Test
    public void testReplacerApply() {
        final String expected = "the value is 5";
        assertEquals(expected, replacer.apply(target, 5));
    }

    @Test
    public void testIsContainedWithin() {
        assertTrue(replacer.isContainedWithin(target));
    }

}
