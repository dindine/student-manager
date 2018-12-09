package com.mystudy.student;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common_util.JDBC_Close;

public class ProfessorCRUD {
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
	//SELECT : ID
	public void dispData(int PNO) {
		try {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//3. Statement�� ����(SQL�� ����)
			String sql = "";
			sql += "SELECT PNO, PNAME, DEPTNO ,PPHONE,PW, SUBNO , JUMIN";
			sql += "  FROM PROFESSOR ";
			sql += " WHERE PNO = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, PNO);

			rs = pstmt.executeQuery();

			//4. SQL ���� ����� ���� ó��
			if (rs.next()) {

				String str = "";
				str += rs.getInt(1) + "\t";
				str += rs.getString(2) + "\t";
				str += rs.getInt(3) + "\t";
				str += rs.getString(4) + "\t";
				str += rs.getString(5) + "\t";
				str += rs.getString(6) + "\t";
				str += rs.getString(7);
				System.out.println(str);
			} else {
				System.out.println(PNO + "-����Ÿ ����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. Ŭ��¡ ó���� ���� �ڿ� �ݳ�
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public ArrayList<ProfessorVO> selectAll() {
		ArrayList<ProfessorVO> list = null;
		try {
			//DB���� - Connection ��ü ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT PNO, PW, PNAME, DEPTNO , SUBNO, SUB, JUMIN, PPHONE ");
			sb.append("  FROM PROFESSOR ");
			sb.append(" ORDER BY PNO ");

			//Connection ��ü�� ���� PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sb.toString());

			//SQL���� ����
			rs = pstmt.executeQuery(); //SELECT : executeQuery()

			list = new ArrayList<>();
			while (rs.next()) {

				list.add(new ProfessorVO(rs.getInt("PNO"), 
						rs.getString("PW"), 
						rs.getString("PNAME"), 
						rs.getInt("DEPTNO"), 
						rs.getInt("SUBNO"),
						rs.getString("SUB"),
						rs.getString("JUMIN"), 
						rs.getString("PPHONE")
						));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return list;
	}

	//�α��� ó�� : ID, PASSWORD ���� �޾Ƽ� ������ ����Ÿ ������ true, ������ false ����
	//boolean checkIdPassword(id, password)
	public boolean checkIdPassword(int PNO, String PW) {
		boolean result = false;
		
		try {
			//DB���� - Connection ��ü ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT PNO  ");
			sb.append("  FROM PROFESSOR ");
			sb.append(" WHERE PNO = ? AND PW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, PNO);
			pstmt.setString(2, PW);

			rs = pstmt.executeQuery();
			if (rs.next()) { //�ּ� 1 �� �̻��� ����Ÿ�� ����
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}


		return result;
	}

	public boolean checkIdPassword2(int PNO, String PW) {
		boolean result = false;
		try {
			//DB���� - Connection ��ü ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) AS CNT ");
			sb.append("  FROM PROFESSOR ");
			sb.append(" WHERE PNO = ? AND PW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, PNO);
			pstmt.setString(2, PW);

			rs = pstmt.executeQuery();
			if (rs.next()) { //COUNT(*) ���� ������ ���� 0~n
				if (rs.getInt(1) > 0) {
					result = true;
				} 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}


		return result;
	}
	public int PinsertOne(ProfessorVO PRO) {
		int result = 0;

		try {
			//DB ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL ���� ������ ���� �غ�
			//SQL ���� �ۼ�
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO PROFESSOR ");
			sb.append("       (	PNO, PW, PNAME, DEPTNO , SUBNO, SUB, JUMIN, PPHONE  ) ");
			sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
			//SQL ������ �����ؼ� ���� �غ�
			pstmt = conn.prepareStatement(sb.toString());

			//SQL���� ?�� �� ��Ī �۾�
			pstmt.setInt(1, PRO.getPNO());
			pstmt.setString(2, PRO.getPW());
			pstmt.setString(3, PRO.getPNAME());
			pstmt.setInt(4, PRO.getDEPTNO());
			pstmt.setInt(5, PRO.getSUBNO());
			pstmt.setString(6, PRO.getSUB());
			pstmt.setString(7, PRO.getJUMIN());
			pstmt.setString(8, PRO.getPPHONE());
			//SQL�� ����
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//����� �ڿ� �ݱ�
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}
	public ProfessorVO selectOne(int PNO) {
		ProfessorVO pvo = null;
		try {
			//DB���� - Connection ��ü ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM PROFESSOR WHERE PNO = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, PNO);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				pvo = new ProfessorVO(rs.getInt("PNO"),
						rs.getString("PW"), 
						rs.getString("PNAME"), 
						rs.getInt("DEPTNO"), 
						rs.getInt("SUBNO"),
						rs.getString("SUB"),
						rs.getString("JUMIN"), 
						rs.getString("PPHONE")
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return pvo;
	}
	public ProfessorVO selectSUBOne(int SUBNO) {
		ProfessorVO pvo = null;
		try {
			//DB���� - Connection ��ü ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM PROFESSOR WHERE SUBNO = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, SUBNO);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				pvo = new ProfessorVO(rs.getInt("SUBNO"),
						rs.getString("PW"), 
						rs.getString("PNAME"), 
						rs.getInt("DEPTNO"), 
						rs.getInt("SUBNO"),
						rs.getString("SUB"),
						rs.getString("JUMIN"), 
						rs.getString("PPHONE")
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return pvo;
	}
	
	public int updateData(ProfessorVO PRO) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE PROFESSOR ");
			sb.append("   SET PW = ? ");
			sb.append("     , PNAME = ? ");
			sb.append("     , DEPTNO = ? ");
			sb.append("     , SUBNO = ? ");
			sb.append("     , SUB = ? ");
			sb.append("     , JUMIN = ? ");
			sb.append("     , PPHONE = ? ");
			sb.append(" WHERE PNO = ?");
			//SQL ������ �����ؼ� ���� �غ�
			
			pstmt = conn.prepareStatement(sb.toString());
			
			//SQL���� ?�� �� ��Ī �۾�

			pstmt.setString(1, PRO.getPW());
			pstmt.setString(2, PRO.getPNAME());
			pstmt.setInt(3, PRO.getDEPTNO());
			pstmt.setInt(4, PRO.getSUBNO());
			pstmt.setString(5, PRO.getSUB());
			pstmt.setString(6, PRO.getJUMIN());
			pstmt.setString(7, PRO.getPPHONE());
			pstmt.setInt(8, PRO.getPNO());

			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		
		return result;
	}
	
	//DELETE : VO ��ü�� �޾Ƽ� ���� - deleteOne : int
		public int deleteOne(ProfessorVO PRO) {
			int result = 0;

			try {
				//DB ����
				conn = DriverManager.getConnection(URL, USER, PASSWORD);

				//SQL ���� ������ ���� �غ�
				//SQL ���� �ۼ�
				StringBuilder sb = new StringBuilder();
				sb.append("DELETE FROM PROFESSOR WHERE PNO = ?");

				//SQL ������ �����ؼ� ���� �غ�
				pstmt = conn.prepareStatement(sb.toString());

				//SQL���� ?�� �� ��Ī �۾�
				pstmt.setInt(1, PRO.getPNO());

				//SQL�� ����
				result = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//����� �ڿ� �ݱ�
				JDBC_Close.closeConnStmt(conn, pstmt);
			}

			return result;
		}


		//DELETE : ID �޾Ƽ� ���� - deleteOne : int
		public int deleteOne(int PNO) {
			int result = 0;

			try {
				//DB ����
				conn = DriverManager.getConnection(URL, USER, PASSWORD);

				//SQL ���� ������ ���� �غ�
				//SQL ���� �ۼ�
				StringBuilder sb = new StringBuilder();
				sb.append("DELETE FROM PROFESSOR WHERE PNO = ?");

				//SQL ������ �����ؼ� ���� �غ�
				pstmt = conn.prepareStatement(sb.toString());

				//SQL���� ?�� �� ��Ī �۾�
				pstmt.setInt(1, PNO);

				//SQL�� ����
				result = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//����� �ڿ� �ݱ�
				JDBC_Close.closeConnStmt(conn, pstmt);
			}

			return result;
		}

}