package com.eacuamba.dev.ustmflix.sevlets;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.eacuamba.dev.ustmflix.App;
import com.eacuamba.dev.ustmflix.dto.Preferences;
import com.eacuamba.dev.ustmflix.dto.User;
import com.eacuamba.dev.ustmflix.entities.Movie;
import com.eacuamba.dev.ustmflix.graph_based_knowledge.GraphBasedKnowledge;
import com.eacuamba.dev.ustmflix.graph_based_knowledge.MovieNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.lang3.StringUtils;

import static com.eacuamba.dev.ustmflix.sevlets.MovieServlet.USTMFLIX;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    GraphBasedKnowledge graphBasedKnowledge = new GraphBasedKnowledge();
    public void init() {
        this.graphBasedKnowledge = App.getMovieGraph();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String userId = request.getParameter("userId");
        long id = Long.parseLong(StringUtils.defaultIfBlank(userId, "1"));

        User userById = this.getUserById(id);
        Preferences preferences = userById.getPreferences();

        List<Movie> list = new ArrayList<>(graphBasedKnowledge.getMovieNodeList(preferences).stream().map(MovieNode::getValue).toList());

        if(preferences.getActors().isEmpty() && preferences.getDirectors().isEmpty() && preferences.getRanking().isEmpty() && preferences.getGenres().isEmpty()){
            Collections.shuffle(list);
        }

        Movie[] movieList = list.toArray(Movie[]::new);


        request.setAttribute("utilizadorNome", userById.getName());
        request.setAttribute("userId", userById.getId());
        request.setAttribute("movieList", movieList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ustm-flix.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }

    private User getUserById(Long id){
        Gson gson = new Gson();
        String jsonPath = USTMFLIX;
        try {
            String readString = Files.readString(Paths.get(jsonPath));

            Type typeMyType = new TypeToken<List<User>>(){}.getType();
            List<User> l =  (List<User>)gson.fromJson(readString, typeMyType);

            return l.stream().filter(user -> user.getId().equals(id)).findFirst().get();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save(User user){
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        String jsonPath = USTMFLIX;
        try {
            String readString = Files.readString(Paths.get(jsonPath));

            Type typeMyType = new TypeToken<List<User>>(){}.getType();
            List<User> l =  (List<User>)gson.fromJson(readString, typeMyType);

            l.removeIf(user1 -> user1.getId().equals(user.getId()));

            l.add(user);

            String json = gson.toJson(l);

            Files.writeString(Paths.get(jsonPath), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}