package fileWriter;

import moviesLibrary.Movie;
import moviesLibrary.Producer;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProducersWriter implements Writer {
    @Override
    public void write(@NotNull Object object, @NotNull String filePath) throws IOException {

        ArrayList<Producer> producers = (ArrayList<Producer>) object;

        JSONObject producersObject = getProducersObject(producers);
        String json = producersObject.toJSONString(producersObject);

        File file = new File(filePath);
        file.createNewFile();
        FileWriter writer = new FileWriter(filePath, false);

        writer.write(json);
        writer.flush();
        writer.close();
    }

    private @NotNull JSONObject getProducersObject(@NotNull ArrayList<Producer> producers) {
        JSONArray jsonProducers = new JSONArray();
        for (Producer producer : producers) {
            JSONObject jsonProducer = new JSONObject();
            jsonProducer.put("name", producer.getName());

            jsonProducer.put("movies", getMoviesJson(producer));

            JSONObject genreObject = new JSONObject();
            genreObject.put("producer", jsonProducer);
            jsonProducers.add(genreObject);
        }

        JSONObject producersObject = new JSONObject();
        producersObject.put("producers", jsonProducers);
        return producersObject;
    }

    private @NotNull JSONArray getMoviesJson(@NotNull Producer producer) {
        JSONArray jsonMovies = new JSONArray();
        for (Movie movie : producer.getMovies()) {
            JSONObject moviesObject = new JSONObject();
            moviesObject.put("name", movie.getName());
            moviesObject.put("country", movie.getCountry());
            moviesObject.put("releaseDate", movie.getReleaseDate());
            moviesObject.put("tags", getTagsJson(movie));
            jsonMovies.add(moviesObject);
        }

        return jsonMovies;
    }

    private @NotNull JSONArray getTagsJson(@NotNull Movie movie) {
        JSONArray jsonTags = new JSONArray();
        for (String tag : movie.getTags()) {
            jsonTags.add(tag);
        }
        return jsonTags;
    }
}