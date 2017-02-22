package eleks.com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySQL {
	

	public Connection connectMySQL() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/selenium", "root", "password");
		return connection;
	}
	
	public ResultSet executionQuery() throws ClassNotFoundException, SQLException {
		Statement statement = connectMySQL().createStatement();
		ResultSet query = statement.executeQuery("select * from loginiua order by login desc limit 1");
		return query;
	}
	
	public String getUserNameSQL() throws SQLException, ClassNotFoundException {
		String login = null;
		ResultSet query = executionQuery();
		while (query.next()) {
			login = query.getString("login");
		}
		return login;
	}

	public String getUserPassSQL() throws SQLException, ClassNotFoundException {
		String password = null;
		ResultSet query = executionQuery();
		while (query.next()) {
			password = query.getString("password");
		}
		return password;
	}

}
