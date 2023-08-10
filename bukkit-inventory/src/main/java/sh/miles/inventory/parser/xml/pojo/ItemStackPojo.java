package sh.miles.inventory.parser.pojo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import sh.miles.inventory.parser.adapter.xml.XMLBaseComponentAdapter;
import sh.miles.inventory.parser.adapter.xml.XMLEnchantmentAdapter;
import sh.miles.inventory.parser.adapter.xml.XMLItemFlagAdapter;
import sh.miles.inventory.parser.adapter.xml.XMLMaterialAdapter;

import java.util.List;
import java.util.Objects;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemStackPojo {

    @XmlAttribute(name = "link")
    private String link;
    @XmlAttribute(name = "click")
    private String click;
    @XmlAttribute(name = "item-type")
    @XmlJavaTypeAdapter(XMLMaterialAdapter.class)
    private Material itemType;
    @XmlElement(name = "name")
    @XmlJavaTypeAdapter(XMLBaseComponentAdapter.class)
    private BaseComponent name;
    @XmlElementWrapper(name = "lore")
    @XmlElement(name = "line")
    @XmlJavaTypeAdapter(XMLBaseComponentAdapter.class)
    private List<BaseComponent> lore;
    @XmlElementWrapper(name = "item-flags")
    @XmlElement(name = "flag")
    @XmlJavaTypeAdapter(XMLItemFlagAdapter.class)
    private List<ItemFlag> flags;
    @XmlElementWrapper(name = "enchantments")
    @XmlElement(name = "enchantment")
    private List<EnchantmentPojo> enchantments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemStackPojo that)) return false;
        return Objects.equals(link, that.link) && Objects.equals(click, that.click) && itemType == that.itemType && Objects.equals(name, that.name) && Objects.equals(lore, that.lore) && Objects.equals(flags, that.flags) && Objects.equals(enchantments, that.enchantments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, click, itemType, name, lore, flags, enchantments);
    }

    @Override
    public String toString() {
        return "ItemStackPojo{" +
                "link='" + link + '\'' +
                ", click='" + click + '\'' +
                ", itemType=" + itemType +
                ", name=" + name +
                ", lore=" + lore +
                ", flags=" + flags +
                ", enchantments=" + enchantments +
                '}';
    }
}
