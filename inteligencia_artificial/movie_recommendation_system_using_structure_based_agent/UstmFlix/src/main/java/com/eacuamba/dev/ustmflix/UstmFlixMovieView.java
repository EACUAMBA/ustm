package com.eacuamba.dev.ustmflix;

import com.eacuamba.dev.ustmflix.dto.Preferences;
import com.eacuamba.dev.ustmflix.dto.User;
import com.eacuamba.dev.ustmflix.entities.Movie;
import com.eacuamba.dev.ustmflix.graph.MovieGraph;
import com.eacuamba.dev.ustmflix.graph.MovieNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ustmFlix", value = "/movie")
public class UstmFlixMovieView extends HttpServlet {
    MovieGraph movieGraph = new MovieGraph();

    public void init() {
        this.movieGraph = App.getMovieGraph();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String userId = request.getParameter("userId");
        long id = Long.parseLong(userId);

        User user = this.getUserById(id);
        Preferences preferences = user.getPreferences();


        String movieId = request.getParameter("moviewId");

        Movie movie = this.movieGraph.getMovieWithIndex(movieId);

        if (!movie.getGenreList().isEmpty()) {
            preferences.getGenres().add(movie.getGenreList().get(1));
        }
        preferences.getDirectors().add(movie.getDirectorName());
        preferences.getRanking().add(movie.getImdbScore());
        preferences.getActors().add(movie.getActorOneName());
        preferences.getActors().add(movie.getActorTwoName());
        preferences.getActors().add(movie.getActorThreeName());

        this.save(user);


        request.setAttribute("utilizadorNome", user.getName());
        request.setAttribute("movie", movie);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ustm-flix.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }

    private User getUserById(Long id) {
        Gson gson = new Gson();
        String jsonPath = "D:\\workspace\\ustm\\inteligencia_artificial\\movie_recommendation_system_using_structure_based_agent\\UstmFlix\\src\\main\\resources\\json_data\\users.json";
        try {
            String readString = Files.readString(Paths.get(jsonPath));

            Type typeMyType = new TypeToken<List<User>>() {
            }.getType();
            List<User> l = (List<User>) gson.fromJson(readString, typeMyType);

            return l.stream().filter(user -> user.getId().equals(id)).findFirst().get();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save(User user) {
        Gson gson = new Gson();
        String jsonPath = "D:\\workspace\\ustm\\inteligencia_artificial\\movie_recommendation_system_using_structure_based_agent\\UstmFlix\\src\\main\\resources\\json_data\\users.json";
        try {
            String readString = Files.readString(Paths.get(jsonPath));

            Type typeMyType = new TypeToken<List<User>>() {
            }.getType();
            List<User> l = (List<User>) gson.fromJson(readString, typeMyType);

            l.removeIf(user1 -> user1.getId().equals(user.getId()));

            l.add(user);

            String json = gson.toJson(l);

            Files.writeString(Paths.get(jsonPath), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}