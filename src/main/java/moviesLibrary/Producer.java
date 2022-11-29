package moviesLibrary;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Producer {
    private String name; //имя режиссера
    private ArrayList<Movie> movies; //список фильмов режиссера

    public Producer() {
        movies = new ArrayList<>();
    }
    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(@NotNull ArrayList<Movie> movies) {
        this.movies = movies;
    }
}