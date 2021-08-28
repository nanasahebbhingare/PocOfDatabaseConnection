package com.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private DBConnection() {
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (null == connection)
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("DB Connection Exception : " + e.getMessage() + " : " + e);
		}
		if (null != connection) {
			System.out.println("DB Connection Successfully....");
		} else {
			System.out.println("DB Connection Not Successfully....");
		}
		return connection;
	}
}
