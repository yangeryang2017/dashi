package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLImport {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = null;

			try {
				System.out.println("Connecting to \n" + DBUtil.MYSQL_URL);
				conn = DriverManager.getConnection(DBUtil.MYSQL_URL);
			} catch (SQLException e) {
				System.out.println("SQLException " + e.getMessage());
				System.out.println("SQLState " + e.getSQLState());
				System.out.println("VendorError " + e.getErrorCode());
			}
			if (conn == null) {
				return;
			}
			Statement stmt = conn.createStatement();
			
			//Step 1. Drop tables if exist. 
			String sql = "DROP TABLE IF EXISTS history";
			stmt.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS restaurants";
			stmt.executeUpdate(sql);
			
			sql = "DROP TABLE IF EXISTS users";
			stmt.executeUpdate(sql);
			
			//Step 2. Create new tables. 
			sql = "CREATE TABLE restaurants "
					+ "(business_id VARCHAR(255) NOT NULL, "
					+ " name VARCHAR(255), " + "categories VARCHAR(255), "
					+ "city VARCHAR(255), " + "state VARCHAR(255), "
					+ "stars FLOAT," + "full_address VARCHAR(255), "
					+ "latitude FLOAT, " + " longitude FLOAT, "
					+ "image_url VARCHAR(255),"
					+ "url VARCHAR(255),"
					+ " PRIMARY KEY ( business_id ))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE users "
					+ "(user_id VARCHAR(255) NOT NULL, "
					+ " password VARCHAR(255) NOT NULL, "
					+ " first_name VARCHAR(255), last_name VARCHAR(255), "
					+ " PRIMARY KEY ( user_id ))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE history "
					+ "(visit_history_id bigint(20) unsigned NOT NULL AUTO_INCREMENT, "
					+ " user_id VARCHAR(255) NOT NULL , "
					+ " business_id VARCHAR(255) NOT NULL, " 
					+ " last_visited_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, "
					+ " PRIMARY KEY (visit_history_id),"
					+ "FOREIGN KEY (business_id) REFERENCES restaurants(business_id),"
					+ "FOREIGN KEY (user_id) REFERENCES users(user_id))";
			stmt.executeUpdate(sql);

			//Step 3. Insert a fake user
			sql = "INSERT INTO users " + "VALUES (\"1111\", \"3229c1097c00d497a0fd282d586be050\", \"John\", \"Smith\")";
			stmt.executeUpdate(sql);

			/*
			sql = "DROP TABLE IF EXISTS USER_CATEGORY_HISTORY";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE USER_CATEGORY_HISTORY "
					+ "(category_id bigint(20) unsigned NOT NULL AUTO_INCREMENT, "
					+ " first_id VARCHAR(255) NOT NULL , "
					+ " second_id VARCHAR(255) NOT NULL, "
					+ " count bigint(20) NOT NULL, "
					+ " PRIMARY KEY (category_id))";
			stmt.executeUpdate(sql);
			*/

			System.out.println("Done DBYelp Importing");

		} catch (Exception e) { /* report an error */
			System.out.println(e.getMessage());
		}
	}
}
