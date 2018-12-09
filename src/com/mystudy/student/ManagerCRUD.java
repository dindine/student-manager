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

	//static �ʱ�ȭ ����
	static {
		try {
			Class.forName(DRIVER);
			//System.out.println(">> ����Ŭ JDBC ����̹� �ε� ����!!");
		} catch (ClassNotFoundException e) {
			System.out.println("[���ܹ߻�] ����̹� �ε� ����!!!");
		}
	}
	
	//�α��� ó�� : ID, PASSWORD ���� �޾Ƽ� ������ ����Ÿ ������ true, ������ false ����
	//boolean checkIdPassword(id, password)
	public boolean checkIdPassword(int MNO, String MPW) {
		boolean result = false;
		try {
			//DB���� - Connection ��ü ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT MNO  ");
			sb.append("  FROM MANAGER ");
			sb.append(" WHERE MNO = ? AND MPW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, MNO);
			pstmt.setString(2, MPW);
			
			rs = pstmt.executeQuery();
			if (rs.next()) { //�ּ� 1 �� �̻��� ����Ÿ�� ����
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
			//DB���� - Connection ��ü ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) AS CNT ");
			sb.append("  FROM MANAGER ");
			sb.append(" WHERE MNO = ? AND MPW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, MNO);
			pstmt.setString(2, MPW);
			
			rs = pstmt.executeQuery();
			if (rs.next()) { //COUNT(*) ���� ������ ���� 0~n
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
