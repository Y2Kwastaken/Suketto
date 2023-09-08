package sh.miles.suketto.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * Utilities for PlayerProfiles
 */
public final class PlayerProfileUtils {

    public static final String URL_START = "https://textures.minecraft.net/texture/%s";

    private PlayerProfileUtils() {
    }

    /**
     * Creates a new player profile with a skin at the provided URL
     *
     * @param url the url of the skin
     * @return the new PlayerProfile
     * @throws MalformedURLException if the url is malformed
     */
    public static PlayerProfile newSkin(String url) throws MalformedURLException {
        final PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        final PlayerTextures textures = profile.getTextures();
        textures.setSkin(new URL(url));
        profile.setTextures(textures);
        return profile;
    }

}
