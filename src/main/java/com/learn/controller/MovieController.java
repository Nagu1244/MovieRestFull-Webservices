package com.learn.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.Exception.InvalidMovieTitle;
import com.learn.Exception.MovieIdNotFoundException;
import com.learn.model.Movie;
import com.learn.service.MovieService;

@RestController
@RequestMapping("/movie")
@Validated
public class MovieController {

	private static final Log Logger = LogFactory.getLog(MovieController.class);
	@Autowired
	private MovieService movieService;
	@Autowired
	private RestTemplate template;
	@Autowired
	private MessageSource source;//dependency for i18n
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(path = "/save")
	public ResponseEntity<String> createMovie(@Valid @RequestBody Movie movie) {
		String m=movieService.insertMovie(movie);
		Logger.info("Added {}:" + movie.getClass());
		return new ResponseEntity<>(m, HttpStatus.CREATED);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> listMovies = movieService.findAllMovies();
		ResponseEntity<List<Movie>> responseEntity = new ResponseEntity<List<Movie>>(listMovies, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/{id}")
	public EntityModel<Movie> findMovieById(@PathVariable("id") int id) {

		// Implementing Hateos
		EntityModel<Movie> resource = EntityModel.of(movieService.fetchDetailsById(id));
		Logger.debug("Hatoes URI called:" + resource);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllMovies());
		resource.add(linkTo.withRel("all-movies"));
		return resource;

	}

	@GetMapping("/movieName")
	public EntityModel<Movie> getMovieDetailsByName(
			@RequestParam(name = "movieTitle") @NotEmpty(message = "Movie name not be empty") String movieTitle) throws InvalidMovieTitle {
		// HATEOS
		Movie movie = movieService.getMovieByMovieName(movieTitle);
		EntityModel<Movie> modelEntity = EntityModel.of(movie);
		WebMvcLinkBuilder linkBUilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllMovies());
		modelEntity.add(linkBUilder.withRel("list of movies"));
		return modelEntity;

	}

	// Pagination user defined
	/*
	@GetMapping
	public Page<Movie> findPagination(@RequestParam(name = "pageNo") int pageNo,
			@RequestParam(name = "pageSize") int pageSize, @RequestParam(name = "sortBy") String sortBy)
	{
		Page<Movie> paged = movieService.findPagination(pageNo, pageSize, sortBy);
		return paged;
	}
	*/

	// Pagination Custom Defined two properties in application.properties file
	@GetMapping
	public Page<Movie> findPagination(Pageable pageable) {
		Page<Movie> paged = movieService.findPagination(pageable);
		return paged;
	}
     
	//Update URI
	@PutMapping(value = "/update/{id}", consumes = "application/json")
	public ResponseEntity<Movie> updateMovieDetails(@Valid @PathVariable(name = "id") int id,
			@Valid @RequestBody Movie movie) throws MovieIdNotFoundException {
		Logger.debug("updatemovieDetails controller called");
		Movie message = movieService.editMovieDetails(id, movie);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(message);// New way to write
	}
     
	//i18n Support for localization
	@DeleteMapping(value = "/delete/{id}")
	public String deleteMovieById(@PathVariable(name = "id") int movieId)
	{
		return movieService.deleteMovieById(movieId) ;
		
	}
	//Get Movies by Director name
	
	
	
}
