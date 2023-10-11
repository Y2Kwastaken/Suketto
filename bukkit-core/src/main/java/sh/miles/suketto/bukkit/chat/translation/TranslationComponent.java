package sh.miles.suketto.bukkit.chat.translation;

import com.google.common.base.Preconditions;
import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.chat.translation.replacing.Replacement;
import sh.miles.suketto.bukkit.chat.translation.replacing.Replacer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TranslationComponent {

    private final String[] raw;
    private final Replacer[] replacers;
    private List<BaseComponent[]> cache = new ArrayList<>();

    TranslationComponent(String[] raw, @NotNull final Replacer... replacers) {
        Preconditions.checkNotNull(raw);
        Preconditions.checkNotNull(replacers);
        validateReplacers(raw, replacers);

        this.raw = raw;
        this.replacers = replacers;
    }

    /**
     * gets the base component from replacements
     *
     * @param replacements the replacements
     * @return the base component`
     */
    public BaseComponent[] get(Replacement... replacements) {
        if (replacements.length > replacers.length) {
            Bukkit.getLogger().info("A length of replacements is greater than the possible replacers in the string \"%s\"".formatted(Arrays.toString(raw)));
        }

        String[] str = raw;
        for (Replacement replacement : replacements) {
            str = replacement.apply(str);
        }

        return join(str).clone();
    }

    /**
     * gets translation
     *
     * @return BaseComponent
     */
    public BaseComponent[] get() {
        if (replacers.length > 0) {
            throw new IllegalArgumentException("The translate method cannot be invoked successfully unless all replacements are fulfilled");
        }

        if (cache == null) {
            cache.add(join(raw));
        }

        return cache.get(0).clone();
    }

    public List<BaseComponent[]> getList() {
        if (replacers.length > 0) {
            throw new IllegalArgumentException("The translate method cannot be invoked successfully unless all replacements are fulfilled");
        }

        if (cache == null) {
            cache = joinList(raw);
        }

        return new ArrayList<>(cache);
    }

    public List<BaseComponent[]> getList(Replacement... replacements) {
        if (replacements.length > replacers.length) {
            Bukkit.getLogger().info("A length of replacements is greater than the possible replacers in the string \"%s\"".formatted(Arrays.toString(raw)));
        }

        String[] str = raw;
        for (Replacement replacement : replacements) {
            str = replacement.apply(str);
        }

        return joinList(str);
    }

    public String[] getRaw() {
        return raw;
    }

    private static BaseComponent[] join(@NotNull final String[] str) {
        final ComponentBuilder builder = new ComponentBuilder();
        for (String s : str) {
            builder.append(MineDown.parse(s));
        }
        return builder.create();
    }

    private static List<BaseComponent[]> joinList(@NotNull final String[] str) {
        final List<BaseComponent[]> components = new ArrayList<>();
        for (String s : str) {
            components.add(MineDown.parse(s));
        }
        return components;
    }

    private static void validateReplacers(String[] raw, Replacer[] replacers) throws IllegalArgumentException {
        for (Replacer replacer : replacers) {
            boolean contained = false;
            for (String string : raw) {
                if (replacer.isContainedWithin(string)) {
                    contained = true;
                    break;
                }
            }

            if (!contained) {
                throw new IllegalArgumentException("THe replacer is not contained");
            }
        }
    }
}
