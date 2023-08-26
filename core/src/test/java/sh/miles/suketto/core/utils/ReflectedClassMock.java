package sh.miles.suketto.core.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Mock of Class that can be reflected
 */
public final class ReflectedClassMock {

    private static final String PRIVATE_CONSTANT = "shh";

    private String content;

    public ReflectedClassMock() {
        this.content = "empty";
    }

    /**
     * Creates new reflected class
     *
     * @param content mock content
     */
    private ReflectedClassMock(String content) {
        this.content = content;
    }

    private void addContent(@NotNull final String content) {
        this.content += content;
    }

    public String getContentPublic() {
        return this.content;
    }
}
