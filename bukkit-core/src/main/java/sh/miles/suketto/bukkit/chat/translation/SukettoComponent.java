package sh.miles.suketto.bukkit.chat.translation;

import com.google.common.base.Preconditions;
import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.chat.Component;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacement;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacer;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A component that can be formed given a translation key and a set of replacers
 */
public class SukettoComponent {

    private final String raw;
    private final Set<Replacer> replacers;
    private BaseComponent component;

    SukettoComponent(@NotNull final String raw, @NotNull final Replacer[] replacers) {
        Preconditions.checkNotNull(raw);
        Preconditions.checkNotNull(replacers);
        validateReplacers(raw, replacers);

        this.raw = raw;
        this.replacers = Stream.of(replacers).collect(Collectors.toSet());
    }

    /**
     * gets the base component from replacements
     *
     * @param replacements the replacements
     * @return the base component`
     */
    public BaseComponent get(Replacement... replacements) {
        if (replacements.length > replacers.size()) {
            Bukkit.getLogger().info("A length of replacements is greater than the possible replacers in the string \"%s\"".formatted(raw));
        }

        String str = raw;
        for (Replacement replacement : replacements) {
            if (!replacers.contains(replacement)) {
                System.out.println(replacers);
                System.out.println(replacement);
                throw new IllegalArgumentException("The provided replacement could not be found within this component");
            }

            str = replacement.apply(str);
        }

        return Component.unarray(MineDown.parse(str));
    }

    /**
     * gets translatoin
     * @return BaseComponent
     */
    public BaseComponent get() {
        if (!replacers.isEmpty()) {
            throw new IllegalArgumentException("The translate method cannot be invoked successfully unless all replacements are fulfilled");
        }

        if (component == null) {
            component = Component.unarray(MineDown.parse(raw));
        }
        return component;
    }

    private static void validateReplacers(String raw, Replacer[] replacers) throws IllegalArgumentException {
        for (Replacer replacer : replacers) {
            if (!replacer.isContainedWithin(raw)) {
                throw new IllegalArgumentException("the provided replacer %s could not be validated as it is not conatined in the raw String \"%s\"".formatted(replacer.key(), raw));
            }
        }
    }

}
