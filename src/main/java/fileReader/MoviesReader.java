package fileReader;

import moviesLibrary.Movie;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
public class MoviesReader implements Reader {

    public @NotNull Object read(@NotNull String fileName) throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = documentBuilder.parse(fileName);
        return getMovies(document);
    }
    private @NotNull ArrayList<Movie> getMovies(@NotNull Document document) {
        ArrayList<Movie> movies = new ArrayList<>();

        Node root = document.getDocumentElement();
        NodeList movieNodes = root.getChildNodes();

        for (int i = 0; i < movieNodes.getLength(); i++) {
            Node movieNode = movieNodes.item(i);
            // Если item не текст
            if (movieNode.getNodeType() != Node.TEXT_NODE) {
                Movie movie = getMovie(movieNode);
                movies.add(movie);
            }
        }
        return movies;
    }
    private @NotNull Movie getMovie(@NotNull Node movieNode) {

        Movie movie = new Movie();
        NodeList movieProperties = movieNode.getChildNodes();
        for (int i = 0; i < movieProperties.getLength(); i++) {
            Node movieProperty = movieProperties.item(i);
            if (movieProperty.getNodeType() != Node.TEXT_NODE) {
                switchProperty(movieProperty, movie);
            }
        }
        return movie;
    }
    private void switchProperty(@NotNull Node movieProperty, @NotNull Movie movie) {
        switch (movieProperty.getNodeName()) {
            case "name" -> movie.setName(movieProperty.getTextContent());
            case "producer" -> movie.setProducer(movieProperty.getTextContent());
            case "country" -> movie.setCountry(movieProperty.getTextContent());
            case "releaseDate" -> movie.setReleaseDate(movieProperty.getTextContent());
            case "tags" -> setTagsToMovie(movieProperty, movie);
            default -> throw new IllegalArgumentException("Неопознанное имя тега");
        }
    }
    private void setTagsToMovie(@NotNull Node movieProperty, @NotNull Movie movie) {
        ArrayList<String> tags = new ArrayList<>();
        NodeList tagsNode = movieProperty.getChildNodes();
        for (int i = 0; i < tagsNode.getLength(); i++) {
            Node tagNode = tagsNode.item(i);
            if (tagNode.getNodeType() != Node.TEXT_NODE) {
                tags.add(tagNode.getTextContent());
            }
        }
        movie.setTags(tags);
    }
}