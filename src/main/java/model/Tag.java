package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tag extends ModelBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Connection con;
	private String title;
	private String slug;

	public Tag(String title) {
		setTitle(title);
		setSlug();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("Tag hat keinen Titel");
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	private void setSlug() {
		slug = Helpers.slugify(getTitle());
	}

	// create or update db query
	public void save() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fdb",
				"root", "root");

		String query = "SELECT COUNT(*) as total FROM tags WHERE slug = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, getSlug());
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		if (rs.getInt("total") == 1) {
			// update
			PreparedStatement preparedStatement = con.prepareStatement(
					"UPDATE tags SET title = ?, slug = ? WHERE slug = ?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, getTitle());
			preparedStatement.setString(2, getSlug());
			preparedStatement.setString(3, getSlug());
			preparedStatement.executeUpdate();
		} else {
			// insert
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO tags (title, slug) VALUES (?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, getTitle());
			preparedStatement.setString(2, getSlug());
			preparedStatement.executeUpdate();
		}
	}
}
