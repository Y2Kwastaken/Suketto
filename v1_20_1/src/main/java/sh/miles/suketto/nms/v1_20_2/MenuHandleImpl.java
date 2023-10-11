package sh.miles.suketto.nms.v1_20_2;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftContainer;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.MenuHandle;
import sh.miles.suketto.nms.v1_20_2.utils.ServerAdapter;

import java.util.function.Function;

public final class MenuHandleImpl implements MenuHandle {

    @NotNull
    @Override
    public InventoryView open(@NotNull Player player, @NotNull Inventory inventory, @NotNull BaseComponent... title) throws IllegalStateException, IllegalArgumentException {
        Preconditions.checkNotNull(inventory);
        Preconditions.checkNotNull(title);
        Preconditions.checkNotNull(player);

        ServerPlayer splayer = ServerAdapter.toServerPlayer(player);
        MenuType<?> menuType = CraftContainer.getNotchInventoryType(inventory);

        if (menuType == null) {
            throw new IllegalArgumentException("could not find menu type for inventory type of " + inventory.getType());
        }
        AbstractContainerMenu menu = new CraftContainer(inventory, splayer, splayer.nextContainerCounter());
        menu = CraftEventFactory.callInventoryOpenEvent(splayer, menu);
        if (menu == null) {
            throw new IllegalStateException("Unable to open menu for unknown reason");
        }

        splayer.connection.send(new ClientboundOpenScreenPacket(menu.containerId, menuType, ServerAdapter.toMinecraftChat(title)));
        splayer.containerMenu = menu;
        splayer.initMenu(menu);
        return splayer.containerMenu.getBukkitView();
    }

    @NotNull
    @SuppressWarnings("deprecation")
    public InventoryView openWorkstation(@NotNull Player player, @NotNull Material workstation, @NotNull Location location) throws IllegalArgumentException {
        if (!(player instanceof CraftPlayer cplayer)) {
            throw new IllegalArgumentException("Unable to cast player to CraftPlayer, only pass in CraftPlayers");
        }

        final Block block = ServerAdapter.toMinecraftBlock(workstation);
        final BlockPos position = CraftLocation.toBlockPosition(player.getLocation());
        MenuProvider provider = block.getMenuProvider(null, cplayer.getHandle().level(), position);
        if (provider == null) {
            provider = getException(workstation).apply(position);
        }

        if (provider == null) {
            throw new IllegalArgumentException("The material %s can not be opened as a workstation".formatted(workstation.name()));
        }

        cplayer.getHandle().openMenu(provider);
        cplayer.getHandle().containerMenu.checkReachable = false;
        return cplayer.getHandle().containerMenu.getBukkitView();
    }

    @Override
    public void sendTitleChange(@NotNull Inventory inventory, @NotNull BaseComponent... title) {
        for (HumanEntity viewer : inventory.getViewers()) {
            sendTitleChange(viewer.getOpenInventory(), title);
        }
    }

    @Override
    @SuppressWarnings("all")
    public void sendTitleChange(@NotNull InventoryView view, @NotNull BaseComponent... title) {
        Preconditions.checkArgument(view != null, "InventoryView cannot be null");
        Preconditions.checkArgument(title != null, "Title cannot be null");
        Preconditions.checkArgument(view.getPlayer() instanceof org.bukkit.entity.Player, "NPCs are not currently supported for this function");
        Preconditions.checkArgument(view.getTopInventory().getType().isCreatable(), "Only creatable inventories can have their title changed");

        final ServerPlayer splayer = ServerAdapter.toServerPlayer(view.getPlayer());
        final int containerId = splayer.containerMenu.containerId;
        final MenuType<?> windowType = CraftContainer.getNotchInventoryType(view.getTopInventory());
        splayer.connection.send(new ClientboundOpenScreenPacket(containerId, windowType, ServerAdapter.toMinecraftChat(title)));
        ((Player) view.getPlayer()).updateInventory();
    }

    @NotNull
    private static Function<BlockPos, MenuProvider> getException(@NotNull final Material material) {
        if (material == Material.ENCHANTING_TABLE) {
            return (pos) ->
                    new SimpleMenuProvider((i, playerinventory, player) ->
                            new EnchantmentMenu(i, playerinventory, ContainerLevelAccess.create(player.level(), pos)),
                            Component.translatable("container.enchant")
                    );
        }

        return (p) -> null;
    }
}
