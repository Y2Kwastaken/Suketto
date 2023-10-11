package sh.miles.suketto.nms.v1_20_2;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import org.bukkit.craftbukkit.v1_20_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R2.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.nms.ItemHandle;
import sh.miles.suketto.nms.v1_20_2.utils.ServerAdapter;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ItemHandleImpl implements ItemHandle {

    private static final MethodHandle handleHandle;

    static {
        try {
            final Field handleField = CraftItemStack.class.getDeclaredField("handle");
            handleField.setAccessible(true);
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            handleHandle = lookup.unreflectGetter(handleField);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemStack setName(@NotNull ItemStack item, @NotNull BaseComponent... name) {
        final CraftItemStack craftItem = ensureCraftStack(item);
        final net.minecraft.world.item.ItemStack nmsItem = getHandle(craftItem);
        nmsItem.setHoverName(ServerAdapter.toMinecraftChat(name));
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Nullable
    @Override
    public BaseComponent getName(@NotNull ItemStack item) {
        final CraftItemStack craftItemStack = ensureCraftStack(item);
        final net.minecraft.world.item.ItemStack nmsItem = getHandle(craftItemStack);
        final Component name = nmsItem.getHoverName();
        return name == null ? null : ServerAdapter.toBungeeChat(name);
    }

    @Override
    public ItemStack setLore(@NotNull ItemStack item, @NotNull List<BaseComponent> lore) {
        final CraftItemStack craftItem = ensureCraftStack(item);
        final net.minecraft.world.item.ItemStack nmsItem = getHandle(craftItem);

        final CompoundTag tag = nmsItem.getTag() == null ? new CompoundTag() : nmsItem.getTag();
        if (!tag.contains(net.minecraft.world.item.ItemStack.TAG_DISPLAY)) {
            tag.put(net.minecraft.world.item.ItemStack.TAG_DISPLAY, new CompoundTag());
        }

        final CompoundTag displayTag = tag.getCompound(net.minecraft.world.item.ItemStack.TAG_DISPLAY);
        ListTag loreTag = new ListTag();
        for (int i = 0; i < lore.size(); i++) {
            loreTag.add(i, StringTag.valueOf(ServerAdapter.toJsonString(lore.get(i))));
        }

        displayTag.put(net.minecraft.world.item.ItemStack.TAG_LORE, loreTag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Override
    public ItemStack setLoreArray(@NotNull ItemStack item, @NotNull List<BaseComponent[]> lore) {
        final CraftItemStack craftItem = ensureCraftStack(item);
        final net.minecraft.world.item.ItemStack nmsItem = getHandle(craftItem);

        final CompoundTag tag = nmsItem.getTag() == null ? new CompoundTag() : nmsItem.getTag();
        if (!tag.contains(net.minecraft.world.item.ItemStack.TAG_DISPLAY)) {
            tag.put(net.minecraft.world.item.ItemStack.TAG_DISPLAY, new CompoundTag());
        }

        final CompoundTag displayTag = tag.getCompound(net.minecraft.world.item.ItemStack.TAG_DISPLAY);
        ListTag loreTag = new ListTag();
        for (int i = 0; i < lore.size(); i++) {
            loreTag.add(i, StringTag.valueOf(ServerAdapter.toJsonString(lore.get(i))));
        }

        displayTag.put(net.minecraft.world.item.ItemStack.TAG_LORE, loreTag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @NotNull
    @Override
    public List<BaseComponent> getLore(@NotNull ItemStack item) {
        final CraftItemStack craftItem = ensureCraftStack(item);
        final net.minecraft.world.item.ItemStack nmsItem = getHandle(craftItem);

        final CompoundTag tag = nmsItem.getTag();
        if (tag == null) {
            return List.of();
        }

        final CompoundTag displayTag = tag.getCompound(net.minecraft.world.item.ItemStack.TAG_DISPLAY);
        if (displayTag == null) {
            return List.of();
        }

        final ListTag loreTag = displayTag.getList(net.minecraft.world.item.ItemStack.TAG_LORE, CraftMagicNumbers.NBT.TAG_LIST);
        if (loreTag == null) {
            return List.of();
        }

        final List<BaseComponent> lore = new ArrayList<>();
        for (int i = 0; i < loreTag.size(); i++) {
            lore.add(ServerAdapter.toBungeeChat(loreTag.getString(i)));
        }

        return lore;
    }

    private static CraftItemStack ensureCraftStack(@NotNull ItemStack item) {
        if (!(item instanceof CraftItemStack)) {
            item = CraftItemStack.asCraftCopy(item);
        }

        return (CraftItemStack) item;
    }

    private static net.minecraft.world.item.ItemStack getHandle(CraftItemStack itemStack) {
        try {
            return (net.minecraft.world.item.ItemStack) handleHandle.invokeExact(itemStack);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
