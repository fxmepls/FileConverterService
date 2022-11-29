package fileReader;

import moviesLibrary.Movie;
import moviesLibrary.Producer;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ProducersReader implements Reader {

    @Override
    public @NotNull Object read(@NotNull String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(fileName);

        JSONObject document = (JSONObject) parser.parse(reader);

        return getProducers(document);
    }

    private @NotNull ArrayList<Producer> getProducers(@NotNull JSONObject document) {
        ArrayList<Producer> producers = new ArrayList<>();

        JSONArray producersJson = (JSONArray) document.get("producers");

        for (Object producerJson : producersJson) {
            JSONObject producerProperties = (JSONObject) producerJson;
            producerProperties = (JSONObject) producerProperties.get("producer");
            Producer producer = getProducer(producerProperties);
            fillProducersInMovies(producer);
            producers.add(producer);
        }

        return producers;
    }
    private @NotNull Producer getProducer(@NotNull JSONObject producerProperties) {
        Producer producer = new Producer();

        producer.setName(producerProperties.get("name").toString());
        JSONArray moviesJson = (JSONArray) producerProperties.get("movies");

        ArrayList<Movie> movies = getMovies(moviesJson);
        producer.setMovies(movies);
        return producer;
    }
    private @NotNull ArrayList<Movie> getMovies(@NotNull JSONArray moviesJson) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (Object movieJson : moviesJson) {
            JSONObject movieData = (JSONObject) movieJson;
            Movie movie = getMovie(movieData);
            movies.add(movie);
        }
        return movies;
    }
    private @NotNull Movie getMovie(@NotNull JSONObject movieData) {
        Movie movie = new Movie();

        movie.setName(movieData.get("name").toString());

        movie.setCountry(movieData.get("country").toString());
        movie.setReleaseDate(movieData.get("releaseDate").toString());

        JSONArray tagsJson = (JSONArray) movieData.get("tags");

        setTagsToMovie(tagsJson, movie);
        return movie;
    }

    private void setTagsToMovie(@NotNull JSONArray tagsJson, @NotNull Movie movie) {
        ArrayList<String> tags = new ArrayList<>();
        for (Object tag : tagsJson) {
            tags.add(tag.toString());
        }
        movie.setTags(tags);
    }
    private void fillProducersInMovies(@NotNull Producer producer) {
        ArrayList<Movie> movies = producer.getMovies();
        for (Movie movie : movies) {
            movie.setProducer(producer.getName());
        }
    }
}