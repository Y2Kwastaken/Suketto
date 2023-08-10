package sh.miles.inventory.parser.impl.xml;

import jakarta.xml.bind.JAXBException;
import org.junit.Test;
import sh.miles.inventory.parser.xml.pojo.ChestPojo;
import sh.miles.inventory.parser.xml.ChestPojoParser;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class ChestPojoParserTest {

    @Test
    public void testChestPojoParser() throws JAXBException, URISyntaxException {
        final File file = new File(getClass().getClassLoader().getResource("normal/inventory.xml").toURI());
        final ChestPojoParser parser = new ChestPojoParser();
        final ChestPojo pojo = parser.parse(file);
        assertNotNull(pojo);

        assertEquals("Example", pojo.getTitle().toPlainText());
        assertEquals("test-menu.json", pojo.getLink());
    }

}
