package com.mystudy.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerCRUD {

	private static final String DRIVER = "oracle.jdbc.OracleDriver"; 
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private static final String USER = "STUDENTMANAGER"; 
	private static final String PASSWORD = "STUDENTMANAGERPW"; 

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	//static 초기화 구문
	static {
		try {
			Class.forName(DRIVER);
			//System.out.println(">> 오라클 JDBC 드라이버 로딩 성공!!");
		} catch (ClassNotFoundException e) {
			System.out.println("[예외발생] 드라이버 로딩 실패!!!");
		}
	}
	
	//로그인 처리 : ID, PASSWORD 값을 받아서 동일한 데이타 있으면 true, 없으면 false 리턴
	//boolean checkIdPassword(id, password)
	public boolean checkIdPassword(int MNO, String MPW) {
		boolean result = false;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT MNO  ");
			sb.append("  FROM MANAGER ");
			sb.append(" WHERE MNO = ? AND MPW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, MNO);
			pstmt.setString(2, MPW);
			
			rs = pstmt.executeQuery();
			if (rs.next()) { //최소 1 건 이상의 데이타가 있음
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			common_util.JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		
		return result;
	}
	
	public boolean checkIdPassword2(int MNO, String MPW) {
		boolean result = false;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) AS CNT ");
			sb.append("  FROM MANAGER ");
			sb.append(" WHERE MNO = ? AND MPW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, MNO);
			pstmt.setString(2, MPW);
			
			rs = pstmt.executeQuery();
			if (rs.next()) { //COUNT(*) 값은 무조건 있음 0~n
				if (rs.getInt(1) > 0) {
					result = true;
				} 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			common_util.JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		
		return result;
	}



}
