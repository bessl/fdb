package dao;

import java.sql.Connection;
import java.util.List;
import model.Tag;

public interface TagDAO extends BaseDAO<Tag> {
	public List<Tag> findBySlug(Connection connection, String slug);
}