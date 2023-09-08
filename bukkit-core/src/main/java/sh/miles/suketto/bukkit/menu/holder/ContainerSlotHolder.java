package sh.miles.suketto.bukkit.menu.holder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.SukettoContainer;

/**
 * The slot holder implementation for a Container
 */
public class ContainerSlotHolder implements SlotHolder<SukettoContainer> {

    private final SukettoContainer container;

    ContainerSlotHolder(SukettoContainer container) {
        this.container = container;
    }

    @Override
    public void setItem(int index, @NotNull ItemStack item) {
        container.setItem(index, item);
    }

    @Override
    public SukettoContainer getHolder() {
        return container;
    }

    @Override
    public int getMinIndex() {
        return container.getMinSlot();
    }

    @Override
    public int getMaxIndex() {
        return container.getMaxSlot();
    }

    @Override
    public boolean hasItemAt(int index) {
        return container.getItem(index).getType() != Material.AIR;
    }
}
