package sh.miles.inventory.exceptions;

public class InvalidEnchantmentException extends IllegalArgumentException {

    public InvalidEnchantmentException(String stringyEnchantment) {
        super("The provided enchantment %s is not a valid enchantment".formatted(stringyEnchantment));
    }

}
