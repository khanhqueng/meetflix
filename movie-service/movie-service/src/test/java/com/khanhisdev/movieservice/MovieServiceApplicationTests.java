package com.khanhisdev.movieservice;

import com.khanhisdev.movieservice.controller.MovieController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class MovieServiceApplicationTests {

	@Autowired
	private MovieController movieController;
	@Test
	void contextLoads() {
		assertThat(movieController).isNotNull();
	}

}
