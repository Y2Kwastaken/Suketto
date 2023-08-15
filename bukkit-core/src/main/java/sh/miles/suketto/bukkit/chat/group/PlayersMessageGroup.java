package sh.miles.suketto.bukkit.chat.group;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class PlayersMessageGroup implements MessageGroup<Player> {

    @Override
    public void computeOn(Consumer<Player> action) {
        Bukkit.getOnlinePlayers().iterator().forEachRemaining(action);
    }

}
