package com.benceborbely.demo.dynamodb.web;

import com.benceborbely.demo.dynamodb.model.FavoriteMovies;
import com.benceborbely.demo.dynamodb.model.FavoriteMoviesRequest;
import com.benceborbely.demo.dynamodb.service.FavoriteMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/favoriteMovies")
public class FavoriteMovieController {

    private FavoriteMoviesService favoriteMoviesService;

    @Autowired
    public FavoriteMovieController(FavoriteMoviesService favoriteMoviesService) {
        this.favoriteMoviesService = favoriteMoviesService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody FavoriteMoviesRequest favoriteMoviesRequest) {
        FavoriteMovies favoriteMovies = new FavoriteMovies();
        favoriteMovies.setUserId(favoriteMoviesRequest.getUserId());
        favoriteMovies.setAddedTsInMs(favoriteMoviesRequest.getAddedTsInMs());
        favoriteMovies.setTitle(favoriteMoviesRequest.getTitle());
        favoriteMovies.setYear(favoriteMoviesRequest.getYear());
        favoriteMovies.setLength(favoriteMoviesRequest.getLength());

        return ResponseEntity.ok(favoriteMoviesService.save(favoriteMovies));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteMovies>> getFavoriteMovies(@PathVariable String userId, @RequestParam long fromTs) {
        return ResponseEntity.ok(favoriteMoviesService.getFavoriteMovies(userId, fromTs));
    }

}
