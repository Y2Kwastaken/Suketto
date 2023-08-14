package sh.miles.suketto.nms.v1_20_1.container;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.EnchantingContainer;
import sh.miles.suketto.nms.v1_20_1.utils.ServerAdapter;
import sh.miles.suketto.nms.v1_20_1.utils.ServerInventoryUtils;

public class EnchantingContainerImpl extends AbstractSukettoContainer implements EnchantingContainer {

    public EnchantingContainerImpl(BaseComponent title) {
        super(title, EnchantingContainer.ITEM_SLOT, EnchantingContainer.LAPIS_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getItem() {
        return super.getItem(EnchantingContainer.ITEM_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getLapis() {
        return super.getItem(EnchantingContainer.LAPIS_SLOT);
    }

    @Override
    public void setItem(@NotNull final ItemStack item) {
        super.setItem(EnchantingContainer.ITEM_SLOT, item);
    }

    @Override
    public void setLapis(@NotNull ItemStack item) {
        super.setItem(EnchantingContainer.LAPIS_SLOT, item);
    }

    @Override
    public InventoryView open(@NotNull HumanEntity human) {
        return ServerInventoryUtils.openWorkstation((CraftHumanEntity) human, Blocks.ENCHANTING_TABLE, (pos) ->
                new SimpleMenuProvider((i, playerinventory, player) ->
                        new EnchantmentMenu(i, playerinventory, ContainerLevelAccess.create(player.level(), pos))
                        , ServerAdapter.toMinecraftChat(super.getTitle())
                ));
    }
}
