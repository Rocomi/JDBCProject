package kh.project.geneJar.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import kh.project.geneJar.model.vo.CustomerLogin;

public class CustomerLoginDAO implements Data<CustomerLogin> {

	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "C##GENEJAR";
	private static final String PASSWORD = "GENEJAR";
	private CustomerLogin[] cArr = new CustomerLogin[100000];
	 	
	
	public void addData(CustomerLogin c) {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Oracle JDBC 드라이버를 로드합니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스에 연결합니다.
			connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

			// SQL 쿼리를 실행합니다.
			statement = connection.createStatement();
			String query = "INSERT INTO CUSTOMER(CUS_NAME, CUS_ID, CUS_PWD, ADDRESS, PHONE)" + "VALUES('" + c.getName()
					+ "','" + c.getId() + "','" + c.getPassword() + "','" + c.getAddress() + "','" + c.getPhone()
					+ "')";
			
			resultSet = statement.executeQuery(query);
			
			System.out.println("회원가입이 완료되었습니다.");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 사용중인 아이디 입니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();
		} finally {
			// 리소스를 해제합니다.
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public CustomerLogin[] fileRead() { // 회원정보들을 파일에서 가져와 객체배열로 생성
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Oracle JDBC 드라이버를 로드합니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스에 연결합니다.
			connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

			// SQL 쿼리를 실행합니다.
			statement = connection.createStatement();
			String query = "SELECT * FROM CUSTOMER";
			resultSet = statement.executeQuery(query);

			// 결과를 처리합니다.
			int i = 0;
			while (resultSet.next()) {
				cArr[i] = new CustomerLogin(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5));
				i++;
			}
			return cArr;
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();
		} finally {
			// 리소스를 해제합니다.
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void changeData(String id, String dataType, String data) { // 입력받은 객체배열을 데이터로 분리하여 파일에 저장 (덮어쓰기)
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Oracle JDBC 드라이버를 로드합니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스에 연결합니다.
			connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

			// SQL 쿼리를 실행합니다.
			statement = connection.createStatement();
			String query = "UPDATE CUSTOMER SET " + dataType + 
			" = '" + data + "'"
			+" WHERE CUS_ID = '"+ id+"'"; //----------------------------------------
			resultSet = statement.executeQuery(query);

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 사용중인 아이디 입니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();
		} finally {
			// 리소스를 해제합니다.
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public void deleteData(String id) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Oracle JDBC 드라이버를 로드합니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 데이터베이스에 연결합니다.
			connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			
			// SQL 쿼리를 실행합니다.
			statement = connection.createStatement();
			String query = "DELETE FROM CUSTOMER" + " WHERE CUS_ID = '" + id + "'";
			resultSet = statement.executeQuery(query);
			
			System.out.println("삭제 완료.");
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("없는 아이디 입니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();
		} finally {
			// 리소스를 해제합니다.
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
