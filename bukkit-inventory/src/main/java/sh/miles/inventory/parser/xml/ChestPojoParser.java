package sh.miles.inventory.parser.xml;

import jakarta.xml.bind.JAXBContext;
import org.jetbrains.annotations.NotNull;
import sh.miles.inventory.parser.Parser;

import java.io.File;

public class ChestContainerPojoParser implements Parser<ChestContainerPojoParser> {

    private static final JAXBContext context;

    @Override
    public ChestContainerPojoParser parse(@NotNull File file) throws Exception {

        return null;
    }
}
