package sh.miles.suketto.bukkit.chat.translation;

import com.google.common.base.Preconditions;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacer;
import sh.miles.suketto.bukkit.helpers.YamlConfigHelper;

import java.io.File;

/**
 * Handles translations for the entire plugin. Note, translations for this implementation are not on a per-player basis
 * and are based on the entire plugin.
 */
public class PluginTranslationManager {

    private final String localePath;
    private final YamlConfigHelper configHelper;
    private TranslationHolder holder;

    /**
     * Plugin translatoin manager
     *
     * @param plugin          the plugin
     * @param bundleFolder    bundle folder
     * @param translationFile translation file name
     * @param configHelper    config helper
     */
    public PluginTranslationManager(@NotNull final Plugin plugin, final String bundleFolder, final String translationFile, final YamlConfigHelper configHelper) {
        this.localePath = bundleFolder + File.separator + "%s" + File.separator + translationFile;
        this.configHelper = configHelper;
    }

    /**
     * Loads a locale from disk. Note, should not be run outside of reloads and start as this is a heavy io operation
     *
     * @param locale the locale to load
     */
    public void loadLocale(final String locale) {
        final String path = localePath.formatted(locale);
        this.holder = new TranslationHolder(configHelper.getYamlFile(path));
    }

    /**
     * Gets the translation holder
     *
     * @return the translation holder to get
     */
    public TranslationHolder getTranslationHolder() {
        return this.holder;
    }

    /**
     * Creates a new component from the given translations and replacers. Note this method is also contained with
     * TranslationHolder, but automatically uses the current holder
     *
     * @param key       the translation key
     * @param replacers all replacements that must be enforced
     * @return the SukettoComponent
     * @throws IllegalArgumentException if a translation holder was not yet loaded
     */
    public SukettoComponent newComponent(@NotNull final String key, @Nullable Replacer... replacers) {
        Preconditions.checkNotNull(this.holder);
        return this.holder.newComponent(key, replacers);
    }

    /**
     * Creates a new component from the given translations and replacers. Note this method is also contained with
     * TranslationHolder, but automatically uses the current holder
     *
     * @param key       the translation key
     * @param replacers all replacements that must be enforced
     * @return the SukettoComponent
     * @throws IllegalArgumentException if a translation holder was not yet loaded
     */
    public SukettoComponentStack newComponentStack(@NotNull final String key, @Nullable Replacer... replacers) {
        Preconditions.checkNotNull(this.holder);
        return this.holder.newComponentStack(key, replacers);
    }

}
