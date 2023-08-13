package sh.miles.suketto.nms.v1_20_1.inventory;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.inventory.AnvilCustomInventory;
import sh.miles.suketto.nms.v1_20_1.utils.ServerInventoryUtils;

public class AnvilCustomInventoryImpl extends AbstractSukettoCustomInventory implements AnvilCustomInventory {

    public AnvilCustomInventoryImpl(BaseComponent title) {
        super(title, AnvilCustomInventory.FIRST_INDEX, AnvilCustomInventory.RESULT_INDEX);
    }

    @NotNull
    @Override
    public ItemStack getFirst() {
        return super.slots.get(AnvilCustomInventory.FIRST_INDEX);
    }

    @NotNull
    @Override
    public ItemStack getSecond() {
        return super.slots.get(AnvilCustomInventory.SECOND_INDEX);
    }

    @NotNull
    @Override
    public ItemStack getResult() {
        return super.slots.get(AnvilCustomInventory.RESULT_INDEX);
    }

    @Override
    public void setFirst(@NotNull ItemStack item) {
        setItem(AnvilCustomInventory.FIRST_INDEX, item);
    }

    @Override
    public void setSecond(@NotNull ItemStack item) {
        setItem(AnvilCustomInventory.SECOND_INDEX, item);
    }

    @Override
    public void setResult(@NotNull ItemStack item) {
        setItem(AnvilCustomInventory.RESULT_INDEX, item);
    }

    @Override
    public InventoryView open(@NotNull HumanEntity human) {
        final InventoryView view = ServerInventoryUtils.openWorkstation((CraftHumanEntity) human, Blocks.ANVIL);
        if (view == null) {
            return null;
        }

        update(view);
        return view;
    }

}
