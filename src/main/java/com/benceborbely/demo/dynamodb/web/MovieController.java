package com.benceborbely.demo.dynamodb.web;

import com.benceborbely.demo.dynamodb.model.Movie;
import com.benceborbely.demo.dynamodb.model.MovieRequest;
import com.benceborbely.demo.dynamodb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.save(createMovieFrom(movieRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody MovieRequest movieRequest, @PathVariable String id) {
        Optional<Movie> movie = movieService.findBy(id);

        if (!movie.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Movie updatedMovie = createMovieFrom(movieRequest);
        updatedMovie.setId(movie.get().getId());

        return ResponseEntity.ok(movieService.save(updatedMovie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable String id) {
        Optional<Movie> movie = movieService.findBy(id);

        return movie.isPresent()
                ? ResponseEntity.ok(movie.get())
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        if (!movieService.findBy(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        movieService.deleteBy(id);

        return ResponseEntity.ok().build();
    }

    private Movie createMovieFrom(MovieRequest movieRequest) {
        return Movie
            .builder()
            .title(movieRequest.getTitle())
            .director(movieRequest.getDirector())
            .length(movieRequest.getLength())
            .year(movieRequest.getYear())
            .ageLimit(movieRequest.getAgeLimit())
            .starActors(movieRequest.getStarActors())
            .build();
    }

}
