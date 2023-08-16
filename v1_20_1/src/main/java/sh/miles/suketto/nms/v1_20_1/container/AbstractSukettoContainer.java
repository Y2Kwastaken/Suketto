package sh.miles.suketto.nms.v1_20_1.container;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.SukettoContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class AbstractSukettoContainer implements SukettoContainer {

    protected final Map<Integer, ItemStack> slots;
    protected final int minSlot;
    protected final int maxSlot;
    private final BaseComponent title;

    protected AbstractSukettoContainer(final BaseComponent title, final int minSlot, final int maxSlot) {
        this.slots = new HashMap<>();
        this.title = title;
        this.minSlot = minSlot;
        this.maxSlot = maxSlot;
    }

    @Override
    public void setItem(int index, @NotNull ItemStack item) {
        Preconditions.checkArgument(!invalidSlots(index));
        Preconditions.checkNotNull(item);
        this.slots.put(index, item);
    }

    @NotNull
    @Override
    public ItemStack getItem(int index) {
        return supplyOrAir(this.slots.get(index));
    }

    @NotNull
    @Override
    public ItemStack removeItem(int index) {
        return supplyOrAir(this.slots.remove(index));
    }

    @Override
    public void clear() {
        IntStream.range(minSlot, maxSlot).forEach((slot) -> new ItemStack(Material.AIR));
    }

    @Override
    public void update(@NotNull InventoryView view) {
        final Inventory topInventory = view.getTopInventory();
        slots.forEach(topInventory::setItem);
    }

    @Override
    public BaseComponent getTitle() {
        return this.title;
    }

    @Override
    public int getMaxSlot() {
        return maxSlot;
    }

    @Override
    public int getMinSlot() {
        return minSlot;
    }

    protected boolean invalidSlots(int index) {
        return index > maxSlot || index < minSlot;
    }

    protected ItemStack supplyOrAir(final ItemStack result) {
        return result == null ? new ItemStack(Material.AIR) : result;
    }
}
