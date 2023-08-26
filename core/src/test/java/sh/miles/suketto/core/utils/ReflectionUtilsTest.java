package sh.miles.suketto.core.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.sql.Ref;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionUtilsTest {

    private ReflectedClassMock mock;

    @BeforeEach
    public void setup() {
        mock = new ReflectedClassMock();
    }

    @Test
    public void shouldCreateNewInstance0() {
        final ReflectedClassMock sample = assertDoesNotThrow(() -> ReflectionUtils.newInstance(ReflectedClassMock.class, new Object[]{"Content"}));
        assertNotNull(sample);
        assertEquals("Content", sample.getContentPublic());
    }

    @Test
    public void shouldCreateNewInstance1() {
        final ReflectedClassMock sample = assertDoesNotThrow(() -> ReflectionUtils.newInstance("sh.miles.suketto.core.utils.ReflectedClassMock", new Object[]{"Content"}));
        assertNotNull(sample);
        assertEquals("Content", sample.getContentPublic());
    }

    @Test
    public void shouldGetFieldValue() {
        final String content = assertDoesNotThrow(() -> ReflectionUtils.getField(mock, "content", String.class));
        assertNotNull(content);
        assertEquals("empty", content);
    }

    @Test
    public void shouldGetFieldStatic() {
        final String content = assertDoesNotThrow(() -> ReflectionUtils.getField(ReflectedClassMock.class, "PRIVATE_CONSTANT", String.class));
        assertNotNull(content);
        assertEquals("shh", content);
    }

    @Test
    public void shouldGetConstructorHandle() {
        final MethodHandle handle = assertDoesNotThrow(() -> {
            return ReflectionUtils.getConstructor(ReflectedClassMock.class, new Class[]{String.class});
        });
        assertNotNull(handle);
        ReflectedClassMock mock = (ReflectedClassMock) assertDoesNotThrow(() -> handle.invoke("content"));
        assertEquals("content", mock.getContentPublic());
    }

    @Test
    public void shouldGetMethodHandle() throws Throwable {
        final MethodHandle handle = assertDoesNotThrow(() -> {
            return ReflectionUtils.getMethod(ReflectedClassMock.class, "addContent", new Class[]{String.class});
        });
        handle.bindTo(this.mock).invoke("extra");
        assertEquals("emptyextra", mock.getContentPublic());
    }

    @Test
    public void shouldGetFieldAsGetter() throws Throwable {
        final MethodHandle handle = assertDoesNotThrow(() -> {
            return ReflectionUtils.getFieldAsGetter(ReflectedClassMock.class, "content");
        });
        final String content = (String) handle.bindTo(this.mock).invoke();
        assertEquals("empty", content);
    }

    @Test
    public void shouldGetFieldAsSetter() throws Throwable {
        final MethodHandle handle = assertDoesNotThrow(() -> {
            return ReflectionUtils.getFieldAsSetter(ReflectedClassMock.class, "content");
        });
        handle.bindTo(this.mock).invoke("new");
        assertEquals("new", mock.getContentPublic());
    }

}
