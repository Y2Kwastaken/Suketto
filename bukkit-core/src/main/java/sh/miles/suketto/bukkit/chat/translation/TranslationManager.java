package sh.miles.suketto.bukkit.chat.translation;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacer;

/**
 * Manages translations for the plugin. A plugin can have multiple translations given that support is added. This class
 * helps manage those translations. All translations are supposed to be in a "messages" config file and each key assumes
 * that the TranslationManager is served a configuration file to use
 */
public final class TranslationManager {

    private final FileConfiguration translations;

    public TranslationManager(final FileConfiguration translations) {
        this.translations = translations;
    }

    public SukettoComponent component(@NotNull final String key, @Nullable Replacer... replacers) {
        if (!translations.contains(key)) {
            throw new IllegalArgumentException("the provided key does not exist and therefore a component can not be created for key " + key);
        }

        if (replacers == null) {
            replacers = new Replacer[0];
        }

        return new SukettoComponent(translations.getString(key), replacers);
    }

}
