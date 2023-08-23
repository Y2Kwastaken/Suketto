package sh.miles.suketto.bukkit.chat.translation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.miles.suketto.bukkit.chat.BasicMockTest;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacement;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacer;

import static org.junit.jupiter.api.Assertions.*;

public class TranslationManagerTest extends BasicMockTest {

    protected TranslationHolder tm;

    @Override
    @BeforeEach
    public void setup() {
        super.setup();
        tm = new TranslationHolder(plugin.getConfig());
    }

    @Test
    public void testMakeComponent() {
        final SukettoComponent component = tm.newComponent("test");
        assertNotNull(component);
        final SukettoComponent componentValued = tm.newComponent("test-valued", Replacer.of("value"));
        assertNotNull(componentValued);
        assertNotEquals(componentValued.get(Replacement.of("value", 5)), componentValued.get(Replacement.of("value", 4)));
    }

}
