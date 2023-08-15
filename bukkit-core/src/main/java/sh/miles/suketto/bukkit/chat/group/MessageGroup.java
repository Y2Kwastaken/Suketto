package sh.miles.suketto.bukkit.chat.group;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.chat.translation.SukettoComponent;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacement;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * An interface that defined a group of players that can be read messages
 */
public interface MessageGroup<T extends CommandSender> {

    MessageGroup<CommandSender> GLOBAL_GROUP = (action) -> {
        Bukkit.getOnlinePlayers().iterator().forEachRemaining(action);
        action.accept(Bukkit.getServer().getConsoleSender());
    };
    MessageGroup<Player> PLAYER_GROUP = (action) -> Bukkit.getOnlinePlayers().iterator().forEachRemaining(action);

    /**
     * Sends the component to the given reader
     *
     * @param component the component to send
     */
    default void sendMessage(@NotNull final BaseComponent component) {
        computeOn((s) -> s.spigot().sendMessage(component));
    }

    /**
     * Sends the component to the given reader
     *
     * @param component    the component to send
     * @param replacements the replacements if any
     */
    default void sendMessage(@NotNull final SukettoComponent component, @NotNull final Replacement... replacements) {
        sendMessage(component.get(replacements));
    }

    /**
     * Sends the component from the given sender to the current group
     *
     * @param sender    who sent the message
     * @param component the component that was sent
     */
    default void sendMessage(@NotNull final UUID sender, @NotNull final BaseComponent component) {
        computeOn((s) -> s.spigot().sendMessage(sender, component));
    }

    /**
     * Sends teh component from the given sender to the current group
     *
     * @param sender       the sender who sent the message
     * @param component    the component that was sent
     * @param replacements the replacements if any
     */
    default void sendMessage(@NotNull final UUID sender, @NotNull final SukettoComponent component, @NotNull final Replacement... replacements) {
        sendMessage(sender, component.get(replacements));
    }

    /**
     * Computes the given action upon a group of users
     *
     * @param action the action to compute on each user in the group
     */
    void computeOn(Consumer<T> action);
}
