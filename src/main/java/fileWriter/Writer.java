package fileWriter;

import org.jetbrains.annotations.NotNull;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;

public interface Writer {
    void write(@NotNull Object object, @NotNull String fileName) throws IOException, XMLStreamException, TransformerConfigurationException;
}