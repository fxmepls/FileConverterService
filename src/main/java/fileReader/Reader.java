package fileReader;

import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface Reader {
    //интерфейс для функции чтения из файла и функции JsonToXml и функции XmlToJson

    @NotNull Object read(@NotNull String fileName) throws IOException, SAXException, ParserConfigurationException, ParseException;
}