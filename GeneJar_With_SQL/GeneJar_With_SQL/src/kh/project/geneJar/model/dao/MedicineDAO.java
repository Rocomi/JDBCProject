package kh.project.geneJar.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import kh.project.geneJar.model.vo.Medicine;

public class MedicineDAO implements Data<Medicine> {
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "C##GENEJAR";
	private static final String PASSWORD = "GENEJAR";
	private Medicine[] mArr = new Medicine[100000];
	
	public void addData(Medicine m) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultSet = null;

		try {
			// Oracle JDBC 드라이버를 로드합니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스에 연결합니다.
			connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

			// SQL 쿼리를 실행합니다.
			String query = "INSERT INTO MEDICINE VALUES(?,?,?,?,?,?,?,?)";
			pstatement = connection.prepareStatement(query);
			pstatement.setString(1, m.getName());
			pstatement.setString(2, m.getMed());
			pstatement.setString(3, m.getFormulation());
			pstatement.setString(4, m.getTarget());
			pstatement.setInt(5, m.getMedNo());
			pstatement.setInt(6, m.getProdCost());
			pstatement.setInt(7, m.getPrice());
			pstatement.setInt(8, m.getInven());
			
			resultSet = pstatement.executeQuery();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("중복된 값이 존재합니다.");
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
				if (pstatement != null)
					pstatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Medicine[] fileRead() {
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
			String query = "SELECT * FROM MEDICINE";
			resultSet = statement.executeQuery(query);

			// 결과를 처리합니다.
			int i = 0;
			while (resultSet.next()) {
				mArr[i] = new Medicine(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8));
				i++;
			}
			return mArr;
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
	
	public void changeData(String med, String dataType, String data) {
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
			String query = "UPDATE MEDICINE SET " + dataType + 
			" = " + data + ""
			+" WHERE MEDICINE = '"+ med+"'"; //----------------------------------------
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
	public void deleteData(String med) {
		
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
			String query = "DELETE FROM MEDICINE" + " WHERE MEDICINE = '" + med + "'";
			resultSet = statement.executeQuery(query);
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("없는 약품 입니다.");
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
