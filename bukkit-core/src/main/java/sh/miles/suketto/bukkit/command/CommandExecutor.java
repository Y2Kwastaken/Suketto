package sh.miles.suketto.bukkit.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Executor equivalent to {@link  org.bukkit.command.CommandExecutor}
 */
@FunctionalInterface
public interface CommandExecutor {

    /**
     * Called when a command is executed
     *
     * @param sender the sender of the command
     * @param args   the arguments sent with the command
     * @return true if the command was a success otherwise false
     */
    boolean execute(@NotNull final CommandSender sender, @NotNull final String[] args);

}
