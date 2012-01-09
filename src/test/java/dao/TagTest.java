package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import model.*;

import org.junit.Before;
import org.junit.Test;

public class TagTest {

	protected static Connection connection;
	private TagDAOMySQLImpl dao = new TagDAOMySQLImpl();

	@Before
	public void setUp() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/fdb", "root", "root");
	}

	@Test
	public void saveInsert() throws SQLException, ClassNotFoundException {
		Tag t1 = new Tag("foto");
		Assert.assertEquals(1, dao.save(connection, t1));
	}

	/*
	@Test
	public void findById() throws SQLException, ClassNotFoundException {
		Movie m = dao.findById(connection, 1L);
		Assert.assertEquals("Testeintrag", m.getTitle());
		Assert.assertEquals("http://bla.at/video.flv", m.getUrl());
	}

	@Test
	public void findAll() throws SQLException, ClassNotFoundException {
		List<Movie> movies = dao.findAll(connection);
		Assert.assertEquals("Testeintrag", movies.get(0).getTitle());
	}

	@Test
	public void searchInSummary() throws SQLException, ClassNotFoundException {
		List<Movie> movies = dao.searchInSummary(connection, "Ich bin Text");
		Assert.assertEquals("Testeintrag", movies.get(0).getTitle());
	}

	@Test
	public void findByTitle() throws SQLException, ClassNotFoundException {
		List<Movie> movies = dao.findByTitle(connection, "Testeintrag");
		Assert.assertEquals("Testeintrag", movies.get(0).getTitle());
	}

	@Test
	public void saveUpdate() throws SQLException, ClassNotFoundException {
		Movie m = dao.findById(connection, 1L);
		m.setRating(5);
		dao.save(connection, m);
		Movie m2 = dao.findById(connection, 1L);
		Assert.assertEquals("5", m2.getRating().toString());
	}
*/

	/*
	 * @Test public void delete() throws SQLException, ClassNotFoundException {
	 * // Datensatz mit ID 2 muss angelegt worden sein Movie m =
	 * dao.findById(connection, 2L);
	 * Assert.assertEquals("Testeintrag zum loeschen", m.getTitle());
	 * dao.delete(connection, m); }
	 */
}
