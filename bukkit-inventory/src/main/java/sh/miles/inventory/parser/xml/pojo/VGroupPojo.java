package sh.miles.inventory.parser.pojo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class VGroupPojo {

    @XmlAttribute(name = "column")
    private int column;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<ItemStackPojo> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VGroupPojo that)) return false;
        return column == that.column && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, items);
    }

    @Override
    public String toString() {
        return "VGroupPojo{" +
                "column=" + column +
                ", items=" + items +
                '}';
    }
}
