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
		Tag t1 = new Tag();
		t1.setTitle("Foto");
		Assert.assertEquals(1, dao.save(connection, t1));
	}

	@Test
	public void findById() throws SQLException, ClassNotFoundException {
		Tag t = dao.findById(connection, 1L);
		Assert.assertEquals("Foto", t.getTitle());
	}
	
	@Test
	public void findAll() throws SQLException, ClassNotFoundException {
		List<Tag> tags = dao.findAll(connection);
		Assert.assertEquals("Foto", tags.get(0).getTitle());
	}


	@Test
	public void findBySlug() throws SQLException, ClassNotFoundException {
		List<Tag> tags = dao.findBySlug(connection, "Foto");
		Assert.assertEquals("Foto", tags.get(0).getTitle());
	}

	
	@Test
	public void saveUpdate() throws SQLException, ClassNotFoundException {
		Tag t = dao.findById(connection, 1L);
		t.setTitle("Foto2");
		dao.save(connection, t);
		Tag t2 = dao.findById(connection, 1L);
		Assert.assertEquals("Foto2", t2.getTitle().toString());
	}
	
/*
	 @Test public void delete() throws SQLException, ClassNotFoundException {
	 // Datensatz mit ID 2 muss angelegt worden sein Movie m =
	 Tag t = dao.findById(connection, 1L);
	 Assert.assertEquals("Foto2", t.getTitle());
	 dao.delete(connection, t); 
	 }
*/
}
