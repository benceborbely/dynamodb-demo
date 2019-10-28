package com.benceborbely.demo.dynamodb.web;

import com.benceborbely.demo.dynamodb.model.FavoriteMovie;
import com.benceborbely.demo.dynamodb.service.FavoriteMovieService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favoriteMovies")
public class FavoriteMovieController {

    private FavoriteMovieService favoriteMovieService;

    @Autowired
    public FavoriteMovieController(FavoriteMovieService favoriteMovieService) {
        this.favoriteMovieService = favoriteMovieService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody FavoriteMovie favoriteMovie) {
        return ResponseEntity.ok(favoriteMovieService.save(favoriteMovie));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteMovie>> getFavoriteMovies(@PathVariable String userId, @RequestParam long fromTs) {
        return ResponseEntity.ok(favoriteMovieService.getFavoriteMovies(userId, fromTs));
    }

}
