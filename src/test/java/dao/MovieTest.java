package dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import model.Movie;
import model.Tag;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {

	protected static Connection connection;
	private MovieDAOMySQLImpl dao = new MovieDAOMySQLImpl();
	
	@Before
	public void setUp() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fdb",
				"root", "root");
	}
	
	@Test
	public void saveInsert() throws SQLException, ClassNotFoundException {
		Movie m1 = new Movie();
		m1.setTitle("Testeintrag");
		m1.setUrl("http://bla.at/video.flv");
		m1.setUrlThumbnail("http://bla.at/video2.flv");
		m1.setUrlPlayer("http://bla.at/video3.flv");
		m1.setSummary("Ich bin Text");
		m1.setRating(2);
		Movie m2 = new Movie();
		m2.setTitle("Testeintrag zum loeschen");
		
		Assert.assertEquals(1, dao.save(connection, m1));
		Assert.assertEquals(1, dao.save(connection, m2));
	}
	
	@Test
	public void findById() throws SQLException, ClassNotFoundException {
		Movie m =  dao.findById(connection, 1L);
		Assert.assertEquals("Testeintrag", m.getTitle());
		Assert.assertEquals("http://bla.at/video.flv", m.getUrl());
	}	
	
	@Test
	public void findAll() throws SQLException, ClassNotFoundException {
		List<Movie> movies =  dao.findAll(connection);
		Assert.assertEquals("Testeintrag", movies.get(0).getTitle());
	}	
	
	/*
	@Test
	public void delete() throws SQLException, ClassNotFoundException {
		// Datensatz mit ID 2 muss angelegt worden sein
		Movie m =  dao.findById(connection, 2L);
		Assert.assertEquals("Testeintrag zum loeschen", m.getTitle());
		dao.delete(connection, m);
	}	
	*/
}
