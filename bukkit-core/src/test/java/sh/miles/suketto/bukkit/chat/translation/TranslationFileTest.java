package sh.miles.suketto.bukkit.chat.translation;

import net.md_5.bungee.api.chat.TextComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.miles.suketto.bukkit.chat.BasicMockTest;
import sh.miles.suketto.bukkit.chat.translation.replacing.Replacement;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public final class TranslationFileTest extends BasicMockTest {

    @Override
    @BeforeEach
    public void setup() {
        super.setup();
        plugin.saveResource("test.lang", true);
    }

    @Test
    public void should_Read_Without_Error() {
        load();
    }

    @Test
    public void should_Have_Correct_Translations() {
        final TranslationFile file = load();
        assertEquals("basic translation", file.getTranslation("basic").getRaw()[0]);
        assertArrayEquals(new String[]{"this", "is", "a", "multiline", "translation"}, file.getTranslation("multiline").getRaw());
    }

    @Test
    public void should_Handle_Replacements_Without_Error() {
        final TranslationFile file = load();
        assertArrayEquals(TextComponent.fromLegacyText("your favorite number is 5"), file.getTranslation("replacement").get(Replacement.of("number", 5)));
    }

    public TranslationFile load() {
        return assertDoesNotThrow(() -> TranslationFile.read(Path.of(plugin.getDataFolder().getAbsolutePath(), "test.lang")));
    }
}
