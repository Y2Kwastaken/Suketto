package sh.miles.suketto.bukkit.chat.translation;

import net.md_5.bungee.chat.ComponentSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.miles.suketto.bukkit.chat.BasicMockTest;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacement;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacer;

import static org.junit.jupiter.api.Assertions.*;

public class TranslationManagerTest extends BasicMockTest {

    protected TranslationManager tm;

    @Override
    @BeforeEach
    public void setup() {
        super.setup();
        tm = new TranslationManager(plugin.getConfig());
    }

    @Test
    public void testMakeComponent() {
        final SukettoComponent component = tm.component("test");
        assertNotNull(component);
        final SukettoComponent componentValued = tm.component("test-valued", Replacer.of("value"));
        assertNotNull(componentValued);
        assertNotEquals(componentValued.get(Replacement.of("value", 5)), componentValued.get(Replacement.of("value", 4)));
    }

}
