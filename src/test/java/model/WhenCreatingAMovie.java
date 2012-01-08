package model;

import org.junit.Before;

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

}
