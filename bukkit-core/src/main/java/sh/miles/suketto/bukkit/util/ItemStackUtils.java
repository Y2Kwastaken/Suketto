package sh.miles.suketto.bukkit.util;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;

/**
 * Utility classes for some ItemStack things
 */
public final class ItemStackUtils {

    private ItemStackUtils() {
    }

    /**
     * Creates a custom head using bukkit's PlayerProfile API. You can get URL endings at <a
     * href="https://minecraft-heads.com"></a>
     *
     * @param urlEnding the url ending
     * @return the ItemStack with the texture applied
     */
    public static ItemStack customHead(@NotNull final String urlEnding) {
        final ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        final SkullMeta meta = (SkullMeta) stack.getItemMeta();
        try {
            meta.setOwnerProfile(PlayerProfileUtils.newSkin(PlayerProfileUtils.URL_START.formatted(urlEnding)));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        stack.setItemMeta(meta);
        return stack;
    }

    /**
     * Creates a custom head using an OfflinePlayer.
     *
     * @param player the player to use
     * @return the custom head
     */
    public static ItemStack customHead(@NotNull final OfflinePlayer player) {
        final ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        final SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwnerProfile(player.getPlayerProfile());
        stack.setItemMeta(meta);
        return stack;
    }
}
