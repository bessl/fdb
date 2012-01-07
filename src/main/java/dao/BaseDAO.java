package dao;

import java.sql.Connection;
import java.util.List;
import model.ModelBase;

public interface BaseDAO<T extends ModelBase> {
	int save(Connection connection, T object);
	int delete(Connection connection, T object);
	T findById(Connection connection, Long id);
	List<T> findAll(Connection con);
}
