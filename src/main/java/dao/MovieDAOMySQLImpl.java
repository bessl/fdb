package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Movie;

public class MovieDAOMySQLImpl extends AbstractDAOMySQLImpl<Movie> implements MovieDAO {

	private static final String SQL_INSERT = "INSERT INTO movies (title, url, url_thumbnail, url_player,slug,summary, rating, updated_at) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT_BY_ID = "SELECT id, title, url, url_thumbnail, url_player,slug,summary, rating, updated_at FROM movies WHERE id = ?";
	private static final String SQL_SELECT_ALL = "SELECT id, title, url, url_thumbnail, url_player,slug,summary, rating, updated_at FROM movies";
	private static final String SQL_DELETE = "DELETE FROM movies WHERE id = ?";
	
	private PreparedStatement insertStmt = null;
	private PreparedStatement selectByIdStmt = null;
	private PreparedStatement selectAllStmt = null;
	private PreparedStatement deleteStmt = null;
	
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
        int recordsAffected = 0;
        validateConnection(connection);

        if (object != null){
        try {
                if (deleteStmt == null) {
                        deleteStmt = connection.prepareStatement(SQL_DELETE);
            }
            deleteStmt.setLong(1, object.getId());
            recordsAffected = deleteStmt.executeUpdate();
        } catch (SQLException e) {
                throw new DAOException("Movie nicht geloescht!", e);
        }
        } else throw new DAOException("Movie muss vorhanden sein zum loeschen!");
        return recordsAffected;
	}

	public Movie findById(Connection connection, Long id) {
		validateConnection(connection);
		Movie foundMovie = null;

		if (id != null) {
			try {
				if (selectByIdStmt == null) {
					selectByIdStmt = connection
							.prepareStatement(SQL_SELECT_BY_ID);
				}

				selectByIdStmt.setLong(1, id);
				ResultSet rs = selectByIdStmt.executeQuery();

				while (rs.next()) {
					foundMovie = new Movie();
					foundMovie.setId(rs.getLong("id"));
					foundMovie.setTitle(rs.getString("title"));
					foundMovie.setUrl(rs.getString("url"));
					foundMovie.setUrlThumbnail(rs.getString("url_thumbnail"));
					foundMovie.setUrlPlayer(rs.getString("url_player"));
					foundMovie.setSummary(rs.getString("summary"));
					foundMovie.setRating(rs.getInt("rating"));
					foundMovie.setLastModifiedTimestamp(JDBCHelper.toDate(rs.getTimestamp("updated_at")));
				}

				rs.close();
			} catch (SQLException e) {
				throw new DAOException("Movie nicht gefunden!", e);
			}
		} else
			throw new DAOException("Keine ID angegeben!");

		return foundMovie;
	}

	public List<Movie> findAll(Connection connection) {
		validateConnection(connection);
        List<Movie> foundMovies = new ArrayList<Movie>();
        Movie foundMovie = null;
        try {
                if (selectAllStmt == null) {
                        selectAllStmt = connection.prepareStatement(SQL_SELECT_ALL);
                }
                ResultSet rs = selectAllStmt.executeQuery();

                while (rs.next()) {
					foundMovie = new Movie();
					foundMovie.setId(rs.getLong("id"));
					foundMovie.setTitle(rs.getString("title"));
					foundMovie.setUrl(rs.getString("url"));
					foundMovie.setUrlThumbnail(rs.getString("url_thumbnail"));
					foundMovie.setUrlPlayer(rs.getString("url_player"));
					foundMovie.setSummary(rs.getString("summary"));
					foundMovie.setRating(rs.getInt("rating"));
					foundMovie.setLastModifiedTimestamp(JDBCHelper.toDate(rs.getTimestamp("updated_at")));
					foundMovies.add(foundMovie);
                }
                rs.close();
        } catch (SQLException e) {
                throw new DAOException("Movie nicht gelesen!", e);
        }
        return foundMovies;

	}

	public List<Movie> findByTitle(Connection connection, String title) {
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
			insertStmt.setString(1, entity.getTitle());
			insertStmt.setString(2, entity.getUrl());
			insertStmt.setString(3, entity.getUrlThumbnail());
			insertStmt.setString(4, entity.getUrlPlayer());
			insertStmt.setString(5, entity.getSlug());
			insertStmt.setString(6, entity.getSummary());
			insertStmt.setInt(7, entity.getRating());
			insertStmt.setTimestamp(8, JDBCHelper.toSqlTimestamp(now));
			
			recordsAffected = insertStmt.executeUpdate();
			generatedKeys = insertStmt.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				long currentId = generatedKeys.getLong(1);
				entity.setId(currentId);
				entity.setLastModifiedTimestamp(now);
			}
		}
		catch(SQLException e) {
			throw new DAOException("Movie konnte nicht angelegt werden!" + e);
		}
		finally {
			closeResultSet(generatedKeys);
		}
		return recordsAffected;
	}

}
