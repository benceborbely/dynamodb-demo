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
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setDirector(movieRequest.getDirector());
        movie.setLength(movieRequest.getLength());
        movie.setYear(movieRequest.getYear());
        movie.setAgeLimit(movieRequest.getAgeLimit());
        movie.setStarActors(movieRequest.getStarActors());

        return ResponseEntity.ok(movieService.save(movie));
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

}
