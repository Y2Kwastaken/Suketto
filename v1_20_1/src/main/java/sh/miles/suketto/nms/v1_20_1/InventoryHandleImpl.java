package sh.miles.suketto.nms.v1_20_1;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftContainer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.nms.InventoryHandle;
import sh.miles.suketto.nms.inventory.SukettoCustomInventory;
import sh.miles.suketto.nms.inventory.SukettoCustomInventoryType;
import sh.miles.suketto.nms.v1_20_1.inventory.AnvilCustomInventoryImpl;
import sh.miles.suketto.nms.v1_20_1.utils.ServerAdapter;

public class InventoryHandleImpl implements InventoryHandle {

    @Nullable
    @Override
    public InventoryView open(@NotNull Inventory inventory, @NotNull BaseComponent title, @NotNull Player player) {
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
            return null;
        }

        splayer.connection.send(new ClientboundOpenScreenPacket(menu.containerId, menuType, ServerAdapter.toMinecraftChat(title)));
        splayer.containerMenu = menu;
        splayer.initMenu(menu);
        return splayer.containerMenu.getBukkitView();
    }

    @Override
    public SukettoCustomInventory create(@NotNull BaseComponent title, @NotNull SukettoCustomInventoryType inventoryType) {
        Preconditions.checkNotNull(title);
        Preconditions.checkNotNull(inventoryType);

        switch (inventoryType) {
            case ANVIL -> {
                return new AnvilCustomInventoryImpl(title);
            }
            default -> {
                throw new IllegalArgumentException("somehow no inventory was created");
            }
        }
    }

    @Override
    public void sendTitleChange(@NotNull Inventory inventory, @NotNull BaseComponent title) {
        for (HumanEntity viewer : inventory.getViewers()) {
            sendTitleChange0(viewer.getOpenInventory(), title);
        }
    }

    @Override
    public void sendTitleChange(@NotNull InventoryView view, @NotNull BaseComponent title) {
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(title);
        sendTitleChange0(view, title);
    }

    private static void sendTitleChange0(InventoryView view, BaseComponent title) {
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
}
