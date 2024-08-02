package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

// DAO (Data Access Object) : DB에 직접 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과 반환(=>JDBC 사용)
public class MemberDao {
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER_NAME = "C##JDBC";
	private final String PASSWORD = "JDBC";
	/*
	 * * JDBC용 객체 - Connection : DB 연결정보를 담고있는 객체 - Statement : 연결된 DB에 sql문 전달해서
	 * 실행하고 결과를 받아주는 객체 - ResultSet : SELECT문(DQL) 실행 후 조회된 결과물을 담고있는 객체
	 * 
	 * * JDBC 과정 (순서*) [1] JDBC driver 등록 : 사용할 DBMS(오라클)에서 제공하는 클래스 등록 
	 * [2]	 * Connection 객체 생성 : DB정보(url, 사용자명, 비밀번호)를 통해 해당 DB와 연결하면서 생성 
	 * [3] Statement 객체	 * 생성 : Connection 객체를 이용해서 생성. sql문을 실행하고 결과를 받아줄 것임 
	 * [4] sql문 전달해서 실행 후 결과 받기 -
	 *  * SELECT문 실행시 ResultSet 객체로 조회 결과를 받음 - DML문(INSERT/UPDATE/DELETE) 실행시 int 타입으로 처리 결과를 받음 (처리된 행 수) 
	 * [5] 결과에 대한 처리 - ResultSet 객체에서 데이터를 하나씩 추출하여 vo객체로 옮겨 담기(저장) - DML의 경우 트랜잭션 처리 ( 성공시 commit, 실패시 rollback) 
	 * [6] 자원 반납(close) => 생성 역순으로 !!
	 */

	/**
	 * 사용자가 입력한 정보들을 DB에 추가하는 메소드 (=> 회원 정보 추가)
	 * 
	 * @param m 사용자가 입력한 값들이 담겨있는 Member객체
	 * @return insert문 실행 후 처리된 행 수
	 */
	public int insertMember(Member m) {
		// insert문 --> int(처리된 행 수) --> 트랜잭션 처리
		int result = 0;

		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?,?,?,?,?,?,?,?,?, SYSDATE)";

		System.out.println("-------------------------------------");
		System.out.println(sql);
		System.out.println("-------------------------------------");

		// JDBC용 객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성 : DB에 연결(url, 사용자명, 비밀번호)
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			// 3) prepareStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,m.getUserId());
			pstmt.setString(2,m.getUserPw());
			pstmt.setString(3,m.getUserName());
			pstmt.setString(4, Character.valueOf(m.getGender()).toString());
			pstmt.setInt(5,m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getPhone());
			pstmt.setString(9, m.getHobby());
					
			
			conn.setAutoCommit(false); // 자동 커밋 옵션 false로 설정 (jdbc6 버전 이후 auto commit 설정)

			// 4) 실행 후 결과받기
			result = pstmt.executeUpdate();

			// 5) 트랜잭션 처리 (DML 실행 시 트랜잭션 처리)
			if (result > 0) {
				// sql문 실행이 성공했다면
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 리소스를 해제합니다.
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public ArrayList<Member> selectList() {

		ArrayList<Member> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성 : DB에 연결(url, 사용자명, 비밀번호)
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4) sql문 실행 후 결과 받기
			rset = stmt.executeQuery(sql);

			// 6) sql문 실행 결과를 하나하나 추출하기
			// * 데이터가 있는 지 여부 확인 => rset.next():boolean (데이터가 있으면 true, 없으면 false)
			while (rset.next()) {
				Member m = new Member(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5) == null ? ' ' : rset.getString(5).charAt(0), rset.getInt(6),
						rset.getString(7), rset.getString(8), rset.getString(9), rset.getString(10), rset.getDate(11));
				// ResultSet 객체에서 각 컬럼의 데이터 뽑아내어 Member객체를 생성(저장)
				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납(close) ** 생성 역순 **
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Member search(String id) {

		Member m = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + id + "'";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성 : DB에 연결(url, 사용자명, 비밀번호)
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4) sql문 실행 후 결과 받기
			rset = stmt.executeQuery(sql);

			// 6) sql문 실행 결과를 하나하나 추출하기
				if(rset.next()) {	// 꼭 해줘야 하는듯 하다...!!!!! 
				m = new Member(
								rset.getInt(1), 
								rset.getString(2), 
								rset.getString(3), 
								rset.getString(4),
								rset.getString(5) == null ? ' ' : rset.getString(5).charAt(0), 
								rset.getInt(6),
								rset.getString(7), 
								rset.getString(8), 
								rset.getString(9), 
								rset.getString(10), 
								rset.getDate(11)
								);
				}
				//조건문이 끝난 시점에
				// 조회 데이터가 없다면 ? m(Member) --> null
				//			 있다면 ? m(Member) --> 새로 생성된 객체


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납(close) ** 생성 역순 **
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	public int delete(String id) {

		Connection conn = null;
		Statement stmt = null;

		
		int result = 0;

		String sql = "DELETE FROM MEMBER WHERE USERID = '" + id + "'";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성 : DB에 연결(url, 사용자명, 비밀번호)
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4) sql문 실행 후 결과 받기
			result = stmt.executeUpdate(sql);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납(close) ** 생성 역순 **
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int update(String id, String column, String data) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		int result = 0;

		String sql = "UPDATE MEMBER SET ? = ? WHERE USERID = ?";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성 : DB에 연결(url, 사용자명, 비밀번호)
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

			// 3) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, column);
			pstmt.setString(2, data);
			pstmt.setString(3, id);

			// 4) sql문 실행 후 결과 받기
			result = pstmt.executeUpdate();

			if (result > 0) {conn.commit();}
			else {conn.rollback();}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납(close) ** 생성 역순 **
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Member> selectByUserName(String keyword) {
		
		ArrayList<Member> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ? ";
//		= "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%' "; 이렇게도 가능하네....신기함

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성 : DB에 연결(url, 사용자명, 비밀번호)
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			conn.setAutoCommit(false);

			// 3) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");

			// 4) sql문 실행 후 결과 받기
			rset = pstmt.executeQuery();

			// 6) sql문 실행 결과를 하나하나 추출하기
			// * 데이터가 있는 지 여부 확인 => rset.next():boolean (데이터가 있으면 true, 없으면 false)
			while (rset.next()) {
				Member m = new Member(rset.getInt(1), 
						rset.getString(2), 
						rset.getString(3), 
						rset.getString(4),
						rset.getString(5) == null ? ' ' : rset.getString(5).charAt(0), 
						rset.getInt(6),
						rset.getString(7), 
						rset.getString(8), 
						rset.getString(9), 
						rset.getString(10), 
						rset.getDate(11)
						);
				// ResultSet 객체에서 각 컬럼의 데이터 뽑아내어 Member객체를 생성(저장)
				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납(close) ** 생성 역순 **
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
