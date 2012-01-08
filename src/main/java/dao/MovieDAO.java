package dao;

import java.sql.Connection;
import java.util.List;
import model.Movie;

public interface MovieDAO extends BaseDAO<Movie> {
	public List<Movie> findByTitle(Connection connection, String title);

	public List<Movie> searchInSummary(Connection connection, String search);
}