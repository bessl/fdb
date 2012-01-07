package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ModelBase;

public abstract class AbstractDAOMySQLImpl<T extends ModelBase> implements BaseDAO<T> {

	public int save(Connection connection, T entity) {
		if(!entity.isDirty()) {
			return 1;
		}
		validateConnection(connection);
		return entity.isNew() ? insert(connection, entity) : update(connection, entity);
	}
	
	protected abstract int update(Connection connection, T entity);
	
	protected abstract int insert(Connection connection, T entity);
	
	protected final void validateConnection(Connection connection) 
	{
		if(connection == null) {
			throw new DAOException("Connection darf nicht null sein!");
		}
		try {
			if(connection.isClosed() || !connection.isValid(250)) {
				throw new DAOException("Connection ist geschlossen oder ungueltig!");
			}
		}
		catch (SQLException e) {
			throw new DAOException("Connection kann nicht ueberprueft werden!", e);
		}
	}
	
	protected final void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}
			catch (SQLException ignored) {
				// wird ignoriert
			}
		}
	}
}

