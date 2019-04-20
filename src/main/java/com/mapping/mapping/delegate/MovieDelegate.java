package com.mapping.mapping.delegate;

import com.mapping.mapping.constant.Genre;
import com.mapping.mapping.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieDelegate {

    public final List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Shrek", Genre.FAMILY));
        movies.add(new Movie(2, "300", Genre.ACTION));
        movies.add(new Movie(3,"Avengers: Endgame", Genre.ACTION));
        movies.add(new Movie(4,"Shazam!", Genre.COMEDY));
        movies.add(new Movie(5,"Breakthrough", Genre.DRAMA));
        movies.add(new Movie(6,"Dumbo", Genre.FAMILY));
        movies.add(new Movie(7,"Penguins", Genre.DOCUMENTARY));
        movies.add(new Movie(8,"Us", Genre.HORROR));
        return movies;
    }

    public final List<Movie> getByGenre(Genre genre) {
        return getAllMovies()
            .stream()
            .filter(movie -> movie.getGenre().equals(genre))
            .collect(Collectors.toList());
    }

    public final Optional<Movie> getById(Integer id) {
        return getAllMovies()
            .stream()
            .filter(movie -> movie.getId().equals(id))
            .findAny();
    }
}
