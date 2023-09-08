package sh.miles.suketto.nms.v1_20_1.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

/**
 * Nms Chat utils for 1.20.1 intended for internal use
 */
public final class ServerAdapter {

    private static final Gson gson;

    static {
        final Field gsonField;
        try {
            gsonField = ComponentSerializer.class.getDeclaredField("gson");
            gsonField.setAccessible(true);

            final MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle gsonHandle = lookup.unreflectGetter(gsonField);
            gson = (Gson) gsonHandle.invokeExact();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Translates the base component into a minecraft component
     *
     * @param component the component to translate
     * @return the minecraft component
     */
    public static Component toMinecraftChat(@NotNull final BaseComponent component) {
        return CraftChatMessage.fromJSON(ComponentSerializer.toString(component));
    }

    /**
     * Translates the given base component into a json string
     *
     * @param component the component to translate
     * @return the string
     */
    public static String toJsonString(@NotNull final BaseComponent component) {
        return ComponentSerializer.toString(component);
    }

    /**
     * Translates the NMS component into a minecraft component
     *
     * @param component the component to translate
     * @return the bungee component
     */
    public static BaseComponent toBungeeChat(@NotNull final Component component) {
        return deserializeBaseComponent(CraftChatMessage.toJSON(component));
    }

    /**
     * Translates the json string into a bungee component
     *
     * @param json the json to translate
     * @return th bungee component
     */
    public static BaseComponent toBungeeChat(@NotNull final String json) {
        return deserializeBaseComponent(json);
    }

    /**
     * Gets the ServerPlayer from the provided bukkit player
     *
     * @param player the player to get the ServerPlayer of
     * @return the server player
     */
    @NotNull
    public static ServerPlayer toServerPlayer(@NotNull final Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    @NotNull
    public static net.minecraft.world.entity.player.Player toMinecraftPlayer(@NotNull final HumanEntity human) {
        return ((CraftHumanEntity) human).getHandle();
    }

    /**
     * Gets the ServerPlayer from the provided HumanEntity assuming it is a player
     *
     * @param humanEntity the human entity
     * @return the ServerPlayer
     * @throws IllegalArgumentException thrown given if the assumption that the humanEntity is a player is false
     */
    @NotNull
    public static ServerPlayer toServerPlayer(@NotNull final HumanEntity humanEntity) throws IllegalArgumentException {
        return ((CraftPlayer) humanEntity).getHandle();
    }

    /**
     * Gets the Craftbukkit ItemStack from an NMS ItemStack
     *
     * @param nmsItem the nms item stack to translate
     * @return the craft item equivalent
     */
    @NotNull
    public static CraftItemStack toItemStack(ItemStack nmsItem) {
        return CraftItemStack.asCraftMirror(nmsItem);
    }

    /**
     * Gets the NMS ItemStack from a provided Craftbukkit ItemStack
     *
     * @param craftItem the craft item stack to translate
     * @return the nms item equivalent
     */
    @NotNull
    public static ItemStack toItemStack(CraftItemStack craftItem) {
        return CraftItemStack.asNMSCopy(craftItem);
    }

    /**
     * Gets an NMS ItemStack from a provided Bukkit ItemStack
     *
     * @param bukkitItem the bukkit item to translate
     * @return the nms item equivalent
     */
    @NotNull
    public static ItemStack toItemStack(org.bukkit.inventory.ItemStack bukkitItem) {
        return CraftItemStack.asNMSCopy(bukkitItem);
    }

    /**
     * Deserializes a base component into a single component instead of a legacy array
     *
     * @param json the json to turn into a BaseComponent
     * @return the BaseComponent
     */
    private static BaseComponent deserializeBaseComponent(final String json) {
        return gson.fromJson(JsonParser.parseString(json), BaseComponent.class);
    }

}
