package sh.miles.suketto.bukkit.chat.translation;

import com.google.common.base.Preconditions;
import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.chat.Component;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacement;
import sh.miles.suketto.bukkit.chat.translation.replacement.Replacer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A stack of ordered components
 */
public class SukettoComponentStack {

    private final List<String> raws;
    private final Set<Replacer> replacers;
    private List<BaseComponent> cached;

    public SukettoComponentStack(@NotNull final List<String> raws, @NotNull final Replacer[] replacers) {
        Preconditions.checkNotNull(raws);
        Preconditions.checkNotNull(replacers);
        validateReplacers(raws, replacers);

        this.raws = raws;
        this.replacers = Set.of(replacers);
    }

    /**
     * gets the base component from replacements
     *
     * @param replacements the replacements
     * @return the base components
     */
    public List<BaseComponent> get(Replacement... replacements) {
        if (replacements.length > replacers.size()) {
            Bukkit.getLogger().info("A length of replacements is greater than the possible replacers in the string \"%s\"".formatted(raws));
        }

        List<String> changed = new ArrayList<>();
        for (String raw : raws) {
            String str = raw;
            for (Replacement replacement : replacements) {
                if (!replacers.contains(replacement)) {
                    throw new IllegalArgumentException("The provided replacement could not be found within this component");
                }

                str = replacement.apply(str);
            }
            changed.add(str);
        }

        final List<BaseComponent> components = new ArrayList<>();
        for (String s : changed) {
            components.add(Component.unarray(MineDown.parse(s)));
        }

        return components;
    }


    /**
     * gets translation
     *
     * @return BaseComponent
     */
    public List<BaseComponent> get() {
        if (!replacers.isEmpty()) {
            throw new IllegalArgumentException("The translate method cannot be invoked successfully unless all replacements are fulfilled");
        }

        if (this.cached == null) {
            final List<BaseComponent> components = new ArrayList<>();
            for (String raw : raws) {
                components.add(Component.unarray(MineDown.parse(raw)));
            }
            this.cached = components;
        }

        return this.cached;
    }


    public static void validateReplacers(List<String> raws, Replacer[] replacers) throws IllegalArgumentException {
        boolean contained;
        for (final Replacer replacer : replacers) {
            contained = false;
            for (String raw : raws) {
                if (!contained) {
                    contained = replacer.isContainedWithin(raw);
                }
            }

            if (!contained) {
                throw new IllegalArgumentException("the provided replacer %s could not be validated as it is not contained in the raw String List \"%s\"".formatted(replacer.key(), raws));
            }
        }
    }
}
