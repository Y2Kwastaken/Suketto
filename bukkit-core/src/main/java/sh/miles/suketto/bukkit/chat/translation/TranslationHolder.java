package sh.miles.suketto.bukkit.chat.translation;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacer;

/**
 * Manages translations for the plugin. A plugin can have multiple translations given that support is added. This class
 * helps manage those translations. All translations are supposed to be in a "messages" config file and each key assumes
 * that the TranslationHolder is served a configuration file to use
 */
public final class TranslationHolder {

    private final FileConfiguration translations;

    /**
     * Creates a TranslationHolder
     *
     * @param translations the file of translations
     */
    public TranslationHolder(final FileConfiguration translations) {
        this.translations = translations;
    }

    /**
     * Creates a new component from the given translations and replacers
     *
     * @param key       the translation key
     * @param replacers all replacements that must be enforced
     * @return the SukettoComponent
     */
    public SukettoComponent newComponent(@NotNull final String key, @Nullable Replacer... replacers) {
        if (!translations.contains(key)) {
            throw new IllegalArgumentException("the provided key does not exist and therefore a component can not be created for key " + key);
        }

        if (replacers == null) {
            replacers = new Replacer[0];
        }

        return new SukettoComponent(translations.getString(key), replacers);
    }

}
