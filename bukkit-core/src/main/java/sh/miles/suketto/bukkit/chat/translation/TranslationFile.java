package sh.miles.suketto.bukkit.chat.translation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.chat.translation.replacing.Replacer;
import sh.miles.suketto.core.collection.abstracts.Tuple;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A File that contains translations
 */
public final class TranslationFile {

    private final Map<String, TranslationComponent> translations;

    private TranslationFile(final Map<String, String[]> translations) {
        this.translations = new HashMap<>();
        init(translations);
    }

    private void init(final Map<String, String[]> translations) {
        translations.forEach((key, value) -> {
            this.translations.put(key, new TranslationComponent(value, Replacer.inStrings(value)));
        });
    }

    @Nullable
    public TranslationComponent getTranslation(@NotNull final String path) {
        return this.translations.get(path);
    }

    public void setTranslation(@NotNull final String path, @NotNull final TranslationComponent translation) {
        this.translations.put(path, translation);
    }

    public List<Tuple<String, String[]>> asList() {
        return this.translations.entrySet().stream().map(entry -> Tuple.of(entry.getKey(), entry.getValue().getRaw())).toList();
    }

    /**
     * Reads from a translation file.
     *
     * @param filePath the file at the path to read into a translation
     * @return a TranslationFile
     * @throws IOException if an IOException occurs
     */
    public static TranslationFile read(@NotNull final Path filePath) throws IOException {
        final Map<String, String[]> translationMap = new HashMap<>();
        try (final BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.startsWith("//")) {
                    continue;
                }
                String[] keyValueSplit = line.split("=", 2);
                final String key = keyValueSplit[0];
                translationMap.put(key, getTranslationFromString(keyValueSplit[1]));
                int index;
            }
        }
        return new TranslationFile(translationMap);
    }

    /**
     * Writes a translation file to the provided path
     *
     * @param tFile    the translation file to write
     * @param filePath the path to write the file to
     * @throws IOException if an IOException occurs
     */
    public static void write(@NotNull final TranslationFile tFile, @NotNull final Path filePath) throws IOException {
        try (final BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Tuple<String, String[]> tuple : tFile.asList()) {
                writer.write(getStringFromTranslation(tuple.getFirst(), tuple.getSecond()));
            }
        }
    }

    private static String[] getTranslationFromString(@NotNull final String key) {
        final List<String> translation = new ArrayList<>(1);
        final StringBuilder translationRaw = new StringBuilder(key);
        int openIndex;
        int closeIndex;
        while ((openIndex = translationRaw.indexOf("<")) != -1 && (closeIndex = translationRaw.indexOf(">")) != -1) {
            translation.add(translationRaw.substring(openIndex + 1, closeIndex));
            translationRaw.replace(openIndex, closeIndex + 1, "");
        }

        if (translation.isEmpty()) {
            translation.add(translationRaw.toString());
        }

        return translation.toArray(String[]::new);
    }

    private static String getStringFromTranslation(@NotNull final String key, @NotNull final String[] translation) {
        final StringBuilder builder = new StringBuilder();
        builder.append(key).append('=');
        if (translation.length == 1) {
            return builder.append(translation[0]).toString();
        }

        for (String s : translation) {
            builder.append('<').append(s).append('>');
        }
        return builder.toString();
    }
}
