package model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WhenCreatingAMovie {

	private Movie m1, m2, m3;

	@Before
	public void setUp() throws Exception {
		m1 = new Movie();
		m1.setTitle("bla");
		m2 = new Movie();
		m2.setTitle("bla 2");
		m3 = new Movie();
		m3.setTitle("bla 3");

	}

	@Test
	public void addMoviesToPlaylist() {
		Playlist pl = new Playlist("Meine neue Playlist");
		pl.addMovie(m1);
		pl.addMovie(m2);
		pl.addMovie(m3);
		
		assertEquals(3, pl.getMovies().size());
	}

}
