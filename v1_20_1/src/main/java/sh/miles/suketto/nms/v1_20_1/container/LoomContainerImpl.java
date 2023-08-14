package sh.miles.suketto.nms.v1_20_1.container;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.LoomContainer;

public class LoomContainerImpl extends AbstractSukettoContainer implements LoomContainer {


    public LoomContainerImpl(BaseComponent title) {
        super(title, LoomContainer.BANNER_SLOT, LoomContainer.RESULT_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getBanner() {
        return super.getItem(LoomContainer.BANNER_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getDye() {
        return super.getItem(LoomContainer.DYE_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getPattern() {
        return super.getItem(LoomContainer.PATTERN_SLOT);
    }

    @NotNull
    @Override
    public ItemStack getResult() {
        return super.getItem(LoomContainer.RESULT_SLOT);
    }

    @Override
    public void setBanner(@NotNull ItemStack item) {
        super.setItem(LoomContainer.BANNER_SLOT, item);
    }

    @Override
    public void setDye(@NotNull ItemStack item) {
        super.setItem(LoomContainer.DYE_SLOT, item);
    }

    @Override
    public void setPattern(@NotNull ItemStack item) {
        super.setItem(LoomContainer.PATTERN_SLOT, item);
    }

    @Override
    public void setResult(@NotNull ItemStack item) {
        super.setItem(LoomContainer.RESULT_SLOT, item);
    }

    @Override
    public InventoryView open(@NotNull HumanEntity human) {
        return null;
    }
}
