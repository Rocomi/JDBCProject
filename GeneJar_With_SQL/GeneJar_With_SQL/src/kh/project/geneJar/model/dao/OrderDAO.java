package kh.project.geneJar.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import kh.project.geneJar.model.vo.Order;

public class OrderDAO implements Data<Order> {
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "C##GENEJAR";
	private static final String PASSWORD = "GENEJAR";
	private Order[] oArr = new Order[100000];

	@Override
	public void addData(Order o) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultSet = null;

		try {
			// Oracle JDBC 드라이버를 로드합니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스에 연결합니다.
			connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

			// SQL 쿼리를 실행합니다.
			String query = "INSERT INTO MED_ORDER VALUES(?,?,?,?,?,?,?,?)";  // 왠지 안되는굼나
			pstatement = connection.prepareStatement(query);
			pstatement.setString(1, o.getMed());
			pstatement.setString(2, o.getFormulation());
			pstatement.setInt(3, o.getMedNo());
			pstatement.setString(4, o.getOrderNo());
			pstatement.setInt(5, o.getQuantity());
			pstatement.setString(6, o.getOrderName());
			pstatement.setString(7, o.getAddress());
			pstatement.setString(8, o.getPhone());

			resultSet = pstatement.executeQuery();

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
				if (pstatement != null)
					pstatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Order[] fileRead() {
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
			String query = "SELECT * FROM MED_ORDER";
			resultSet = statement.executeQuery(query);

			// 결과를 처리합니다.
			int i = 0;
			while (resultSet.next()) {
				oArr[i] = new Order(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
				i++;
			}
			return oArr;
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

	@Override
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
			String query = "UPDATE MED_ORDER SET " + dataType + 
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
		public void deleteData(String orderNo) {
			
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
				String query = "DELETE FROM MED_ORDER" + " WHERE ORDER_NO = '" + orderNo + "'";
				resultSet = statement.executeQuery(query);
				
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
