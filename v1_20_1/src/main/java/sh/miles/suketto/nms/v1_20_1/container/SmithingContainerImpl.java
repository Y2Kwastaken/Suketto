package sh.miles.suketto.nms.v1_20_1.container;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.SmithingContainer;
import sh.miles.suketto.nms.v1_20_1.utils.ServerInventoryUtils;

public class SmithingContainerImpl extends AbstractSukettoContainer implements SmithingContainer {

    public SmithingContainerImpl(@NotNull final BaseComponent title) {
        super(title, SmithingContainer.TEMPLATE_INDEX, SmithingContainer.RESULT_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getTemplate() {
        return super.getItem(SmithingContainer.TEMPLATE_INDEX);
    }

    @NotNull
    @Override
    public ItemStack getTool() {
        return super.getItem(SmithingContainer.TOOL_INDEX);
    }

    @NotNull
    @Override
    public ItemStack getUpgrade() {
        return super.getItem(SmithingContainer.UPGRADE_INDEX);
    }

    @NotNull
    @Override
    public ItemStack getResult() {
        return super.getItem(SmithingContainer.RESULT_SLOT);
    }

    @Override
    public void setTemplate(@NotNull ItemStack item) {
        super.setItem(SmithingContainer.TEMPLATE_INDEX, item);
    }

    @Override
    public void setTool(@NotNull ItemStack item) {
        super.setItem(SmithingContainer.TOOL_INDEX, item);
    }

    @Override
    public void setUpgrade(@NotNull ItemStack item) {
        super.setItem(SmithingContainer.UPGRADE_INDEX, item);
    }

    @Override
    public void setResult(@NotNull ItemStack item) {
        super.setItem(SmithingContainer.RESULT_SLOT, item);
    }

    @Override
    public InventoryView open(@NotNull final HumanEntity human) {
        return ServerInventoryUtils.openWorkstation((CraftHumanEntity) human, Blocks.SMITHING_TABLE);
    }
}
