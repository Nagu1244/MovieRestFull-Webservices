package com.learn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.model.Director;
import com.learn.model.Movie;
import com.learn.repository.DirectorRepository;
import com.learn.repository.MovieRepository;

@Service
public class DirectorService {

	@Autowired
	private DirectorRepository direpo;
	
	
	public String createDirector(Director director) {
		try
		{
		Director di=direpo.save(director);
		
		return "Director  is created successfully";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Director id with "+director.getDirectorId()+" is created successfully";
	}

	public List<String> getMoviesByDirectorName(String firstName, String lastName) {
		
		List<String> list=new ArrayList<>();
		Director director=direpo.getMoviesByDirectorName(firstName,lastName);
		List<Movie> mv=director.getMovie();
		List<String> fromMv=mv.stream().map(name->name.getMovieTitle()).collect(Collectors.toList());
		for(String names:fromMv)
		{
			list.add(names);
		}
		return list;
	}

	public String deleteDirector(Integer directorId) {

          direpo.deleteById(directorId);
          return "record deleted with Director Id "+directorId+" successfully";
		
	}

}
