package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class WhenSavingANewTag {

	private Tag tag1;
	String random_str = UUID.randomUUID().toString();

	private static Connection con;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fdb",
				"root", "root");
		assertNotNull(con);
		assertFalse(con.isClosed());
		con.setAutoCommit(false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (con != null && !con.isClosed()) {
			con.close();
		}
		con = null;
	}

	@Test
	public void savingANewObject() throws Exception {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT count(*) as total FROM tags");
		rs.next();
		int total_pre = rs.getInt("total");

		tag1 = new Tag(random_str);
		tag1.save();

		ResultSet rs2 = stmt.executeQuery("SELECT count(*) as total FROM tags");
		rs2.next();
		int total_post = rs2.getInt("total");

		assertEquals(total_pre, (total_post - 1));

		String query = "SELECT COUNT(*) as total FROM tags WHERE title = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, random_str);
		ResultSet rs3 = pstmt.executeQuery();
		rs3.next();

		assertEquals(1, rs3.getInt("total"));

	}

}
