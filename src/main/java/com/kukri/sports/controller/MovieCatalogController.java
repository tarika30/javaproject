package com.kukri.sports.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kukri.sports.exceptions.DuplicateResourceException;
import com.kukri.sports.exceptions.ResourceNotFoundException;
import com.kukri.sports.model.MovieCatalog;
import com.kukri.sports.service.MovieCatalogService;

/**
 * @author tarika
 * 
 */

@RestController
@RequestMapping("/movie")
public class MovieCatalogController {

	@Autowired
	public MovieCatalogService movieCatalogService;

//	@Autowired
//    MessageSource messageSource;

	@GetMapping("/getall")
	public List<MovieCatalog> getMovieCatlog() {
		return movieCatalogService.findAll();
	}

	@GetMapping("/getmovie/{name}")
	public ResponseEntity<MovieCatalog> getMovieByName(@PathVariable String name) throws ResourceNotFoundException {
		MovieCatalog movie = movieCatalogService.findByName(name);
		Optional<MovieCatalog> mv = Optional.ofNullable(movie);
		mv.orElseThrow(() -> new ResourceNotFoundException("Movie " + name + " not found."));
		return ResponseEntity.ok().body(movie);

	}

	@PostMapping("/savemovie")
	public MovieCatalog saveMovie(@RequestBody MovieCatalog newMovie) throws SQLIntegrityConstraintViolationException {
		MovieCatalog movie = movieCatalogService.findByName(newMovie.getName());
		if (!(movie == null)) {
			throw new DuplicateResourceException("Movie " + newMovie.getName() + " already exist.");
		} else {
			return movieCatalogService.saveMovie(newMovie);
		}

	}

	@PutMapping("/updatemovie")
	public MovieCatalog updateMovie(@RequestBody MovieCatalog newMovie) {
		MovieCatalog movie = movieCatalogService.findByName(newMovie.getName());
		Optional<MovieCatalog> mv = Optional.ofNullable(movie);
		mv.ifPresentOrElse(value -> {
			movie.setDirector(newMovie.getDirector());
			movie.setName(newMovie.getName());
			movie.setRating(newMovie.getRating());
			// just to show result
			newMovie.setId(movie.getId());
			movieCatalogService.saveMovie(movie);
		}, () -> movieCatalogService.saveMovie(newMovie));
		return newMovie;
	}

	@DeleteMapping("/deletemovie/{name}")
	public ResponseEntity<String> deleteEmployee(@PathVariable String name) {
		MovieCatalog movie = movieCatalogService.findByName(name);
		Optional<MovieCatalog> mv = Optional.ofNullable(movie);
		mv.orElseThrow(() -> new ResourceNotFoundException("Movie " + name + " not found to delete."));
		@SuppressWarnings("unused")
		Integer x = movieCatalogService.delete(movie);
		return ResponseEntity.ok().body("Movie Deleted");
	}

	@GetMapping(path = { "/searchmovie/director/{director}", "/searchmovie/{director}/{rating}",
			"/searchmovie/rating/{rating}" })
	public ResponseEntity<List<MovieCatalog>> getMovieByName(
			@PathVariable(required = false, name = "director") String director,
			@PathVariable(required = false, name = "rating") Float rating) throws ResourceNotFoundException {
		List<MovieCatalog> movies = null;
		if (director == null) {
			movies = movieCatalogService.getAllByRating(rating);
		} else {
			if (rating == null) {
				movies = movieCatalogService.getAllByDirector(director);
			} else {
				movies = movieCatalogService.getAllByDirectorAndRating(director, rating);
			}
		}

		if (movies.size() <= 0) {
			throw new ResourceNotFoundException("No movies found.");
		}

		return ResponseEntity.ok().body(movies);

	}

}
