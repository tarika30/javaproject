package com.kukri.sports;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kukri.sports.dao.MovieCatalogDao;
import com.kukri.sports.model.MovieCatalog;
import com.kukri.sports.service.MovieCatalogService;

@RunWith(SpringRunner.class)
@SpringBootTest
class KukriApplicationServiceTests {



	@Autowired
	private MovieCatalogService service;

	@MockBean
	private MovieCatalogDao movieDao;

	@Test
	public void getMovieCatlogTest() {
		when(movieDao.findAll()).thenReturn(Stream
				.of(new MovieCatalog(234, "Interstellar", "Christopher Nolan", (float) 9.0),
						new MovieCatalog(236, "The Knight Rises", "Christopher Nolan", (float) 9.1))
				.collect(Collectors.toList()));
		assertEquals(2, service.findAll().size());
	}

	@Test
	public void deleteMovieTest() {
		MovieCatalog movie = new MovieCatalog(234, "Interstellar", "Christopher Nolan", (float) 9.0);
		service.delete(movie);
		verify(movieDao,times(1)).delete(movie);
		
		
	}
	
	@Test
	public void saveMovieTest() {
		MovieCatalog movie = new MovieCatalog(234, "Interstellar", "Christopher Nolan", (float) 9.0);
		when(movieDao.save(movie)).thenReturn(movie);
		assertEquals(movie, service.saveMovie(movie));
	}
	
}
