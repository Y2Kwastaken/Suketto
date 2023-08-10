package sh.miles.inventory.parser.xml.adapter;

import de.themoep.minedown.MineDown;
import de.themoep.minedown.MineDownStringifier;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import net.md_5.bungee.api.chat.BaseComponent;
import sh.miles.suketto.bukkit.chat.Component;

public class XMLBaseComponentAdapter extends XmlAdapter<String, BaseComponent> {

    @Override
    public BaseComponent unmarshal(String string) {
        return Component.unarray(MineDown.parse(string));
    }

    @Override
    public String marshal(BaseComponent baseComponent) {
        return new MineDownStringifier().stringify(baseComponent);
    }

}
