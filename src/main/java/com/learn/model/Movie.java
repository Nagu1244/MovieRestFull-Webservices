package com.learn.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	@Id
	@Column(name = "Movie_Id")
	private int movieId;

	@Pattern(regexp = "^[A-Za-z0-9? ,_-]+$",message = "Please enter Movie name as String or Alphanumeric")//Allows Alphanumeric
	//@NotEmpty(message = "movie name not be empty")^[a-zA-Z0-9]+$
	private String movieTitle;
    
	
	@FutureOrPresent(message = "Please enter the date as present or future date in the format yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "Date_Released")
	private Date releasedDate;
    
	@NotNull(message = "Time should not be null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss a")
	@Column(name = "Movie_Running_Time")
	private LocalTime runningTime;
    
	private Boolean active;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
	@JoinColumn(name="directorId")
	private Director director;
    
	
	    public Director getDirector() {
		return director;
	}


	public void setDirector(Director director) {
		this.director = director;
	}


		public Movie(int movieId, String movieTitle, Date releasedDate, LocalTime runningTime,Boolean active,Director director) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.releasedDate = releasedDate;
		this.runningTime = runningTime;
		this.active=active;
		this.director=director;
	}

	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Movie() {

	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Date getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	public LocalTime getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(LocalTime runningTime) {
		this.runningTime = runningTime;
	}

}
