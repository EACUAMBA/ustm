package com.eacuamba.dev;

import com.eacuamba.dev.entities.Movie;
import com.eacuamba.dev.graph.MovieGraph;
import com.eacuamba.dev.graph.MovieNode;
import com.eacuamba.dev.graph.Node;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    private static final String DATABASE_PATH = "D:\\workspace\\ustm\\inteligencia_artificial\\movie_recommendation_system_using_structure_based_agent\\database";
    private static Connection connection;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //fillDatabase();

        MovieGraph movieGraph = new MovieGraph();

        List<Movie> movieList = retrieveMovieList();
        movieList.forEach(movieGraph::createNodesForMovie);

        movieGraph.getGenreNodeList()
                .stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .forEach(genreNode -> {
                    System.out.println("Movies of Genre " + genreNode.getName());
                    List<MovieNode> movieNodeList = genreNode.getMovieNodeList();
                    movieNodeList.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                            .forEach(movieNode -> System.out.println(movieNode.getName() + " " + movieNode.getValue().getImdbScore()));
                });
        //.forEach(genreNode -> System.out.println(genreNode.getName()));

        System.out.println("Created graph!");
    }

    private static List<Movie> retrieveMovieList() {
        List<Movie> movieList = new ArrayList<>();
        String sql = "select director_name, duration, actor_1_name, actor_2_name, actor_3_name, genres, title, imdb_score from movie;";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Movie movie = Movie
                        .builder()
                        .directorName(resultSet.getString("director_name"))
                        .genreList(Optional.ofNullable(resultSet.getString("genres")).map(s -> Arrays.stream(s.split("[|]")).toList()).orElse(new ArrayList<>()))
                        .actorOneName(resultSet.getString("actor_1_name"))
                        .actorTwoName(resultSet.getString("actor_2_name"))
                        .actorThreeName(resultSet.getString("actor_3_name"))
                        .imdbScore(resultSet.getDouble("imdb_score"))
                        .title(resultSet.getString("title"))
                        .build();

                movieList.add(movie);
            }

            resultSet.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return movieList;
    }

    public static void fillDatabase() {
        try (
                PreparedStatement deleteAllPreparedStatement = getConnection().prepareStatement("DELETE FROM movie where 1=1;");
        ) {
            deleteAllPreparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to wipe movie! ");
            return;
        }

        List<String> movieDataCSVList = getMovieDataCSVList();

        movieDataCSVList
                .stream()
                .skip(1)
                .forEach(App::insertIntoDatabase);

        System.out.println("INSERTED ALL DATA");
    }

    public static void insertIntoDatabase(String csvRow) {
        List<String> csvColumnList = List.of(csvRow.split("[,]")).stream().map(s -> s.trim()).toList();
        String sql = """
                    INSERT INTO movie (`index`,  director_name, duration, actor_1_name, actor_2_name, actor_3_name, genres, title, `language`, country, title_year, imdb_score)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql);) {

            preparedStatement.setString(1, csvColumnList.get(0));
            preparedStatement.setString(2, csvColumnList.get(1));
            preparedStatement.setString(3, csvColumnList.get(2));
            preparedStatement.setString(4, csvColumnList.get(5));
            preparedStatement.setString(5, csvColumnList.get(3));
            preparedStatement.setString(6, csvColumnList.get(8));
            preparedStatement.setString(7, csvColumnList.get(4));
            preparedStatement.setString(8, csvColumnList.get(6));
            preparedStatement.setString(9, csvColumnList.get(11));
            preparedStatement.setString(10, csvColumnList.get(12));
            preparedStatement.setString(11, csvColumnList.get(13));
            preparedStatement.setString(12, csvColumnList.get(14));

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Not inserted! " + csvColumnList);
        }
    }

    public static List<String> getMovieDataCSVList() {
        InputStream inputStream = App.class.getResourceAsStream("/movie_data.csv");

        if (Objects.nonNull(inputStream)) {
            try (Scanner scanner = new Scanner(inputStream)) {
                List<String> temporaryMovieDataCSVList = new ArrayList<>();

                while (scanner.hasNextLine()) {
                    temporaryMovieDataCSVList.add(scanner.nextLine());
                }

                return temporaryMovieDataCSVList;

            }
        }

        return List.of();
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:sqlite://" + DATABASE_PATH);
        }

        return connection;
    }
}
