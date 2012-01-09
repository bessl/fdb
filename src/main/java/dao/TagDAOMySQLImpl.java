package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Tag;

public class TagDAOMySQLImpl extends AbstractDAOMySQLImpl<Tag> implements
		TagDAO {

	private static final String SQL_INSERT = "INSERT INTO tags (title, slug, updated_at) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE tags SET title=?, slug=?, updated_at=? WHERE id=?";
	private static final String SQL_SELECT_BY_ID = "SELECT id, title, slug, updated_at FROM tags WHERE id = ?";
	private static final String SQL_SELECT_BY_SLUG = "SELECT id, title, slug, updated_at FROM tags WHERE slug = ?";
	private static final String SQL_SELECT_ALL = "SELECT id, title, slug, updated_at FROM tags";
	private static final String SQL_DELETE = "DELETE FROM tags WHERE id = ?";

	private PreparedStatement insertStmt = null;
	private PreparedStatement updateStmt = null;
	private PreparedStatement selectByIdStmt = null;
	private PreparedStatement selectBySlugStmt = null;
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
		int recordsAffected = 0;
		validateConnection(connection);

		if (object != null) {
			try {
				if (deleteStmt == null) {
					deleteStmt = connection.prepareStatement(SQL_DELETE);
				}
				deleteStmt.setLong(1, object.getId());
				recordsAffected = deleteStmt.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("Tag konnte nicht geloescht werden!", e);
			}
		} else
			throw new DAOException("Tag muss vorhanden sein zum loeschen!");
		return recordsAffected;
	}
	
	public Tag findById(Connection connection, Long id) {
		validateConnection(connection);
		Tag foundTag = null;

		if (id != null) {
			try {
				if (selectByIdStmt == null) {
					selectByIdStmt = connection
							.prepareStatement(SQL_SELECT_BY_ID);
				}

				selectByIdStmt.setLong(1, id);
				ResultSet rs = selectByIdStmt.executeQuery();

				while (rs.next()) {
					foundTag = new Tag();
					foundTag.setId(rs.getLong("id"));
					foundTag.setTitle(rs.getString("title"));
					foundTag.setLastModifiedTimestamp(JDBCHelper.toDate(rs
							.getTimestamp("updated_at")));
				}

				rs.close();
			} catch (SQLException e) {
				throw new DAOException("Tag nicht gefunden!", e);
			}
		} else
			throw new DAOException("Keine ID angegeben!");

		return foundTag;
	}
	
	public List<Tag> findAll(Connection connection) {
		validateConnection(connection);
		List<Tag> foundTags = new ArrayList<Tag>();
		Tag foundTag = null;
		try {
			if (selectAllStmt == null) {
				selectAllStmt = connection.prepareStatement(SQL_SELECT_ALL);
			}
			ResultSet rs = selectAllStmt.executeQuery();

			while (rs.next()) {
				foundTag = new Tag();
				foundTag.setId(rs.getLong("id"));
				foundTag.setTitle(rs.getString("title"));
				foundTag.setLastModifiedTimestamp(JDBCHelper.toDate(rs
						.getTimestamp("updated_at")));
				foundTags.add(foundTag);
			}
			rs.close();
		} catch (SQLException e) {
			throw new DAOException("Tags koennen nicht gelesen werden!", e);
		}
		return foundTags;

	}
	public List<Tag> findBySlug(Connection connection, String title) {
		validateConnection(connection);
		List<Tag> foundTags = new ArrayList<Tag>();
		Tag foundTag = null;
		try {
			if (selectBySlugStmt == null) {
				selectBySlugStmt = connection
						.prepareStatement(SQL_SELECT_BY_SLUG);
			}

			selectBySlugStmt.setString(1, title);
			ResultSet rs = selectBySlugStmt.executeQuery();

			while (rs.next()) {
				foundTag = new Tag();
				foundTag.setId(rs.getLong("id"));
				foundTag.setTitle(rs.getString("title"));
				foundTag.setLastModifiedTimestamp(JDBCHelper.toDate(rs
						.getTimestamp("updated_at")));
				foundTags.add(foundTag);
			}
			rs.close();
		} catch (SQLException e) {
			throw new DAOException("Tag konnte nicht gelesen werden!", e);
		}
		return foundTags;
	}
	
	@Override
	protected int update(Connection connection, Tag entity) {
		int recordsAffected = 0;
		Date now = new Date();

		try {
			if (updateStmt == null) {
				updateStmt = connection.prepareStatement(SQL_UPDATE);
			}
			// title, slug
			updateStmt.setString(1, entity.getTitle());
			updateStmt.setString(2, entity.getSlug());
			updateStmt.setTimestamp(3, JDBCHelper.toSqlTimestamp(now));
			updateStmt.setLong(4, entity.getId());

			recordsAffected = updateStmt.executeUpdate();

			entity.setLastModifiedTimestamp(now);
		} catch (SQLException e) {
			throw new DAOException("Movie konnte nicht geaendert werden!", e);
		}
		return recordsAffected;
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
