package dbutil;

import java.sql.*;
public class SQLHelper {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/cloudresource";
	private static String user = "root", pwd = "Dd223322";
	private static Connection con;
    
	static {
		try {
			Class.forName(driver);
		} catch (Exception ex) {
ex.printStackTrace();
		}
	}
    
	public static void executeUpdate(String sql) {
		try {
			con = DriverManager.getConnection(url, user, pwd);
			Statement cmd = con.createStatement();
			cmd.executeUpdate(sql);
			con.close();
} catch (Exception ex) {
 ex.printStackTrace();

		}
	}

	public static ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			Statement cmd = con.createStatement();
			rs = cmd.executeQuery(sql);
		} catch (Exception ex) { 
ex.printStackTrace();
		}
		return rs;
	}

public static void closeConnection() {
		try {
           if(!con.isClosed())
			con.close();
		  } catch (Exception ex) {
		}
	}

}