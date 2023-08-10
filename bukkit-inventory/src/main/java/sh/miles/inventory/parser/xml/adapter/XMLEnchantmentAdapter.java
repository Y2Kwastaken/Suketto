package sh.miles.inventory.parser.xml.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import sh.miles.inventory.exceptions.InvalidEnchantmentException;

public class XMLEnchantmentAdapter extends XmlAdapter<String, Enchantment> {
    @Override
    public Enchantment unmarshal(String string) throws InvalidEnchantmentException {
        Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(string));
        if (enchantment == null) {
            throw new InvalidEnchantmentException(string);
        }
        return enchantment;
    }

    @Override
    public String marshal(Enchantment enchantment) {
        return enchantment.getKey().getKey();
    }
}
