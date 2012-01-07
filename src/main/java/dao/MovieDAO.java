package dao;

import java.sql.Connection;
import java.util.List;
import model.Movie;


public interface MovieDAO {
	int save(Connection connection, Movie movie);
	int delete(Connection connection, Movie movie);
	Movie findById(Connection connection, Long id);
	List<Movie> findAll(Connection con);
}
