package sh.miles.suketto.bukkit.helpers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class that aids in creating and getting configuration files and directories
 */
public class YamlConfigHelper {

    private final Plugin plugin;

    /**
     * Creates a yaml config helper
     *
     * @param plugin the plugin to use
     */
    public YamlConfigHelper(final Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads the YAML file from the disk
     *
     * @param name the name of the file to load
     * @return the FileConfiguration loaded
     */
    public FileConfiguration loadYamlFile(@NotNull final String name) {
        return YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), name));
    }

    /**
     * Makes a directory with the specified name
     *
     * @param name the name of the directory
     * @return the new directory path
     */
    public Path mkdir(@NotNull final String name) {
        final Path directory = Paths.get(plugin.getDataFolder().getAbsolutePath(), name.replace("/", File.separator));
        if (Files.notExists(directory, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return directory;
    }

    /**
     * Gets a yaml file
     *
     * @param name the name of the yaml file to get
     * @return the file configuration resulting from the getting of the yaml file
     */
    public FileConfiguration getYamlFile(@NotNull final String name) {
        final Path path = Path.of(plugin.getDataFolder().getAbsolutePath(), name);
        if (Files.notExists(path)) {
            plugin.saveResource(name, false);
        }

        final FileConfiguration loadedConfiguration = loadYamlFile(name);
        if (Files.notExists(path)) {
            try {
                loadedConfiguration.save(path.toFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return loadedConfiguration;
    }

    /**
     * Saves the yaml file
     *
     * @param plugin the plugin
     * @param config the config to save
     * @param name   the name of the config
     */
    public static void saveYamlFile(@NotNull final Plugin plugin, @NotNull final FileConfiguration config, @NotNull final String name) {
        try {
            config.save(new File(plugin.getDataFolder(), name));
        } catch (final IOException e) {
            Bukkit.getLogger().throwing("YamlConfigHelper", "saveYamlFile", e);
        }
    }
}
