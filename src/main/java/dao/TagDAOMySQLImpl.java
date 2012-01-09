package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Movie;
import model.Tag;

public class TagDAOMySQLImpl extends AbstractDAOMySQLImpl<Tag> implements
		TagDAO {

	private static final String SQL_INSERT = "INSERT INTO tags (title, slug, updated_at) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE tags SET title=?, url=?, url_thumbnail=?, url_player=?,slug=?,summary=?, rating=?, updated_at=? WHERE id=?";
	private static final String SQL_SELECT_BY_ID = "SELECT id, title, url, url_thumbnail, url_player,slug,summary, rating, updated_at FROM tags WHERE id = ?";
	private static final String SQL_SELECT_BY_TITLE = "SELECT id, title, url, url_thumbnail, url_player,slug,summary, rating, updated_at FROM tags WHERE title = ?";
	private static final String SQL_SELECT_ALL = "SELECT id, title, url, url_thumbnail, url_player,slug,summary, rating, updated_at FROM tags";
	private static final String SQL_DELETE = "DELETE FROM tags WHERE id = ?";

	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt = null;
	private PreparedStatement selectByIdStmt = null;
	private PreparedStatement selectByTitleStmt = null;
	private PreparedStatement selectAllStmt = null;
	private PreparedStatement deleteStmt = null;
	
	public int save(Connection connection, Tag object) {
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
			throw new IllegalArgumentException("Tag darf nicht null sein!");
	}
	
	public int delete(Connection connection, Tag object) {
		// TODO Auto-generated method stub
		return 0;
	}
	public Tag findById(Connection connection, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Tag> findAll(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Tag> findBySlug(Connection connection, String title) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected int update(Connection connection, Tag entity) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected int insert(Connection connection, Tag entity) {
		validateConnection(connection);

		int recordsAffected = 0;
		Date now = new Date();
		ResultSet generatedKeys = null;

		try {
			if (insertStmt == null) {
				insertStmt = connection.prepareStatement(SQL_INSERT,
						PreparedStatement.RETURN_GENERATED_KEYS);
			}
			insertStmt.setString(1, entity.getTitle());
			insertStmt.setString(2, entity.getSlug());
			insertStmt.setTimestamp(3, JDBCHelper.toSqlTimestamp(now));

			recordsAffected = insertStmt.executeUpdate();
			generatedKeys = insertStmt.getGeneratedKeys();

			if (generatedKeys.next()) {
				long currentId = generatedKeys.getLong(1);
				entity.setId(currentId);
				entity.setLastModifiedTimestamp(now);
			}
		} catch (SQLException e) {
			throw new DAOException("Tag konnte nicht angelegt werden!" + e);
		} finally {
			closeResultSet(generatedKeys);
		}
		return recordsAffected;
	}


}
