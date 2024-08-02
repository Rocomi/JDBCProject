package kh.project.geneJar.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import kh.project.geneJar.model.vo.Employee;

public class EmployeeDAO implements Data<Employee>{
	
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "C##GENEJAR";
	private static final String PASSWORD = "GENEJAR";
	private Employee[] eArr = new Employee[10000];
	
	public void addData(Employee em) {

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
			
			
			String query = "INSERT INTO EMPLOYEE(EMP_NO, EMP_ID, EMP_PWD, EMP_NAME, GENDER, ADDRESS, PHONE, DEPT, SALARY, BONUS)" + " VALUES('" + em.getEmpNo()
					+ "','" + em.getId() + "','" + em.getPassword() + "','" + em.getName() + "','"+ em.getGender() + "','"+ em.getAddress() + "','" + em.getPhone()
					+ "','" + em.getDept()+ "','" + em.getSalary()+ "','" + em.getBonus()+ "')";
			
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
	
	public Employee[] fileRead() { // 현재 데이터 못가져오는즁
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
			String query = "SELECT * FROM EMPLOYEE";
			resultSet = statement.executeQuery(query);

			// 결과를 처리합니다.
			int i = 0;
			while (resultSet.next()) {
				eArr[i] = new Employee(resultSet.getInt(1), 
						resultSet.getString(3), 
						resultSet.getString(4),
						resultSet.getString(2), 
						resultSet.getString(5) == null ? ' ' : resultSet.getString(5).charAt(0),
						resultSet.getString(6),
						resultSet.getString(7),
						resultSet.getString(8),
						resultSet.getInt(9),
						resultSet.getDouble(10));
				i++;
			}
			return eArr;
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
	
	public void changeData(String id, String dataType, String data) {
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
			String query = "UPDATE EMPLOYEE SET " + dataType + 
			" = '" + data + "'"
			+" WHERE EMP_ID = '"+ id+"'"; //----------------------------------------
			
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
			String query = "DELETE FROM EMPLOYEE" + " WHERE CUS_ID = '" + id + "'";
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
