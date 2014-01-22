package com.neaea_exam_admin.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	private String dbUserPassword="realengineer";
	private String dbUserName="root";
	private String schemaName="neaeaexamadmin";	
	private int port = 3306;

	private String buildConnString() {
		return "jdbc:mysql://localhost:" + port + "/" + schemaName;
	}
    public ConnManager(){
    	
    }
	public ConnManager(String _dbUserName, String _dbUserPassword,
			String _schemaName) {
		dbUserName = _dbUserName;
		dbUserPassword = _dbUserPassword;
		schemaName = _schemaName;
		

	}
	/**
	 * opens a connection to the database
	 * @return a Connection object
	 */
    private Connection openConn(){
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = (Connection) DriverManager.getConnection(
					buildConnString(), dbUserName, dbUserPassword);
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * closes a connection that is left open
     * @param connection
     */
    private void closeConn(Connection connection){
    	try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	

 	/**
	 * for queries of type select
	 * 
	 * @param sqlString
	 * @return ResultSet object
	 */

	public ResultSet executeRead(String sqlString) {
		ResultSet rst = null;
		Connection connection=openConn();
		try {
			Statement stmt = connection.createStatement();
			rst = stmt.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (connection!=null){
				closeConn(connection);
			}
		}
		return rst;
	}

	/**
	 * for queries of type create,update or delete.
	 * 
	 * @param sqlString
	 * @return the number of rows affected
	 */
	public void executeCUD(String sqlString) {
		@SuppressWarnings("unused")
		int  rowsAffected= 0;
		Connection connection=openConn();
		try {
			Statement stmt = connection.createStatement();
			rowsAffected = stmt.executeUpdate(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		 if(connection!=null){
			 closeConn(connection);
		 }
		}
		
	}
	public PreparedStatement getPreparedStatement(String prepStmtQuery){
		try {
			return openConn().prepareStatement(prepStmtQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
