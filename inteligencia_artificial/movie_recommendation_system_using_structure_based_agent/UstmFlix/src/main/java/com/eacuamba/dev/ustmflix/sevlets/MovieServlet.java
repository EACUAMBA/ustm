package com.eacuamba.dev.ustmflix.sevlets;

import com.eacuamba.dev.ustmflix.App;
import com.eacuamba.dev.ustmflix.dto.Preferences;
import com.eacuamba.dev.ustmflix.dto.User;
import com.eacuamba.dev.ustmflix.entities.Movie;
import com.eacuamba.dev.ustmflix.graph_based_knowledge.GraphBasedKnowledge;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/movie")
public class MovieServlet extends HttpServlet {
    public static final String USTMFLIX = Paths.get("\\").toAbsolutePath().resolve("ustmflix").resolve("users.json").toString();
    GraphBasedKnowledge graphBasedKnowledge = new GraphBasedKnowledge();

    public void init() {
        this.graphBasedKnowledge = App.getMovieGraph();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String userId = request.getParameter("userId");
        long id = Long.parseLong(StringUtils.defaultIfBlank(userId, "1"));

        User user = this.getUserById(id);
        Preferences preferences = user.getPreferences();

        String movieId = request.getParameter("movieId");

        Movie movie = this.graphBasedKnowledge.getMovieWithIndex(Integer.parseInt(movieId));

        if (!movie.getGenreList().isEmpty()) {
            preferences.getGenres().addAll(movie.getGenreList());
        }

        preferences.getDirectors().add(movie.getDirectorName());
        preferences.getRanking().add(movie.getImdbScore());
        preferences.getActors().add(movie.getActorOneName());
        preferences.getActors().add(movie.getActorTwoName());
        preferences.getActors().add(movie.getActorThreeName());

        this.save(user);

        request.setAttribute("utilizadorNome", user.getName());
        request.setAttribute("movie", movie);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/movie.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }

    private User getUserById(Long id) {
        Gson gson = new Gson();
        String jsonPath = USTMFLIX;
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
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        String jsonPath = USTMFLIX;
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