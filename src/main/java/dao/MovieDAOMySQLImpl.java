package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Movie;

public class MovieDAOMySQLImpl extends AbstractDAOMySQLImpl<Movie> implements MovieDAO {

	private static final String SQL_INSERT = "INSERT INTO movies (title, url, url_thumbnail, url_player,slug,summary, rating) VALUES (?,1,2,3,4,5,6)";
	private static final String SQL_SEARCH_IN_SUMMARY = "SELECT * FROM movies WHERE summary LIKE '%?%'";
	
	private PreparedStatement insertStmt;
	
	public int save(Connection connection, Movie object) {
        validateConnection(connection);
        if (object != null) {
                int recordsAffected = 0;

                if (object.isNew()) {
                        recordsAffected = insert(connection, object);
                } else {
                        recordsAffected = update(connection, object);
                }
                return recordsAffected;
        } else
                throw new IllegalArgumentException("Movie darf nicht null sein!");
	}

	public int delete(Connection connection, Movie object) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Movie findById(Connection connection, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Movie> findAll(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Movie> findByTitle(Connection con, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Movie> searchInSummary(Connection con, String search) {
	
        return null;
	}

	@Override
	protected int update(Connection connection, Movie entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int insert(Connection connection, Movie entity) {
		validateConnection(connection);
		//validateMovie(entity);
		
		int recordsAffected = 0;
		Date now = new Date();
		ResultSet generatedKeys = null;
		
		try {
			if (insertStmt == null) {
				insertStmt = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			}
			//insertStmt.setTimestamp(1, JDBCHelper.toSqlTimestamp(now));
			insertStmt.setString(1, entity.getTitle());
			
			recordsAffected = insertStmt.executeUpdate();
			generatedKeys = insertStmt.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				long currentId = generatedKeys.getLong(1);
				entity.setId(currentId);
				entity.setLastModifiedTimestamp(now);
			}
		}
		catch(SQLException e) {
			throw new DAOException("Movie could not be inserted!");
		}
		finally {
			closeResultSet(generatedKeys);
		}
		return recordsAffected;
	}

}
