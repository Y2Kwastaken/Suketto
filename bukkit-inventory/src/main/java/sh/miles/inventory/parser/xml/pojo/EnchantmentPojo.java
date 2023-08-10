package sh.miles.inventory.parser.xml.pojo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.bukkit.enchantments.Enchantment;
import sh.miles.inventory.parser.xml.adapter.XMLEnchantmentAdapter;

import java.util.Objects;

public class EnchantmentPojo {

    @XmlElement(name = "key")
    @XmlJavaTypeAdapter(XMLEnchantmentAdapter.class)
    private Enchantment enchantment;
    @XmlElement(name = "level")
    private int level;
    @XmlElement(name = "ignore-level-cap")
    private boolean ignoreLevelCap;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnchantmentPojo that)) return false;
        return level == that.level && ignoreLevelCap == that.ignoreLevelCap && Objects.equals(enchantment, that.enchantment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enchantment, level, ignoreLevelCap);
    }

    @Override
    public String toString() {
        return "EnchantmentPojo{" +
                "enchantment=" + enchantment +
                ", level=" + level +
                ", ignoreLevelCap=" + ignoreLevelCap +
                '}';
    }
}
