package com.eacuamba.dev.ustmflix;

import com.eacuamba.dev.ustmflix.entities.Movie;
import com.eacuamba.dev.ustmflix.graph_based_knowledge.GraphBasedKnowledge;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static final String DATABASE_PATH = Paths.get("\\").toAbsolutePath().resolve("ustmflix").resolve("database.db").toString();
    public static final String USTMFLIX = Paths.get("\\").toAbsolutePath().resolve("ustmflix").resolve("users.json").toString();
    private static Connection connection;

    private static GraphBasedKnowledge graphBasedKnowledge;

    public static GraphBasedKnowledge getMovieGraph() {
        return graphBasedKnowledge;
    }

    public static GraphBasedKnowledge BuildGraph() {
        System.out.println("Hello World!");
        //fillDatabase();

        GraphBasedKnowledge graphBasedKnowledge = new GraphBasedKnowledge();

        List<Movie> movieList = retrieveMovieList();

        movieList
                .stream()
                .forEach(graphBasedKnowledge::createNodesForMovie);

        System.out.println("Created graph!");

        return App.graphBasedKnowledge = graphBasedKnowledge;
    }

    private static List<Movie> retrieveMovieList() {
        List<Movie> movieList = new ArrayList<>();
        String sql = "select director_name, duration, actor_1_name, actor_2_name, actor_3_name, genres, title, imdb_score, `index` from movie;";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Movie movie = Movie
                        .builder()
                        .directorName(resultSet.getString("director_name"))
                        //.genreList(Optional.ofNullable(resultSet.getString("genres")).map(s -> Arrays.stream(s.split("[|]")).toList()).orElse(new ArrayList<>()))
                        .genres(resultSet.getString("genres"))
                        .actorOneName(resultSet.getString("actor_1_name"))
                        .actorTwoName(resultSet.getString("actor_2_name"))
                        .actorThreeName(resultSet.getString("actor_3_name"))
                        .imdbScore(resultSet.getDouble("imdb_score"))
                        .title(resultSet.getString("title"))
                        .index(resultSet.getInt("index"))
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
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite://" + DATABASE_PATH);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    public static void setDirectoriesAndCopyFiles() {
        try {
            Path ustmflix = Files.createDirectories(Paths.get("\\").resolve("ustmflix").toAbsolutePath());
            Path usersPath = ustmflix.resolve("users.json");
            if (Files.notExists(usersPath)) {
                InputStream resourceAsStream = App.class.getResourceAsStream("/users.json");
                Path usersFile = Files.createFile(ustmflix.resolve("users.json"));
                Files.copy(resourceAsStream, usersFile, StandardCopyOption.REPLACE_EXISTING);
            }

            Path databasePath = ustmflix.resolve("database.db");
            if (Files.notExists(databasePath)) {
                InputStream resourceAsStream2 = App.class.getResourceAsStream("/database.db");
                Path databaseFile = Files.createFile(databasePath);
                Files.copy(resourceAsStream2, databaseFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
