package sh.miles.inventory.parser.xml.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.bukkit.Material;
import sh.miles.inventory.exceptions.IllegalItemTypeException;
import sh.miles.inventory.exceptions.InvalidMaterialException;

public class XMLMaterialAdapter extends XmlAdapter<String, Material> {

    @Override
    public Material unmarshal(String string) throws InvalidMaterialException, IllegalItemTypeException {
        final Material material = Material.matchMaterial(string);
        if (material == null) {
            throw new InvalidMaterialException(string);
        }

        if (!material.isItem()) {
            throw new IllegalItemTypeException(material);
        }

        return material;
    }

    @Override
    public String marshal(Material material) throws Exception {
        return material.name();
    }
}
