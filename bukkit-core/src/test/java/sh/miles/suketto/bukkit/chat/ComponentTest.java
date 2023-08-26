package sh.miles.suketto.bukkit.chat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentTest {

    private final BaseComponent[] complexTestLegacy = new ComponentBuilder()
            .append(TextComponent.fromLegacyText("This", ChatColor.AQUA))
            .bold(true)
            .color(ChatColor.GRAY)
            .append(TextComponent.fromLegacyText("is awesome!"))
            .create();
    private final BaseComponent complexTestNew = Component.of(TextComponent.fromLegacyText("This", ChatColor.AQUA))
            .bold(true)
            .color(ChatColor.GRAY)
            .append(TextComponent.fromLegacyText("is awesome!"))
            .build();


    @Test
    public void testComponentsComplex() {
        final String legacyJson = ComponentSerializer.toString(complexTestLegacy);
        final String newJson = ComponentSerializer.toString(complexTestNew);

        assertEquals(legacyJson, newJson, "legacy and new should be the same");
    }


}
