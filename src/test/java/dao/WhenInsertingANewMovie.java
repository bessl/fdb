package dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Movie;
import model.Tag;

import org.junit.Test;

public class WhenInsertingANewMovie {

	protected static Connection connection;
	private MovieDAOMySQLImpl dao = new MovieDAOMySQLImpl();
	
	@Test
	public void tagTitleShouldSetAURLAutomatically() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fdb",
				"root", "root");
		
		Movie m1 = new Movie();
		m1.setTitle("xx111xx");

		dao.save(connection, m1);
			
	}
	
}
