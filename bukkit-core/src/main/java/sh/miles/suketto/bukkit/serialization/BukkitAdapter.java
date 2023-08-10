package sh.miles.suketto.bukkit.serialization;

import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

public interface BukkitAdapter<I, O> {

    O serialize(@NotNull final I in);

    I deserialize(@NotNull final O out);

    @SuppressWarnings("unchecked")
    default Class<I> getInputClass() {
        return (Class<I>) new TypeToken<I>() {
        }.getRawType();
    }

    @SuppressWarnings("unchecked")
    default Class<O> getOutputClass() {
        return (Class<O>) new TypeToken<O>() {
        }.getRawType();
    }
}
