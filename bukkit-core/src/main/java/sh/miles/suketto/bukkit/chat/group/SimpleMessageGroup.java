package sh.miles.suketto.bukkit.chat.group;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Simple implementation of a message group for a set of players
 */
public class SimpleMessageGroup implements MessageGroup<Player> {

    private final List<UUID> uuids;

    /**
     * Creates a simple message group
     *
     * @param uuids the uuids of the group
     */
    public SimpleMessageGroup(final List<UUID> uuids) {
        this.uuids = uuids;
    }

    @Override
    public void computeOn(Consumer<Player> action) {
        uuids.forEach((uuid) -> {
            action.accept(Bukkit.getPlayer(uuid));
        });
    }
}
