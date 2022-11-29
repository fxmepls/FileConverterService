import fileConverter.JsonToXml;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

public class JsonToXmlConverter {


    public static void main(@NotNull String [] args) throws IOException, ParseException, XMLStreamException {
        if (new File("data/output").mkdirs()) {
            System.out.println("Директория для сохранения выходных файлов успешно создана\n");
        }
        JsonToXml converter = new JsonToXml();
        converter.convert(args[0], args[1]);
        System.out.println("Преобразование прошло успешно, результат сохранен в файл data/output/" + args[1] );
    }
}