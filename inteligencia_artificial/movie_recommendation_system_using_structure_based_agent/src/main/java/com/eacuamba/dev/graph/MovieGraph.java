package com.eacuamba.dev.graph;

import java.util.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.eacuamba.dev.entities.Movie;

public class MovieGraph {
    private final Set<Node<?>> nodeList = new HashSet<>();

    private void addNode(Node<?> node){
        this.nodeList.add(node);
    }

    public void createNodesForMovie(Movie movie) {
        //Movie
        String movieName = movie.getTitle();
        Optional<MovieNode> movieNodeOptional = this.nodeList
                .stream()
                .filter(MovieNode.class::isInstance)
                .filter((node) -> node.getName().equals(movieName))
                .map(MovieNode.class::cast)
                .findFirst();

        MovieNode movieNode = movieNodeOptional
                .orElse(MovieNode.builder().value(movie).name(movieName).build());

        // Director
        String directorName = movie.getDirectorName();
        Optional<DirectorNode> directorNodeOptional = this.nodeList
                .stream()
                .filter(DirectorNode.class::isInstance)
                .filter((node) -> node.getName().equals(directorName))
                .map(DirectorNode.class::cast)
                .findFirst();

        DirectorNode directorNode = directorNodeOptional
                .orElse(DirectorNode.builder().value(directorName).name(directorName).build());

        // ActorOne
        String actorOneName = movie.getActorOneName();
        Optional<ActorNode> actorOneNodeOptional = this.nodeList
                .stream()
                .filter(ActorNode.class::isInstance)
                .filter((node) -> node.getName().equals(actorOneName))
                .map(ActorNode.class::cast)
                .findFirst();

        ActorNode actorOneNode = actorOneNodeOptional
                .orElse(ActorNode.builder().value(actorOneName).name(actorOneName).build());

        // ActorTwo
        String actorTwoName = movie.getActorTwoName();
        Optional<ActorNode> actorTwoNodeOptional = this.nodeList
                .stream()
                .filter(ActorNode.class::isInstance)
                .filter((node) -> node.getName().equals(actorTwoName))
                .map(ActorNode.class::cast)
                .findFirst();

        ActorNode actorTwoNode = actorTwoNodeOptional
                .orElse(ActorNode.builder().value(actorTwoName).name(actorTwoName).build());

        // ActorThree
        String actorThreeName = movie.getActorThreeName();
        Optional<ActorNode> actorThreeNodeOptional = this.nodeList
                .stream()
                .filter(ActorNode.class::isInstance)
                .filter((node) -> node.getName().equals(actorThreeName))
                .map(ActorNode.class::cast)
                .findFirst();

        ActorNode actorThreeNode = actorThreeNodeOptional
                .orElse(ActorNode.builder().value(actorThreeName).name(actorThreeName).build());

        // Ratings
        Double rating = movie.getImdbScore();
        Optional<RatingNode> ratingNodeOptional = this.nodeList
                .stream()
                .filter(ActorNode.class::isInstance)
                .filter((node) -> node.getName().equals(Objects.toString(rating)))
                .map(RatingNode.class::cast)
                .findFirst();

        RatingNode ratingNode = ratingNodeOptional
                .orElse(RatingNode.builder().value(rating).name(Objects.toString(rating)).build());

        // GenreList
        List<String> genreNameList = movie.getGenreList();
        for(String genre: genreNameList){
            Optional<GenreNode> genreNodeOptional = this.nodeList
                    .stream()
                    .filter(GenreNode.class::isInstance)
                .filter((node) -> node.getName().equals(genre))
                .map(GenreNode.class::cast)
                .findFirst();

        GenreNode genreNode = genreNodeOptional
                .orElse(GenreNode.builder().value(genre).name(genre).build());

        
        genreNode.addNode(movieNode);
        genreNode.addNode(directorNode);
        genreNode.addNode(actorOneNode);
        genreNode.addNode(actorTwoNode);
        genreNode.addNode(actorThreeNode);
        genreNode.addNode(ratingNode);
        genreNode.addNode(genreNode);

        movieNode.addNode(genreNode);
        directorNode.addNode(genreNode);
        actorOneNode.addNode(genreNode);
        actorTwoNode.addNode(genreNode);
        actorThreeNode.addNode(genreNode);
        ratingNode.addNode(genreNode);

            this.addNode(genreNode);
        }

        movieNode.addNode(directorNode);
        movieNode.addNode(actorOneNode);
        movieNode.addNode(actorTwoNode);
        movieNode.addNode(actorThreeNode);
        movieNode.addNode(ratingNode);

        directorNode.addNode(movieNode);
        directorNode.addNode(actorOneNode);
        directorNode.addNode(actorTwoNode);
        directorNode.addNode(actorThreeNode);
        directorNode.addNode(ratingNode);

        actorOneNode.addNode(movieNode);
        actorOneNode.addNode(directorNode);
        actorOneNode.addNode(actorTwoNode);
        actorOneNode.addNode(actorThreeNode);
        actorOneNode.addNode(ratingNode);

        actorTwoNode.addNode(movieNode);
        actorTwoNode.addNode(directorNode);
        actorTwoNode.addNode(actorOneNode);
        actorTwoNode.addNode(actorThreeNode);
        actorTwoNode.addNode(ratingNode);

        actorThreeNode.addNode(movieNode);
        actorThreeNode.addNode(directorNode);
        actorThreeNode.addNode(actorOneNode);
        actorThreeNode.addNode(actorTwoNode);
        actorThreeNode.addNode(ratingNode);

        ratingNode.addNode(movieNode);
        ratingNode.addNode(directorNode);
        ratingNode.addNode(actorOneNode);
        ratingNode.addNode(actorTwoNode);
        ratingNode.addNode(actorThreeNode);

        this.addNode(movieNode);
        this.addNode(directorNode);
        this.addNode(actorOneNode);
        this.addNode(actorTwoNode);
        this.addNode(actorThreeNode);
        this.addNode(ratingNode);
    }

    public List<GenreNode> getGenreNodeList(){
        return this.nodeList.stream().filter(GenreNode.class::isInstance).map(GenreNode.class::cast).toList();
    }

    public List<DirectorNode> getDirectorNodeList(){
        return this.nodeList.stream().filter(DirectorNode.class::isInstance).map(DirectorNode.class::cast).toList();
    }

    public List<MovieNode> getMovieNodeList(){
        return this.nodeList.stream().filter(MovieNode.class::isInstance).map(MovieNode.class::cast).toList();
    }

    public List<ActorNode> getActorNodeList(){
        return this.nodeList.stream().filter(ActorNode.class::isInstance).map(ActorNode.class::cast).toList();
    }
}
