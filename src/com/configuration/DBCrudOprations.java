package com.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBCrudOprations {
	public static void main(String[] args) {
		System.out.print("Enter DB Operation Name : \n 1)INSERT\n 2)SELECT\n 3)DELETE\n");
		Scanner scanner = new Scanner(System.in);
		String oprationType = scanner.next();
		performCrudOperations(oprationType);
	}

	private static void performCrudOperations(String oprationType) {
		Connection connection = DBConnection.getConnection();
		System.out.println("OprationType Is : " + oprationType);
		switch (oprationType) {
		case "INSERT":
			insertValuesInDB(connection);
			break;
		case "SELECT":
			selectValuesInDB(connection);
			break;
		case "DELETE":
			deleteValuesInDB(connection);
			break;
		default:
			System.out.print("Please Enter Valid OprationType Is : " + oprationType);
			break;
		}
	}

	private static void insertValuesInDB(Connection connection) {
		String insertSql = "INSERT INTO student VALUES(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "Nana Bhingare");
			preparedStatement.setString(3, "Pune");
			int insertCnt = preparedStatement.executeUpdate();
			if (insertCnt > 0) {
				System.out.println("Value Insert Successfully.....!!!!");
			}
		} catch (SQLException e) {
			System.out.println("Exeception In Inserting DB values : " + e.getMessage() + " : " + e);
		}
	}

	private static void selectValuesInDB(Connection connection) {
		String selectSql = "SELECT * FROM student";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " : " + resultSet.getString(2) + " : " + resultSet.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("Exeception In Selecting DB values : " + e.getMessage() + " : " + e);
		}
	}

	private static void deleteValuesInDB(Connection connection) {
		String deleteSql = "DELETE FROM student WHERE rollno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, 1);
			int deleteCnt = preparedStatement.executeUpdate();
			if (deleteCnt > 0) {
				System.out.println("Record Deleted successfully......!!");
			}
		} catch (SQLException e) {
			System.out.println("Exeception In Deleting DB values : " + e.getMessage() + " : " + e);
		}
	}
}
