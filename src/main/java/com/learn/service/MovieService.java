package com.learn.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learn.Exception.InvalidMovieTitle;
import com.learn.Exception.MovieIdNotFoundException;
import com.learn.model.Director;
import com.learn.model.Movie;
import com.learn.repository.DirectorRepository;
import com.learn.repository.MovieRepository;


@Service
@Profile(value = {"!dev","!prod"})
public class MovieService {

	private static final Log Logger=LogFactory.getLog(MovieService.class);
	@Autowired
	private MovieRepository movieRepo;
    @Autowired
	private DirectorRepository direpo;
	
	public String insertMovie(Movie movie) {
		
	 Movie mv=movieRepo.save(movie);
	 return "movie saved successfully";
		
	}

	public Movie fetchDetailsById(int id) {
		
		 Movie movie=movieRepo.findById(id).get();
		 return movie;
		
	}

	public Movie getMovieByMovieName(String movieTitle) throws InvalidMovieTitle {
		
		List<Movie> listmv=movieRepo.findAll();
		
		Movie movie=null;
		for(Movie mv:listmv)
		{
			if(mv.getMovieTitle().equals(movieTitle))
			{
				movie=movieRepo.getMovieDetailsWithName(movieTitle);
			}
		}
		if(movie==null)
		{
			throw new InvalidMovieTitle("Movie", "title", movieTitle);
		}
		
	return movie;	
};

	public List<Movie> findAllMovies() {
		
		List<Movie> listMovies=movieRepo.findAll();
		return listMovies;
	}
	
	
	//Pagination
	/*
	public Page<Movie> findPagination(int pageNo,int pageSize,String sortBy)
	{
		Pageable pageable=PageRequest.of(pageNo,pageSize,Sort.by(sortBy).ascending());
		Page<Movie> page=movieRepo.findAll(pageable);
		return page;
	}*/
	
	//Pagination
		public Page<Movie> findPagination(Pageable pageable)
		{
			Page<Movie> page=movieRepo.findAll(pageable);
			return page;
		}

		//Update logic in Service layer
		public Movie editMovieDetails(int id, Movie movie) throws MovieIdNotFoundException {
        
            Optional<Movie> existingId = movieRepo.findById(id);
            Logger.debug("Id found with {}:"+existingId.isPresent());
            if(existingId.isPresent())
            {
            Movie updateMv=existingId.get();
            updateMv.setMovieTitle(movie.getMovieTitle());
            updateMv.setReleasedDate(movie.getReleasedDate());
            updateMv.setRunningTime(movie.getRunningTime());
            updateMv.setActive(movie.getActive());
            movieRepo.save(updateMv);
            Logger.info("movie details updated");
            return updateMv;
            
            }
            else
            {
            	Logger.error("Exception caught");
            	throw new MovieIdNotFoundException("Movie","movieId",id);
            }
	
		}

		public String deleteMovieById(int movieId) {

            movieRepo.deleteById(movieId);
			Logger.info("movie deleted with id {}:"+movieId);
			//return "movie.deleteMessage";
			return "movie with Id "+movieId+" got deleted successfully";
		}
		
		
}
