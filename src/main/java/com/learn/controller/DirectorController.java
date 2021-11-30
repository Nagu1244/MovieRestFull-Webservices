package com.learn.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.model.Director;
import com.learn.service.DirectorService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/director")
@OpenAPIDefinition(info = @Info(title = "Controller layer for Director and Movies related operations"))
public class DirectorController {

	@Autowired
	private DirectorService diService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addDirector(@RequestBody Director director)
	{
		String s=diService.createDirector(director);
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
	}
	
	//Swagger annotation
	@ApiResponse(description = "Get method for calling the list of movies")
	@GetMapping
	public List<String> getMoviesByDirectorName(@RequestParam(name = "firstName") String firstName,
		                                        @RequestParam(name = "lastName") String lastName)
	{
		List<String> movieNames=diService.getMoviesByDirectorName(firstName,lastName);
		return movieNames;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteDirectorById(@PathVariable("id") Integer directorId)
	{
		String msg=diService.deleteDirector(directorId);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
}
