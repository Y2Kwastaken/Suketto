package sh.miles.inventory.parser.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.jetbrains.annotations.NotNull;
import sh.miles.inventory.parser.Parser;
import sh.miles.inventory.parser.xml.pojo.ChestPojo;

import java.io.File;

public class ChestPojoParser implements Parser<ChestPojo> {

    private static final JAXBContext context;
    private static final Unmarshaller unmarshaller;

    static {
        try {
            context = JAXBContext.newInstance(ChestPojo.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChestPojo parse(@NotNull File file) throws JAXBException {
        return (ChestPojo) unmarshaller.unmarshal(file);
    }
}
