package com.learn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.learn.controller.MovieController;
import com.learn.model.Movie;
import com.learn.repository.MovieRepository;
import com.learn.service.MovieService;

@SpringBootTest
public class MovieControllerTest {
	
	@Test
	public void createMovieTest()
	{
		
		
	}
}
