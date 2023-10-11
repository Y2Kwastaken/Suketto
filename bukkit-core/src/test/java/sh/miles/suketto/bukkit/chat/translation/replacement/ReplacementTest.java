package sh.miles.suketto.bukkit.chat.translation.replacement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sh.miles.suketto.bukkit.chat.translation.replacing.Replacement;
import sh.miles.suketto.bukkit.chat.translation.replacing.Replacer;

import static org.junit.jupiter.api.Assertions.*;


public class ReplacementTest {

    private static Replacement replacement;
    private static String target;


    @BeforeAll
    public static void setup() {
        replacement = new Replacement("value", 5);
        target = "Your value is {value}";
    }

    @Test
    public void testApplyReplacement() {
        final String expected = "Your value is 5";
        assertEquals(expected, replacement.apply(target)[0]);
    }

    @Test
    public void testReplacementEquality() {
        final Replacement equal = new Replacement("value", 5);
        assertEquals(equal, replacement);
    }

    @Test
    public void testContainedWithin() {
        assertTrue(replacement.isContainedWithin(target));
    }

}
