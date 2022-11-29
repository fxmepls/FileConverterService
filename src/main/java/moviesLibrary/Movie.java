package moviesLibrary;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Movie {
    private String name; //название фильма
    private String producer; //режиссер фильма
    private String country; //страна произведения
    private String releaseDate; //дата выпуска
    private ArrayList<String> tags; //список жанров

    public Movie() {
        tags = new ArrayList<>();
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getProducer() {
        return producer;
    }

    public void setProducer(@NotNull String producer) {
        this.producer = producer;
    }

    public @NotNull String getCountry() {
        return country;
    }

    public void setCountry(@NotNull String country) {
        this.country = country;
    }

    public @NotNull String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public @NotNull ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(@NotNull ArrayList<String> tags) {
        this.tags = tags;
    }
}