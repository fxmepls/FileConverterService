package fileConverter;

import fileReader.MoviesReader;
import fileWriter.ProducersWriter;
import moviesLibrary.Movie;
import moviesLibrary.Producer;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.SAXException;
import tools.FileExtension;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class XmlToJson implements FileConverter {

    @Override
    public void convert(@NotNull String xmlFileName, @NotNull String jsonFileName) throws IOException, ParserConfigurationException, SAXException {
        if (!FileExtension.getExtension(jsonFileName).equals("json")) {
            throw new IllegalArgumentException("Неверное расширение файла");
        }
        MoviesReader reader = new MoviesReader();

        ArrayList<Movie> movies = (ArrayList<Movie>) reader.read("data/input/" + xmlFileName);

        ArrayList<Producer> producers = convertMoviesToProducers(movies);

        ProducersWriter writer = new ProducersWriter();

        writer.write(producers, "data/output/" + jsonFileName);
    }


    private @NotNull ArrayList<Producer> convertMoviesToProducers(@NotNull ArrayList<Movie> movies) {

        ArrayList<Producer> producers = new ArrayList<>();

        for (Movie movie : movies) {
            Producer producer = new Producer();
            producer.setName(movie.getProducer());

            if (contains(producers, producer.getName())) continue;

            ArrayList<Movie> producerMovies = new ArrayList<>();
            for (Movie movieLoop : movies) {
                if (movieLoop.getProducer().equals(producer.getName())) {
                    producerMovies.add(movieLoop);
                }
            }
            producer.setMovies(producerMovies);
            producers.add(producer);
        }

        return producers;
    }

    private boolean contains(@NotNull ArrayList<Producer> producers, @NotNull String producerName) {
        for (Producer producer : producers) {
            if (producer.getName().equals(producerName)) {
                return true;
            }
        }
        return false;
    }
}