package com.neaea_exam_admin.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A connection provider class
 * 
 * @author misgana
 * 
 */
public class ConnManager {
	private String dbUserPassword;
	private String dbUserName;
	private String schemaName;
	private Connection connection;
	private int port = 3306;

	private String buildConnString() {
		return "jdbc:mysql://localhost:" + port + "/" + schemaName;
	}

	public ConnManager(String _dbUserName, String _dbUserPassword,
			String _schemaName) {
		dbUserName = _dbUserName;
		dbUserPassword = _dbUserPassword;
		schemaName = _schemaName;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection(
					buildConnString(), dbUserName, dbUserPassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return connection;
	}

	/**
	 * for queries of type select
	 * 
	 * @param sqlString
	 * @return ResultSet object
	 */

	public ResultSet executeRead(String sqlString) {
		ResultSet rst = null;
		try {
			Statement stmt = connection.createStatement();
			rst = stmt.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rst;
	}

	/**
	 * for queries of type create,update or delete.
	 * 
	 * @param sqlString
	 * @return the number of rows affected
	 */
	public int executeCUD(String sqlString) {
		int rowsAffected = 0;
		try {
			Statement stmt = connection.createStatement();
			rowsAffected = stmt.executeUpdate(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}

	/*
	 * This is basically for primitive testing
	 */
	public static void main(String[] args) {
		ConnManager cm = new ConnManager("root","realengineer","neaeaexamadmin");
		ResultSet rs = cm.executeRead("SELECT * FROM exam");
		if (rs == null) {
			System.out.println("returned null");
		}
		else{
			System.out.println("Successfully connected and executed");
		}

	}
}
