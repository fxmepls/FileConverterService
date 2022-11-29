package fileConverter;

import fileReader.ProducersReader;
import fileWriter.MoviesWriter;
import moviesLibrary.Movie;
import moviesLibrary.Producer;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import tools.FileExtension;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;

public class JsonToXml implements FileConverter {

    @Override
    public void convert(@NotNull String jsonFileName, @NotNull String xmlFileName) throws IOException, ParseException, XMLStreamException {
        if (!FileExtension.getExtension(xmlFileName).equals("xml")) {
            throw new IllegalArgumentException("Неверное расширение файла");
        }
        ProducersReader reader = new ProducersReader();

        ArrayList<Producer> producers = (ArrayList<Producer>) reader.read("data/input/" + jsonFileName);

        ArrayList<Movie> movies = convertProducersToMovie(producers);

        MoviesWriter writer = new MoviesWriter();

        writer.write(movies, "data/output/" + xmlFileName);
    }

    private @NotNull ArrayList<Movie> convertProducersToMovie(@NotNull ArrayList<Producer> producers) {
        ArrayList<Movie> movies = new ArrayList<>();

        for (Producer producer : producers) {
            ArrayList<Movie> producerMovies = producer.getMovies();

            movies.addAll(producerMovies);
        }

        return movies;
    }
}