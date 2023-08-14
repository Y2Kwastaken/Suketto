package sh.miles.suketto.nms.container;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A Custom container representing a smithing table
 */
public interface SmithingContainer {

    int TEMPLATE_INDEX = 0;
    int TOOL_INDEX = 1;
    int UPGRADE_INDEX = 2;
    int RESULT_SLOT = 3;

    @NotNull
    ItemStack getTemplate();

    @NotNull
    ItemStack getTool();

    @NotNull
    ItemStack getUpgrade();

    @NotNull
    ItemStack getResult();

    void setTemplate(@NotNull final ItemStack item);

    void setTool(@NotNull final ItemStack item);

    void setUpgrade(@NotNull final ItemStack item);

    void setResult(@NotNull final ItemStack item);
}
