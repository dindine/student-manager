package com.mystudy.student;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mystudy.student.StudentVO;

import common_util.JDBC_Close;



public class StudentCRUD {

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

	//SELECT : ID
	public void dispData(int SNO) {
		try {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//3. Statement문 실행(SQL문 실행)
			String sql = "";
			sql += "SELECT SNO, PW, NAME, DEPTNO, QPA, JUMIN, PHONE, ADDRESS, SUBNO ";
			sql += "  FROM STUDENT ";
			sql += " WHERE SNO = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, SNO);

			rs = pstmt.executeQuery();

			//4. SQL 실행 결과에 대한 처리
			if (rs.next()) {

				String str = "";
				str += rs.getInt(1) + "\t";
				str += rs.getString(2) + "\t";
				str += rs.getString(3) + "\t";
				str += rs.getInt(4) + "\t";
				str += rs.getString(5) + "\t";
				str += rs.getString(6) + "\t";
				str += rs.getString(7) + "\t";
				str += rs.getString(8) + "\t";
				str += rs.getInt(9);
				System.out.println(str);
			} else {
				System.out.println(SNO + "-데이타 없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 클로징 처리에 의한 자원 반납
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

	public ArrayList<StudentVO> selectAll() {
		ArrayList<StudentVO> list = null;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT SNO, PW, NAME, DEPTNO, QPA, JUMIN, PHONE, ADDRESS, SUBNO ");
			sb.append("  FROM STUDENT ");
			sb.append(" ORDER BY SNO ");

			//Connection 객체로 부터 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sb.toString());

			//SQL문장 실행
			rs = pstmt.executeQuery(); //SELECT : executeQuery()

			list = new ArrayList<>();
			while (rs.next()) {

				list.add(new StudentVO(rs.getInt("SNO"), 
						rs.getString("PW"), 
						rs.getString("NAME"), 
						rs.getInt("DEPTNO"), 
						rs.getString("QPA"), 
						rs.getString("JUMIN"), 
						rs.getString("PHONE"), 
						rs.getString("ADDRESS"),
						rs.getInt("SUBNO")
						));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

		return list;
	}
	public ArrayList<StudentVO> selectSubnoList(int subno) {
		ArrayList<StudentVO> list = null;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT SNO, PW, NAME, DEPTNO, QPA, JUMIN, PHONE, ADDRESS, SUBNO ");
			sb.append("  FROM STUDENT ");
			sb.append("  WHERE SUBNO = ? ");
			sb.append(" ORDER BY SNO ");

			//Connection 객체로 부터 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, subno);
			//SQL문장 실행
			rs = pstmt.executeQuery(); //SELECT : executeQuery()

			list = new ArrayList<>();
			while (rs.next()) {

				list.add(new StudentVO(rs.getInt("SNO"), 
						rs.getString("PW"), 
						rs.getString("NAME"), 
						rs.getInt("DEPTNO"), 
						rs.getString("QPA"), 
						rs.getString("JUMIN"), 
						rs.getString("PHONE"), 
						rs.getString("ADDRESS"),
						rs.getInt("SUBNO")
						));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

		return list;
	}

	public int insertOne(int SNO, String PW, String NAME,
			int DEPTNO, int QPA, String JUMIN, int PHONE, String ADDRESS) {
		int cnt = 0;

		try {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//3. Statement문 실행(SQL문 실행)
			String sql = "";
			sql += "SELECT STUDENT ";
			sql += "       (SNO, PW, NAME, DEPTNO, QPA, JUMIN, PHONE, ADDRESS ) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
			//3-1. SQL 문장 실행 준비
			pstmt = conn.prepareStatement(sql);

			//3-2. SQL 문장의 ? 위치에 값 매칭
			pstmt.setInt(1, SNO);
			pstmt.setString(2, PW);
			pstmt.setString(3, NAME);
			pstmt.setInt(4, DEPTNO);
			pstmt.setInt(5, QPA);
			pstmt.setString(6, JUMIN);
			pstmt.setInt(7, PHONE);
			pstmt.setString(8, ADDRESS);

			//3-3 준비된 SQL문장 실행 처리
			cnt = pstmt.executeUpdate();

			//4. SQL 실행 결과에 대한 처리
			System.out.println("처리건수: " + cnt);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return cnt;
	}

	//SELECT : 하나의 데이타 조회(ID) - selectOne : MemberVO
	public StudentVO selectOne(int SNO) {
		StudentVO mvo = null;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM STUDENT WHERE SNO = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, SNO);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mvo = new StudentVO(rs.getInt("SNO"), 
						rs.getString("PW"), 
						rs.getString("NAME"), 
						rs.getInt("DEPTNO"), 
						rs.getString("QPA"), 
						rs.getString("JUMIN"), 
						rs.getString("PHONE"), 
						rs.getString("ADDRESS"),
						rs.getInt("SUBNO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

		return mvo;
	}

	//SELECT : 하나의 데이타 조회(VO) - selectOne : StudentVO
	public StudentVO selectOne(StudentVO STUDENT) {
		StudentVO svo = null;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM STUDENT WHERE SNO = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, STUDENT.getSNO());

			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				svo = new StudentVO(rs.getInt("SNO"), 
//						rs.getString("PW"), 
//						rs.getString("NAME"), 
//						rs.getInt("DEPTNO"), 
//						rs.getInt("QPA"), 
//						rs.getString("JUMIN"), 
//						rs.getInt("PHONE"), 
//						rs.getString("ADDRESS"),
//						rs.getInt("getInt"));
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}

		return svo;
	}
	
	//SELECT : 하나의 데이타 조회(VO) - selectOne : StudentVO
		public StudentVO selectOneA(StudentVO STUDENT) {
			StudentVO svo = null;
			try {
				//DB연결 - Connection 객체 생성
				conn = DriverManager.getConnection(URL, USER, PASSWORD);

				StringBuilder sb = new StringBuilder();
				sb.append("SELECT * FROM STUDENT WHERE SNO = ?");
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, STUDENT.getSNO());

				rs = pstmt.executeQuery();
//				if (rs.next()) {
//					svo = new StudentVO(rs.getInt("SNO"), 
//							rs.getString("PW"), 
//							rs.getString("NAME"), 
//							rs.getInt("DEPTNO"), 
//							rs.getInt("QPA"), 
//							rs.getString("JUMIN"), 
//							rs.getInt("PHONE"), 
//							rs.getString("ADDRESS"),
//							rs.getInt("getInt"));
//				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
			}

			return svo;
		}
	public StudentVO selectSUBOne(int SUBNO) {
		StudentVO pvo = null;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM STUDENT WHERE SUBNO = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, SUBNO);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				pvo = new StudentVO(rs.getInt("SNO"), 
						rs.getString("PW"), 
						rs.getString("NAME"), 
						rs.getInt("DEPTNO"), 
						rs.getString("QPA"), 
						rs.getString("JUMIN"), 
						rs.getString("PHONE"), 
						rs.getString("ADDRESS"),
						rs.getInt("SUBNO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return pvo;
	}
	public int updateData(StudentVO STUDENT) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE STUDENT ");
			sb.append("   SET PW = ? ");
			sb.append("     , NAME = ? ");
			sb.append("     , DEPTNO = ? ");
			sb.append("     , QPA = ? ");
			sb.append("     , JUMIN = ? ");
			sb.append("     , PHONE = ? ");
			sb.append("     , ADDRESS = ? ");
			sb.append(" WHERE SNO = ?");
			//SQL 문장을 전달해서 실행 준비

			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업

			pstmt.setString(1, STUDENT.getPW());
			pstmt.setString(2, STUDENT.getNAME());
			pstmt.setInt(3, STUDENT.getDEPTNO());
			pstmt.setString(4, STUDENT.getQPA());
			pstmt.setString(5, STUDENT.getJUMIN());
			pstmt.setString(6, STUDENT.getPHONE());
			pstmt.setString(7, STUDENT.getADDRESS());
			pstmt.setInt(8, STUDENT.getSNO());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}




	public int insertOne(StudentVO STUDENT) {
		int result = 0;

		try {
			//DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL 문장 실행을 위한 준비
			//SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO STUDENT ");
			sb.append("       (SNO, PW, NAME, DEPTNO, QPA, JUMIN, PHONE, ADDRESS ) ");
			//			sb.append("       ( SNO, PW, NAME, JUMIN ) ");
			sb.append("VALUES (?, ?, ?, ? , ?, ?, ?, ?) ");
			//SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업
			pstmt.setInt(1, STUDENT.getSNO());
			pstmt.setString(2, STUDENT.getPW());
			pstmt.setString(3, STUDENT.getNAME());
			pstmt.setInt(4, STUDENT.getDEPTNO());
			pstmt.setString(5, STUDENT.getQPA());
			pstmt.setString(6, STUDENT.getJUMIN());
			pstmt.setString(7, STUDENT.getPHONE());
			pstmt.setString(8, STUDENT.getADDRESS());

			//SQL문 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//사용한 자원 닫기
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}

	//	public int joinUser() {
	//		int result = 0;
	//		
	//		try {
	//			//DB 연결
	//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
	//			
	//			//SQL 문장 실행을 위한 준비
	//			//SQL 문장 작성
	//			StringBuilder sb = new StringBuilder();
	//			sb.append("INSERT INTO STUDENT ");
	////			sb.append("       (SNO, PW, NAME, DEPTNO, QPA, JUMIN, PHONE, ADDRESS ) ");
	//			sb.append("       ( SNO, PW, NAME, JUMIN ) ");
	//			sb.append("VALUES (?, ?, ?, ? ) ");
	//			//SQL 문장을 전달해서 실행 준비
	//			pstmt = conn.prepareStatement(sb.toString());
	//			StudentManager sm = new StudentManager();
	//			System.out.println(sm.JUMIN);
	//			//SQL문의 ?에 값 매칭 작업
	//			pstmt.setInt(1, sm.SNO);
	//			pstmt.setString(2, sm.PW);
	//			pstmt.setString(3, sm.NAME);
	//			pstmt.setString(4, sm.JUMIN);
	//			
	//			//SQL문 실행
	//			result = pstmt.executeUpdate();
	//			
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			//사용한 자원 닫기
	//			JDBC_Close.closeConnStmt(conn, pstmt);
	//		}
	//		
	//		return result;
	//	}

	//DELETE : VO 객체를 받아서 삭제 - deleteOne : int
	public int deleteOne(StudentVO STUDENT) {
		int result = 0;

		try {
			//DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL 문장 실행을 위한 준비
			//SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM STUDENT WHERE SNO = ?");

			//SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업
			pstmt.setInt(1, STUDENT.getSNO());

			//SQL문 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//사용한 자원 닫기
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}


	//DELETE : ID 받아서 삭제 - deleteOne : int
	public int deleteOne(int SNO) {
		int result = 0;

		try {
			//DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL 문장 실행을 위한 준비
			//SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM STUDENT WHERE SNO = ?");

			//SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업
			pstmt.setInt(1, SNO);

			//SQL문 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//사용한 자원 닫기
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}

	//로그인 처리 : ID, PASSWORD 값을 받아서 동일한 데이타 있으면 true, 없으면 false 리턴
	//boolean checkIdPassword(id, password)
	public boolean checkIdPassword(int SNO, String PW) {
		boolean result = false;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT SNO  ");
			sb.append("  FROM STUDENT ");
			sb.append(" WHERE SNO = ? AND PW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, SNO);
			pstmt.setString(2, PW);

			rs = pstmt.executeQuery();
			if (rs.next()) { //최소 1 건 이상의 데이타가 있음
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}


		return result;
	}

	public boolean checkIdPassword2(int SNO, String PW) {
		boolean result = false;
		try {
			//DB연결 - Connection 객체 생성
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) AS CNT ");
			sb.append("  FROM STUDENT ");
			sb.append(" WHERE SNO = ? AND PW = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, SNO);
			pstmt.setString(2, PW);

			rs = pstmt.executeQuery();
			if (rs.next()) { //COUNT(*) 값은 무조건 있음 0~n
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
	
	public int updateOne(StudentVO STUDENT) {
		int result = 0;
		
		try {
			//DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//SQL 문장 실행을 위한 준비
			//SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE STUDENT ");
			sb.append("   SET PW = ? ");
			sb.append("     , NAME = ? ");
			sb.append("     , DEPTNO = ? ");
			sb.append("     , QPA = ? ");
			sb.append("     , JUMIN = ? ");
			sb.append("     , PHONE = ? ");
			sb.append("     , ADDRESS = ? ");
			sb.append("     , SUBNO = ? ");
			sb.append(" WHERE SNO = ?");
			//SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());
			
			//SQL문의 ?에 값 매칭 작업
			pstmt.setString(1, STUDENT.getPW());
			pstmt.setString(2, STUDENT.getNAME());
			pstmt.setInt(3, STUDENT.getDEPTNO());
			pstmt.setString(4, STUDENT.getQPA());
			pstmt.setString(5, STUDENT.getJUMIN());
			pstmt.setString(6, STUDENT.getPHONE());
			pstmt.setString(7, STUDENT.getADDRESS());
			pstmt.setInt(8, STUDENT.getSUBNO());
			pstmt.setInt(9, STUDENT.getSNO());
			
			//SQL문 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//사용한 자원 닫기
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		
		return result;
	}
	
//	public int updateOne(int SNO, String NAME, String PW,int DEPTNO,int QPA,int PHONE,String JUMIN,String ADDRESS) {
//		int result = 0;
//		
//		try {
//			//DB 연결
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			
//			//SQL 문장 실행을 위한 준비
//			//SQL 문장 작성
//			StringBuilder sb = new StringBuilder();
//			sb.append("UPDATE STUDENT ");
//			sb.append("   SET NAME = ? ");
//			sb.append("     , PW = ? ");
//			sb.append("     , DEPTNO = ? ");
//			sb.append("     , QPA = ? ");
//			sb.append("     , PHONE = ? ");
//			sb.append("     , JUMIN = ? ");
//			sb.append("     , ADDRESS = ? ");
//			sb.append(" WHERE SNO = ?");
//			//SQL 문장을 전달해서 실행 준비
//			pstmt = conn.prepareStatement(sb.toString());
//			
//			//SQL문의 ?에 값 매칭 작업
//			pstmt.setString(1, NAME);
//			pstmt.setString(2, PW);
//			pstmt.setInt(3, DEPTNO);
//			pstmt.setInt(4, QPA);
//			pstmt.setInt(5, PHONE);
//			pstmt.setString(6, JUMIN);
//			pstmt.setString(7, ADDRESS);
//			pstmt.setInt(8, SNO);
//			
//			//SQL문 실행
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			//사용한 자원 닫기
//			JDBC_Close.closeConnStmt(conn, pstmt);
//		}
//		
//		return result;
//	}	
}