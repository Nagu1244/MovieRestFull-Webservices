package com.learn.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learn.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
      
	 @Query(value ="SELECT * FROM movie where movie_title=?1",nativeQuery = true)
	 public Movie getMovieDetailsWithName(String movieTitle);

}
