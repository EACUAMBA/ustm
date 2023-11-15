package com.eacuamba.dev.ustmflix.graph_based_knowledge;


import com.eacuamba.dev.ustmflix.dto.Preferences;
import com.eacuamba.dev.ustmflix.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GraphBasedKnowledge {
    private final Set<Node<?>> nodeList = new HashSet<>();

    private void addNode(Node<?> node) {
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
        for (String genre : genreNameList) {
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

    public List<GenreNode> getGenreNodeList() {
        return this.nodeList.stream().filter(GenreNode.class::isInstance).map(GenreNode.class::cast).toList();
    }

    public List<DirectorNode> getDirectorNodeList() {
        return this.nodeList.stream().filter(DirectorNode.class::isInstance).map(DirectorNode.class::cast).toList();
    }

    public List<MovieNode> getMovieNodeList() {
        return this.nodeList.stream().filter(MovieNode.class::isInstance).map(MovieNode.class::cast).toList();
    }

    public List<MovieNode> getMovieNodeList(int size) {
        return this.nodeList.stream().filter(MovieNode.class::isInstance).map(MovieNode.class::cast).limit(size).toList();
    }

    public List<MovieNode> getMovieNodeList(Preferences preferences) {

        List<Map.Entry<String, Integer>> genresStatistic = this.countStringFrequency(preferences.getGenres()).entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        List<Map.Entry<String, Integer>> actorsStatistic = this.countStringFrequency(preferences.getActors()).entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        List<Map.Entry<Double, Integer>> rankingStatistic = this.countStringFrequency(preferences.getRanking()).entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        List<Map.Entry<String, Integer>> directorStatistic = this.countStringFrequency(preferences.getDirectors()).entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();

        List<MapItem<MovieNode, Integer>> items = new ArrayList<>();

        preparingData(genresStatistic, items, actorsStatistic, directorStatistic);

        items.sort(Comparator.comparing(o -> o.value));

        items = items.reversed();

        return Optional.of(items.stream().map(MapItem::getKey).toList()).filter(movieNodes -> !movieNodes.isEmpty()).orElse(this.getMovieNodeList()).stream().distinct().toList();
    }

    private void preparingData(List<Map.Entry<String, Integer>> genresStatistic, List<MapItem<MovieNode, Integer>> mapItems, List<Map.Entry<String, Integer>> actorsStatistic, List<Map.Entry<String, Integer>> directorStatistic) {
        this.getMovieNodeList()
                .forEach(movieNode -> {
                    int v = 0;
                    if (movieNode.getGenreNodeList().stream().anyMatch(genreNode -> {
                        return genresStatistic.stream().map(Map.Entry::getKey).anyMatch(genreNode.getName()::equals);
                    })) {
                        v = v + 1;
                    }

                    if (movieNode.getActorNodeList().stream().anyMatch(genreNode -> {
                        return actorsStatistic.stream().map(Map.Entry::getKey).anyMatch(genreNode.getName()::equals);
                    })) {
                        v = v + 1;
                    }

                    if (movieNode.getDirectorNodeList().stream().anyMatch(genreNode -> {
                        return directorStatistic.stream().map(Map.Entry::getKey).anyMatch(genreNode.getName()::equals);
                    })) {
                        v = v + 1;
                    }

                    if(v == 1){
                        mapItems.add(new MapItem<>(movieNode, 5000));
                    }

                    if(v == 2){
                        mapItems.add(new MapItem<>(movieNode, 7500));
                    }

                    if(v == 3){
                        mapItems.add(new MapItem<>(movieNode, 10000));
                    }
                });



        for (Map.Entry<String, Integer> entry : genresStatistic) {
            this.getMovieNodeList()
                    .forEach(movieNode -> {
                        if (movieNode.getGenreNodeList().stream().map(Node::getName).anyMatch(entry.getKey()::equals)) {
                            mapItems.add(new MapItem<>(movieNode, entry.getValue()));
                        }
                    });
        }

        for (Map.Entry<String, Integer> entry : actorsStatistic) {
            this.getMovieNodeList()
                    .forEach(movieNode -> {
                        if (movieNode.getActorNodeList().stream().map(Node::getName).anyMatch(entry.getKey()::equals)) {
                            mapItems.add(new MapItem<>(movieNode, entry.getValue()));
                        }
                    });
        }


        for (Map.Entry<String, Integer> entry : directorStatistic) {
            this.getMovieNodeList()
                    .forEach(movieNode -> {
                        if (movieNode.getDirectorNodeList().stream().map(Node::getName).anyMatch(entry.getKey()::equals)) {
                            mapItems.add(new MapItem<>(movieNode, entry.getValue()));
                        }
                    });
        }
    }

    public List<ActorNode> getActorNodeList() {
        return this.nodeList.stream().filter(ActorNode.class::isInstance).map(ActorNode.class::cast).toList();
    }

    public Movie getMovieWithIndex(Integer movieId) {
        return this.getMovieNodeList().stream().map(Node::getValue).filter(m -> m.getIndex().equals(movieId)).findFirst().get();
    }

    public <T> Map<T, Integer> countStringFrequency(List<T> stringList) {
        Map<T, Integer> frequencyMap = new HashMap<>();

        for (T str : stringList) {
            frequencyMap.put(str, frequencyMap.getOrDefault(str, 0) + 1);
        }

        return frequencyMap;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    static
    class MapItem<T, V>{
        private T key;
        private V value;
    }
}
