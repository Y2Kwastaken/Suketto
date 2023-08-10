package sh.miles.inventory.exceptions;

import org.bukkit.Material;

public class IllegalItemTypeException extends IllegalArgumentException {

    public IllegalItemTypeException(final Material material) {
        super("The provided provided type was not an item " + material);
    }

}
