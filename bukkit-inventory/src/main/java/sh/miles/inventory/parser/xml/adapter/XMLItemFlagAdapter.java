package sh.miles.inventory.parser.xml.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

public class XMLItemFlagAdapter extends XmlAdapter<String, ItemFlag> {
    @Override
    public ItemFlag unmarshal(String s) throws IllegalArgumentException {
        return ItemFlag.valueOf(s);
    }

    @Override
    public String marshal(@NotNull ItemFlag itemFlag) {
        return itemFlag.name();
    }
}
