package sh.miles.suketto.core.collection.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatableTreeSetTest {

    private static final Function<ComplexObject, Integer> KEY_FUNCTION = ComplexObject::value;

    protected UpdateableTreeSet<ComplexObject> set;
    private ComplexObject objectOne;

    @BeforeEach
    public void setup() {
        set = new UpdateableTreeSet<>(Comparator.comparing(KEY_FUNCTION));
        objectOne = new ComplexObject("Jim", 5);
        set.add(objectOne);
        set.add(new ComplexObject("John", 10));
    }

    @Test
    public void should_Resort_Correctly() {
        objectOne.setValue(11);
        set.update(objectOne);
        assertEquals(objectOne, set.toArray()[1]);
    }

    public static final class ComplexObject {
        private String name;
        private Integer value;

        public ComplexObject(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ComplexObject that)) return false;
            return Objects.equals(name, that.name) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, value);
        }

        @Override
        public String toString() {
            return "ComplexObject{" + "name='" + name + '\'' + ", object=" + value + '}';
        }

        public String name() {
            return name;
        }

        public Integer value() {
            return value;
        }

    }
}
