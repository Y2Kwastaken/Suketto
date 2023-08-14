package sh.miles.suketto.nms.v1_20_1.container;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.GrindstoneContainer;
import sh.miles.suketto.nms.v1_20_1.utils.ServerInventoryUtils;

public class GrindstoneContainerImpl extends AbstractSukettoContainer implements GrindstoneContainer {

    public GrindstoneContainerImpl(BaseComponent title) {
        super(title, GrindstoneContainer.ITEM_SLOT, GrindstoneContainer.ITEM_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getItem() {
        return super.getItem(GrindstoneContainer.ITEM_SLOT);
    }

    @Override
    public void setItem(@NotNull ItemStack item) {
        super.setItem(GrindstoneContainer.ITEM_SLOT, item);
    }

    @Override
    public InventoryView open(@NotNull HumanEntity human) {
        return ServerInventoryUtils.openWorkstation((CraftHumanEntity) human, Blocks.GRINDSTONE);
    }
}
