package com.learn.repository;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learn.model.Director;
@Repository
public interface DirectorRepository extends JpaRepository<Director,Integer> {
    @Query(value = "select * from director where first_name=? and last_name=?",nativeQuery = true) 
	Director getMoviesByDirectorName(String firstName, String lastName);

}
