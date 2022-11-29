package fileWriter;

import moviesLibrary.Movie;
import org.jetbrains.annotations.NotNull;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MoviesWriter implements Writer {


    @Override
    public void write(@NotNull Object object, @NotNull String filePath) throws IOException, XMLStreamException {
        ArrayList<Movie> movies = (ArrayList<Movie>) object;
        XMLOutputFactory output = XMLOutputFactory.newInstance();

        XMLStreamWriter writer = output.createXMLStreamWriter(new FileOutputStream(filePath), "UTF-8");

        writer.writeStartElement("movies");

        for (int i = 0; i < movies.size(); i++) {
            writeMovie(writer, movies.get(i), i);
        }

        writer.writeEndElement();

        writer.writeEndDocument();
        writer.flush();
    }
    private void writeMovie(@NotNull XMLStreamWriter writer, @NotNull Movie movie, int id) throws XMLStreamException {

        writer.writeStartElement("movie");
        writer.writeAttribute("id", String.valueOf(id));

        writer.writeStartElement("name");
        writer.writeCharacters(movie.getName());
        writer.writeEndElement();

        writer.writeStartElement("producer");
        writer.writeCharacters(movie.getProducer());

        writer.writeEndElement();

        writer.writeStartElement("country");
        writer.writeCharacters(movie.getCountry());
        writer.writeEndElement();

        writer.writeStartElement("releaseDate");
        writer.writeCharacters(movie.getReleaseDate());
        writer.writeEndElement();

        writeTags(writer, movie);
        writer.writeEndElement();
    }

    private void writeTags(@NotNull XMLStreamWriter writer, @NotNull Movie movie) throws XMLStreamException {
        writer.writeStartElement("tags");
        for (String tag : movie.getTags()) {
            writer.writeStartElement("tag");
            writer.writeCharacters(tag);
            writer.writeEndElement();
        }
        writer.writeEndElement();
    }
}