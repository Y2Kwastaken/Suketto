package sh.miles.inventory.parser;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface Parser<T> {

    T parse(@NotNull final File file) throws Exception;

}
