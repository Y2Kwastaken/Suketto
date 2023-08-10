package sh.miles.inventory.parser.adapter.xml;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.jetbrains.annotations.NotNull;

public class XMLClassAdapter extends XmlAdapter<String, Class<?>> {

    @Override
    public Class<?> unmarshal(@NotNull final String string) throws Exception {
        return Class.forName(string);
    }

    @Override
    public String marshal(@NotNull Class<?> clazz) throws Exception {
        return clazz.getName();
    }
}
