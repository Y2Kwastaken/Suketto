package sh.miles.inventory.exceptions;

public class InvalidMaterialException extends IllegalArgumentException {

    public InvalidMaterialException(String stringyMaterial) {
        super("%s is an invalid material".formatted(stringyMaterial));
    }

}
