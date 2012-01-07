package dao;

public class JDBCHelper {

	  public static java.sql.Date toSqlDate(java.util.Date date) {
	    return new java.sql.Date(date.getTime());
	  }

	  public static java.sql.Time toSqlTime(java.util.Date date) {
	    return new java.sql.Time(date.getTime());
	  }

	  public static java.sql.Timestamp toSqlTimestamp(java.util.Date date) {
	    return new java.sql.Timestamp(date.getTime());
	  }

	  public static java.util.Date toDate(java.sql.Date date) {
	    return new java.util.Date(date.getTime());
	  }

	  public static java.util.Date toDate(java.sql.Time time) {
	    return new java.util.Date(time.getTime());
	  }

	  public static java.util.Date toDate(java.sql.Timestamp timestamp) {
	    return new java.util.Date(timestamp.getTime());
	  }

	}
