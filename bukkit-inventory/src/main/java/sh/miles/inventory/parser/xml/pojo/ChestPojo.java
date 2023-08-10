package sh.miles.inventory.parser.xml.pojo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import net.md_5.bungee.api.chat.BaseComponent;
import sh.miles.inventory.parser.xml.adapter.XMLBaseComponentAdapter;
import sh.miles.inventory.parser.xml.adapter.XMLClassAdapter;

import java.util.List;
import java.util.Objects;

@Getter
@XmlRootElement(name = "chest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChestPojo {

    @XmlAttribute(name = "title")
    @XmlJavaTypeAdapter(XMLBaseComponentAdapter.class)
    private BaseComponent title;
    @XmlAttribute(name = "rows", required = true)
    private String rows;
    @XmlAttribute(name = "columns", required = true)
    private String columns;
    @XmlAttribute(name = "responder")
    @XmlJavaTypeAdapter(XMLClassAdapter.class)
    private Class<?> responder;
    @XmlAttribute(name = "link")
    private String link;
    @XmlElementWrapper(name = "hgroups")
    @XmlElement(name = "hgroup")
    private List<HGroupPojo> hgroups;
    @XmlElementWrapper(name = "vgroups")
    @XmlElement(name = "vgroup")
    private List<VGroupPojo> vgroups;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<ItemStackPojo> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChestPojo chestPojo)) return false;
        return rows == chestPojo.rows && columns == chestPojo.columns && Objects.equals(title, chestPojo.title) && Objects.equals(responder, chestPojo.responder) && Objects.equals(link, chestPojo.link) && Objects.equals(hgroups, chestPojo.hgroups) && Objects.equals(vgroups, chestPojo.vgroups) && Objects.equals(items, chestPojo.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rows, columns, responder, link, hgroups, vgroups, items);
    }

    @Override
    public String toString() {
        return "ChestPojo{" +
                "title=" + title +
                ", rows=" + rows +
                ", columns=" + columns +
                ", responder=" + responder +
                ", link='" + link + '\'' +
                ", hgroups=" + hgroups +
                ", vgroups=" + vgroups +
                ", items=" + items +
                '}';
    }
}
