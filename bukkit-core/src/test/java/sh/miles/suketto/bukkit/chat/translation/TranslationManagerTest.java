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
    public void should_Make_Component() {
        final SukettoComponent component = tm.newComponent("test");
        assertNotNull(component);
        final SukettoComponent componentValued = tm.newComponent("test-valued", Replacer.of("value"));
        assertNotNull(componentValued);
        assertNotEquals(componentValued.get(Replacement.of("value", 5)), componentValued.get(Replacement.of("value", 4)));
    }

    @Test
    public void should_Make_List_Component() {
        final SukettoComponentStack component = tm.newComponentStack("test-list", Replacer.of("name0"), Replacer.of("name1"));
        assertNotNull(component);
        assertNotEquals(component.get(Replacement.of("name0", "jim"), Replacement.of("name1", "jim")), component.get(Replacement.of("name0", "joe"), Replacement.of("name1", "joe")));
    }

    @Test
    public void should_Get_Nested_Value() {
        final SukettoComponent component = tm.newComponent("nested.test");
        assertNotNull(component);
    }
}
