package sh.miles.suketto.nms.container;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A container representing a loom
 */
public interface LoomContainer {

    int BANNER_SLOT = 0;
    int DYE_SLOT = 1;
    int PATTERN_SLOT = 2;
    int RESULT_SLOT = 3;

    @NotNull
    ItemStack getBanner();

    @NotNull
    ItemStack getDye();

    @NotNull
    ItemStack getPattern();

    @NotNull
    ItemStack getResult();

    void setBanner(@NotNull final ItemStack item);

    void setDye(@NotNull final ItemStack item);

    void setPattern(@NotNull final ItemStack item);

    void setResult(@NotNull final ItemStack item);
}
