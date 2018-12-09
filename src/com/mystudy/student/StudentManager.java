package com.mystudy.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import common_util.JDBC_Close;

public class StudentManager {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "STUDENTMANAGER";
	private static final String PASSWORD = "STUDENTMANAGERPW";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	static {// static 초기화 구문
		try {
			Class.forName(DRIVER);
			//System.out.println(">> 오라클 JDBC 드라이버 로딩 성공!!");
		} catch (ClassNotFoundException e) {
			System.out.println("[예외발생] 드라이버 로딩 실패!!!");
		}
	}
	// =======================================================
	int SNO = 0,PNO = 0, MNO = 0;
	String PW; String NAME; String JUMIN; String QPA; String PHONE; String ADDRESS; String PNAME; String PPHONE; String SUB;
	int DEPTNO; int SUBNO;
	
	StudentCRUD crud = new StudentCRUD();
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StudentManager sm = new StudentManager();
		sm.startManager();
		// sm.Manager();
	}

	public void startManager() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("					■			학생 관리 시스템		   ■");
			while (true) {
				if (studentManager() == false) {
					break;
				}
			}
//			System.out.println("								작업을 종료합니다.");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private boolean studentManager() {
		boolean jobContinue = true;
		showMenu();
		int select = 99;
		try {
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("잘못된 값이 입력되었습니다." + " 메뉴(0~3) 숫자만 선택하세요");
			return jobContinue;
		}
		if (select == 1) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	학생 로그인 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			studentLogin();
		} else if (select == 2) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	교수 로그인 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			professorLogin();
		} else if (select == 3) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	관리자 로그인 		            *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			adminLogin();
		} else if (select == 4) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	학생 회원 가입 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			joinstu();
		} else if (select == 5) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	교수 회원 가입 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			projoin();
		} else if (select == 0) {
			System.out.println("					■	   	       	종료합니다. 		          ■");
			jobContinue = false; // 작업종료인 경우 false 리턴
		} else {
			System.out.println("잘못된 값이 입력되었습니다." + " 메뉴(0~4) 숫자만 선택하세요");
		}
		return jobContinue;
	}
	private void showMenu() {
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■		       메뉴를 선택하세요 		   ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■	메뉴 : 						   ■");
		System.out.println("					■			1. [학생로그인]			   ■");
		System.out.println("					■			2. [교수로그인]			   ■");
		System.out.println("					■			3. [관리자로그인]		   ■");
		System.out.println("					■			4. [학생 회원가입]		   ■");
		System.out.println("					■			5. [교수 회원가입]		   ■");
		System.out.println("					■			0. [종료]		           ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println();
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.print("						 	선택 메뉴 : 	");
	}
	
	private boolean studentLogin1() {
		boolean jobContinue = true;
		stushowMenu();
		int select = 99;
		try {
			System.out.println();
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("					잘못된 값이 입력되었습니다." + " 메뉴(0~3) 숫자만 선택하세요");
			return jobContinue;
		}
		if (select == 1) {
			stuselect();
			studentLogin1();
		} else if (select == 2) {
			stuUpdate();
			studentLogin1();

		} else if (select == 0) {
			System.out.println("					■	   	       	종료합니다. 		           ■");
			studentManager();
			jobContinue = false; // 작업종료인 경우 false 리턴
		} else {
			System.out.println("잘못된 값이 입력되었습니다." + " 메뉴(0~2) 숫자만 선택하세요");
		}
		return jobContinue;
	}
	private void stushowMenu() {
		System.out.println();
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■		       메뉴를 선택하세요 		    ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■			1. [본인 정보 검색]		    ■");
		System.out.println("					■			2. [본인 정보 수정]		    ■");
		System.out.println("					■			0. [종료]		            ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println();
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.print("						 	선택 메뉴 : 	");
	}
	private boolean professorManager() {
		boolean jobContinue = true;
		proshowmenu();
		int select = 99;
		try {
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("					잘못된 값이 입력되었습니다." + " 메뉴(0~4) 숫자만 선택하세요");
			return jobContinue;
		}
		if (select == 1) {
			proselect();
			professorManager();
		} else if (select == 2) {
			proUpdate();
			professorManager();
		} else if (select == 3) {
			prostuSelect();
			professorManager();
		} else if (select == 4) {
			professorUpdateQpa();
			professorManager();
		} else if (select == 0) {
			System.out.println("						종료합니다.			");
			studentManager();
			jobContinue = false; // 작업종료인 경우 false 리턴
		} else {
			System.out.println("잘못된 값이 입력되었습니다." + " 메뉴(0~4) 숫자만 선택하세요");
		}
		return jobContinue;
	}
	private void proshowmenu() {
		System.out.println();
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■		       메뉴를 선택하세요 		    ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■			1. [본인 정보 검색]		    ■");
		System.out.println("					■			2. [본인 정보 수정]		    ■");
		System.out.println("					■			3. [담당 학생 정보 검색]	    ■");
		System.out.println("					■			4. [담당 학생 정보 수정]	    ■");
		System.out.println("					■			0. [종료]		            ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println();
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.print("						 	선택 메뉴 : 	");
	}
	private void studentLogin() {
		while (true) {
			try {
				System.out.print("							 학번을 입력하세요  :  ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					    		 비밀번호를 입력하세요  :  ");
				PW = sc.nextLine();

				StudentCRUD crud = new StudentCRUD();
				if (crud.checkIdPassword(SNO, PW)) {
					System.out.println("						 	 로그인 성공!! 환영합니다. ");
					studentLogin1();
				} else {
					System.out.println("				             학번/비밀번호 확인하세요!");
				}

			} catch (NumberFormatException e) {
				System.out.println("					             학번 혹은 비밀번호가 잘못되었습니다." + " 다시 시도하세요.");
				continue;
			}
			break;
		}
	}
	public void stuselect() {
		while (true) {
			try {		
				StudentCRUD crud = new StudentCRUD();	
				StudentVO svo = crud.selectOne(SNO);		
				if (crud.checkIdPassword(SNO, PW)) {
					System.out.println(svo.toString());
					studentLogin1();
				} else {
					System.out.println("					학번 비밀번호 확인하세요!");
				}

			}catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	private void professorLogin() {
		while (true) {
			try {
				System.out.println("");
				System.out.print("							 교수번호 :  ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("							 비밀번호 :  ");
				PW = sc.nextLine();
				ProfessorCRUD crud = new ProfessorCRUD();
				if (crud.checkIdPassword(PNO, PW)) {
					System.out.println();
					System.out.println("							 로그인 성공 ! !			");
					professorManager();
				} else {
					System.out.println("					■			학번 비밀번호 확인하세요!  				    ■");
				}
				//professorManager();
			} catch (NumberFormatException e) {
				System.out.println("					학번 혹은 비밀번호가 잘못되었습니다." + " 다시 시도하세요.");
				continue;
			}
			break;
		}
	}
	public void proselect() {
		while (true) {
			try {		
				ProfessorCRUD crud = new ProfessorCRUD();
				ProfessorVO pvo = crud.selectOne(PNO);		
				if (crud.checkIdPassword(PNO, PW)) {
					System.out.println(pvo.toString());
					professorManager();
				} else {
					System.out.println("					학번 비밀번호 확인하세요!");
				}

			}catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	private void stuUpdate() {
		while (true) {
			try{
				System.out.print("							 수정하실 정보를 입력해주세요.");
				System.out.println();

				System.out.print("							 핸드폰번호를 입력하세요 : " );
				PHONE = sc.nextLine();
				System.out.println();

				System.out.print("							 주소를 입력하세요 : " );
				ADDRESS = sc.nextLine();
				System.out.println();

				if (ADDRESS == null) {
					System.out.println("							 빈칸이 있습니다. 다시 확인해주세요! ");
				}
				stuInfoUpdate();
				studentLogin1();

				System.out.println("							 학생 정보 수정을 완료하였습니다.");

			} catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	public int stuInfoUpdate() {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE STUDENT ");
			sb.append("       SET PHONE = ?");
			sb.append("       , ADDRESS = ?");
			sb.append(" WHERE SNO = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, PHONE);
			pstmt.setString(2, ADDRESS);
			pstmt.setInt(3, SNO);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}	
		return result;
	}
	private void proUpdate() {
		while (true) {
			try{
				System.out.println();
				System.out.println("							 수정할 정보를 입력해주세요. ");
				System.out.println();
				System.out.print("							 핸드폰번호를 입력하세요 : " );
				PPHONE = sc.nextLine();

				System.out.print("							 비밀번호를 입력하세요 : " );
				PW = sc.nextLine();

				if (PW == null) {
					System.out.println("							 빈칸이 있습니다. 다시 확인해주세요! ");
				}
				proInfoUpdate();
				professorManager();

				System.out.println("							 교수 정보 수정을 완료하였습니다.");

			} catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	public int proInfoUpdate() {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE PROFESSOR ");
			sb.append("       SET PPHONE = ?");
			sb.append("       , PW = ?");
			sb.append(" WHERE PNO = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, PPHONE);
			pstmt.setString(2, PW);
			pstmt.setInt(3, PNO);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}	
		return result;
	}
	private void prostuSelect() {//교수가 학생 정보 검색
		ProfessorCRUD pcrud = new ProfessorCRUD();
		ProfessorVO pvo = pcrud.selectOne(PNO);
		
		ArrayList<StudentVO> list = crud.selectSubnoList(pvo.getSUBNO());
		System.out.println(list);

	}
	//===============
	private void joinstu() {

		while (true) {
			System.out.println("							 회원가입을 진행합니다.");

			try {

				System.out.print("							 학생번호를 입력하세요 : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("							 비밀번호를 입력하세요 : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("							 이름을 입력하세요");
				NAME = sc.nextLine();
				System.out.println();
				while (true) {
					System.out.print("							 주민번호를 입력하세요. ex)000000-0000000 : ");
					JUMIN = sc.nextLine();
					System.out.println();
					if (PW == null || NAME == null || JUMIN == null) {
						System.out.println("							 비밀번호 혹은 이름 혹은 주민등록번호가 누락 되었습니다.");
					}
					if (JUMIN.length() == 14) {
					} else {
						System.out.println("							 000000-0000000형식으로 입력해주세요.");
					}
					int month = Integer.parseInt(JUMIN.substring(2, 4));
					if (month >= 1 && month <= 12) {
						//System.out.println("[정상] 생월이 1~12의값으로 정상입력");
					} else {
						System.out.println("							 생월이 1~12값이 아닙니다.");
						System.out.println("							 주민등록번호를 다시 입력하세요.");
					}
					int dd = Integer.parseInt(JUMIN.substring(4, 6));// substring(4,6)
					if (dd >= 1 && dd <= 31) {
						break;
					} else {
						System.out.println("							 [예외발생] 생월이 1~31값이 아닙니다.");
						System.out.println("							 주민등록번호를 다시 입력하세요.");
						System.out.println();
						continue;
					}
				}
				stujoin();
				System.out.println(NAME+"							 님, 회원가입이 완료 되었습니다.");
			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}
	}
	public int stujoin() {
		int result = 0;

		try {
			// DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL 문장 실행을 위한 준비
			// SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO STUDENT ");
			sb.append("       ( SNO, PW, NAME, JUMIN ) ");
			sb.append("VALUES (?, ?, ?, ? ) ");
			// SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());
			
			// SQL문의 ?에 값 매칭 작업
			pstmt.setInt(1, SNO);
			pstmt.setString(2, PW);
			pstmt.setString(3, NAME);
			pstmt.setString(4, JUMIN);

			// SQL문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원 닫기
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}
	
	private void projoin() {

		while (true) {
			System.out.println("					회원가입을 진행합니다.");

			try {

				System.out.print("					 교수번호(숫자4자리)를 입력하세요 : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 비밀번호를 입력하세요 : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("					 이름을 입력하세요");
				PNAME = sc.nextLine();
				System.out.println();
				while (true) {

					System.out.print("					 주민번호를 입력하세요(ex)000000-0000000) : ");
					JUMIN = sc.nextLine();
					System.out.println();

					if (PW == null || PNAME == null || JUMIN == null) {
						System.out.println("					비밀번호 혹은 이름 혹은 주민등록번호가 누락 되었습니다.");
					}

					if (JUMIN.length() == 14) {
					} else {
						System.out.println("					000000-0000000형식으로 입력해주세요.");
						System.out.println("					주민등록번호를 다시 입력하세요.");
						continue;
					}
					int month = Integer.parseInt(JUMIN.substring(2, 4));
					if (month >= 1 && month <= 12) {
					} else {
						System.out.println("					생월이 1~12값이 아닙니다.");
						System.out.println("					주민등록번호를 다시 입력하세요.");
						continue;
					}

					int dd = Integer.parseInt(JUMIN.substring(4, 6));
					if (dd >= 1 && dd <= 31) {
						break;
					} else {
						System.out.println("					[예외발생] 생월이 1~31값이 아닙니다.");
						System.out.println("					주민등록번호를 다시 입력하세요.");
						System.out.println();
						continue;
					}
				}
				joinpro();
				System.out.println("					"+ PNAME + " 교수의 회원가입이 완료 되었습니다. ");
			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}
	}
	public int joinpro() {
		int result = 0;

		try {
			// DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL 문장 실행을 위한 준비
			// SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO PROFESSOR ");
			sb.append("       ( PNO, PW, PNAME, JUMIN ) ");
			sb.append("VALUES (?, ?, ?, ? ) ");
			// SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			// SQL문의 ?에 값 매칭 작업
			pstmt.setInt(1, PNO);
			pstmt.setString(2, PW);
			pstmt.setString(3, PNAME);
			pstmt.setString(4, JUMIN);

			// SQL문 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원 닫기
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}
	
	
	private void adminLogin() {

		while (true) {
			try {
				System.out.print("							 관리자번호(ID) : ");
				MNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("							 비밀번호 : ");
				PW = sc.nextLine();
				ManagerCRUD crud = new ManagerCRUD();
				if (crud.checkIdPassword(MNO, PW)) {
					System.out.println();
					System.out.println("							 로그인 성공 !!");
					manager();

				} else {
					System.out.println("							 관리자 비밀번호 확인하세요!");
				}
			} catch (NumberFormatException e) {
				System.out.println("							 관리자번호 혹은 비밀번호가 잘못되었습니다." + " 다시 시도하세요.");
				continue;
			}
			break;

		}
	}

	// ---------------------------------------------------
	// int select;
	private boolean manager() {
		boolean jobContinue = true;
		mshowMenu();
		int select = 99;
		try {
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("					잘못된 값이 입력되었습니다." + " 메뉴(0~8) 숫자만 선택하세요");
			return jobContinue;
		}
		if (select == 1) {
			System.out.println("					[학생 목록]");
			studentSelect();
			manager();
		} else if (select == 2) {
			System.out.println("					[교수 목록]");
			professorSelect();
			manager();
		} else if (select == 3) {
			System.out.println("					[학생정보수정]");
			studentUpdate();
			manager();
		} else if (select == 4) {
			System.out.println("					[교수정보수정]");
			professorUpdate();
			manager();
		}else if (select == 5) {
			System.out.println("					[학생 추가]");
			studentInsert();
			manager();
		} else if (select == 6) {
			System.out.println("					[교수 추가]");
			professorInsert();
			manager();
		} else if (select == 7) {
			studentDelete();
			manager();
		} else if (select == 8) {
			System.out.println("							[교수정보삭제]");

			professorDelete();
			manager();
		} else if (select == 0) {
			System.out.println("							종료합니다.");
			jobContinue = false; // 작업종료인 경우 false 리턴
		} else {
			System.out.println("					잘못된 값이 입력되었습니다." + " 메뉴(0~8) 숫자만 선택하세요");
		}
		return jobContinue;
	}

	private void mshowMenu() {
		System.out.println();
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■		       메뉴를 선택하세요 		    ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("					■			1. [학생 목록]	 	   	    ■");
		System.out.println("					■			2. [교수 목록]	 	   	    ■");
		System.out.println("					■			3. [학생 정보 수정]		    ■");
		System.out.println("					■			4. [교수 정보 수정]		    ■");
		System.out.println("					■			5. [학생 추가]		   	    ■");
		System.out.println("					■			6. [교수 추가]		   	    ■");
		System.out.println("					■			7. [학생 정보 삭제]		    ■");
		System.out.println("					■			8. [교수 정보 삭제]		    ■");
		System.out.println("					■			0. [종료]		            ■");
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println();
		System.out.println("					■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.print("						 	선택 메뉴 : 	");
	}

	private void professorSelect() {

		// select = Integer.parseInt(sc.nextLine());
		ProfessorCRUD pcrud = new ProfessorCRUD();
		ArrayList<ProfessorVO> list = pcrud.selectAll();
		//System.out.println("--- 전체데이타 조회 결과 ---");
		System.out.print("					--------------------------------------------------------------\n");
		System.out.println("					교수번호 이름 학과번호 핸드폰번호    비밀번호 과목번호 주민등록번호");
		for (ProfessorVO stu : list) {
			System.out.print("					" + "- ");
			pcrud.dispData(stu.getPNO());
		}
		System.out.print("					--------------------------------------------------------------\n");
	}

	private void studentSelect() {
		// select = Integer.parseInt(sc.nextLine());
		StudentCRUD scrud = new StudentCRUD();
		ArrayList<StudentVO> slist = scrud.selectAll();
		System.out.print("					-----------------------------------------------------------------------------------------------------\n");
		System.out.println("					   학번         비밀번호 이름 학과번호 학점 	주민번호 	전화번호	 주소		과목번호");
		System.out.println();
		for (StudentVO stu : slist) {
			System.out.print("					" + "- ");
			scrud.dispData(stu.getSNO());
		}
		System.out.print("					-----------------------------------------------------------------------------------------------------\n");
	}

	private void studentUpdate() {

		while (true) {
			System.out.println("							 학생 수정을 진행합니다.");

			try {

				System.out.print("							 학번을 입력하세요 : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("							 이름을 입력하세요 : ");
				NAME = sc.nextLine();
				System.out.println();

				System.out.print("							 학과를 입력하세요 : " +"ex) 컴퓨터공학과:1 , 경영학과:2, 세무회계과:3");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("							 학점을 입력하세요 : " );
				QPA = sc.nextLine();
				System.out.println();

				System.out.print("							 주민등록번호를 입력하세요 : " );
				JUMIN = sc.nextLine();
				System.out.println();

				System.out.print("							 핸드폰번호를 입력하세요 : " );
				PHONE = sc.nextLine();
				System.out.println();

				System.out.print("							 주소를 입력하세요 : " );
				ADDRESS = sc.nextLine();
				System.out.println();

				if (NAME == null ||JUMIN== null||ADDRESS == null) {
					System.out.println("							 빈칸이 있습니다. 다시 확인해주세요! ");
				}

				updateOne();
				System.out.println("							 학생 정보 수정을 완료하였습니다.");


			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}

	}

	private void professorUpdate() {

		while (true) {
			System.out.println("						교수 정보 수정을 진행합니다.");

			try {

				System.out.print("						 교수번호를 입력하세요 : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("						 이름을 입력하세요 : ");
				PNAME = sc.nextLine();
				System.out.println();

				System.out.print("						 학과번호를 입력하세요.  컴퓨터공학과:1 , 경영학과:2, 세무회계과:3 ");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("						핸드폰번호를 입력하세요 : ");
				PPHONE =sc.nextLine();
				System.out.println();

				System.out.print("						 비밀번호를 입력하세요 : ");
				PW = sc.nextLine();
				System.out.println();

				if (PW == null || PNAME == null ||  SUB == null) {
					System.out.println("						비밀번호 혹은 이름 혹은 주민등록번호 혹은 주소가 누락 되었습니다.");
				}
				updateData();
				System.out.println("						교수 정보 수정을 완료하였습니다. ");

			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}

	}


	private void studentDelete() {
		while (true) {

			System.out.println("					학생삭제를 시작합니다.");
			try {
				System.out.print("					삭제할 학생의 학번을 입력하세요 : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				studeleteOne();
				System.out.println("					삭제를 완료하였습니다.");
			}catch(NumberFormatException e){
				continue;
			}	break;
		}
	}
	public int studeleteOne() {
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
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}
	private void professorDelete() {
		while (true) {
			System.out.println("					교수 삭제를 시작합니다.");
			try {
				System.out.print("					삭제할 교수의 교수번호를 입력하세요 : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				prodeleteOne();
				System.out.println("					삭제를 완료하였습니다.");
			}catch(NumberFormatException e){
				continue;
			}	break;
		}
	}
	public int prodeleteOne() {
		int result = 0;

		try {
			//DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL 문장 실행을 위한 준비
			//SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM PROFESSOR WHERE PNO = ?");

			//SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업
			pstmt.setInt(1, PNO);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}


	private void studentInsert() {

		while (true) {
			System.out.println("					학생 추가를 진행합니다.");
			System.out.println();
			try {

				System.out.print("					 학번을 입력하세요 : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 비밀번호를 입력하세요 : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("					 이름을 입력하세요 : ");
				NAME = sc.nextLine();
				System.out.println();
				System.out.print("					 학과번호를 입력하세요.  컴퓨터공학과:1 , 경영학과:2, 세무회계과:3 		");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 학점을 입력하세요 : ");
				QPA = sc.nextLine();
				System.out.println();
				System.out.print("					 주민번호를 입력하세요( - 포함 14자) : ");
				JUMIN = sc.nextLine();
				System.out.println();
				System.out.print("					 전화번호를 입력하세요 : ");
				PHONE = sc.nextLine();
				System.out.println();
				System.out.print("					 주소를 입력하세요 : ");
				ADDRESS = sc.nextLine();
				System.out.println();


				if (PW == null || NAME == null || JUMIN == null || ADDRESS == null) {
					System.out.println("					비밀번호 혹은 이름 혹은 주민등록번호 혹은 주소가 누락 되었습니다.");
				}
				insertOne();
				System.out.println("					학생 추가를 완료하였습니다.");
			} catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	private void professorInsert() {
		while (true) {
			System.out.println("					교수 추가를 진행합니다.");
			try {
				System.out.println();
				System.out.print("					 교수번호를 입력하세요 : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 비밀번호를 입력하세요 : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("					 이름을 입력하세요 : ");
				PNAME = sc.nextLine();
				System.out.println();
				System.out.print("					 주민번호를 입력하세요 : ");
				JUMIN = sc.nextLine();
				System.out.println();
				System.out.print("					 학과번호를 입력하세요.  컴퓨터공학과:1 , 경영학과:2, 세무회계과:3 ");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 과목 번호를 입력하세요 : ");
				SUBNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					핸드폰번호를 입력하세요 : ");
				PPHONE = sc.nextLine();
				System.out.println();
//				if (PW == null || NAME == null ||  JUMIN == null) {
//					System.out.println("					비밀번호 혹은 이름 혹은 주민등록번호가 누락 되었습니다.");
//				}
				insertOneP();
				System.out.println("					교수 추가를 완료하였습니다.");
			} catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	public int insertOne() {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO STUDENT ");
			sb.append("       (SNO, PW, NAME, DEPTNO, QPA, JUMIN, PHONE, ADDRESS, SUBNO ) ");
			sb.append("VALUES (?, ?, ?, ? , ?, ?, ?, ?, ?) ");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, SNO);
			pstmt.setString(2, PW);
			pstmt.setString(3, NAME);
			pstmt.setInt(4, DEPTNO);
			pstmt.setString(5, QPA);
			pstmt.setString(6, JUMIN);
			pstmt.setString(7, PHONE);
			pstmt.setString(8, ADDRESS);
			pstmt.setInt(9, SUBNO);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}	
		return result;
	}
	public int insertOneP() {
		int result = 0;
		try {
			//DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL 문장 실행을 위한 준비
			//SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO PROFESSOR ");
			sb.append("       (PNO, PNAME, DEPTNO, PPHONE, JUMIN, PW, SUBNO ) ");
			sb.append("VALUES (?, ?, ?, ?, ?, ?, ?) ");
			//SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업
			pstmt.setInt(1, PNO);
			pstmt.setString(2, PNAME);
			pstmt.setInt(3, DEPTNO);
			pstmt.setString(4, PPHONE);
			pstmt.setString(5, JUMIN);
			pstmt.setString(6, PW);
			pstmt.setInt(7, SUBNO);

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

	public int updateOne() {
		int result = 0;

		try {
			// DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL 문장 실행을 위한 준비
			// SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE STUDENT ");
			sb.append("   SET NAME = ? ");
			sb.append("     , DEPTNO = ? ");
			sb.append("     , QPA = ? ");
			sb.append("     , JUMIN = ? ");
			sb.append("     , PHONE = ? ");
			sb.append("     , ADDRESS = ? ");
			sb.append("     , PW = ? ");
			sb.append("     , SUBNO = ? ");
			sb.append(" WHERE SNO = ?");
			// SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			// SQL문의 ?에 값 매칭 작업
			pstmt.setString(1, NAME);
			pstmt.setInt(2, DEPTNO);
			pstmt.setString(3, QPA);
			pstmt.setString(4, JUMIN);
			pstmt.setString(5, PHONE);
			pstmt.setString(6, ADDRESS);
			pstmt.setString(7, PW);
			pstmt.setInt(8, SUBNO);
			pstmt.setInt(9, SNO);
			// SQL문 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원 닫기
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}

	public int updateData() {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE PROFESSOR ");
			sb.append("   SET PNAME = ? ");
			sb.append("     , DEPTNO = ? ");
			sb.append("     , PPHONE = ? ");
			sb.append("     , JUMIN = ? ");
			sb.append("     , PW = ? ");
			sb.append("     , SUBNO = ? ");
			sb.append(" WHERE PNO = ?");
			//SQL 문장을 전달해서 실행 준비

			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업

			pstmt.setString(1, PNAME);
			pstmt.setInt(2, DEPTNO);
			pstmt.setString(3, PPHONE);
			pstmt.setString(4, JUMIN);
			pstmt.setString(5, PW);
			pstmt.setInt(6, SUBNO);
			pstmt.setInt(7, PNO);


			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}
	private void professorUpdateQpa() {
		while (true) {
			System.out.println("							 성적 수정을 시작합니다.");

			try {

				System.out.print("							 학생번호를 입력하세요 : ");
				SNO = Integer.parseInt(sc.nextLine());

				System.out.print("							 학점을 입력하세요 :  ");
				QPA = sc.nextLine();

				updateQPA();
				System.out.println("							 성적 수정이 완료되었습니다.");
				professorManager();


			} catch (NumberFormatException e) {
				System.out.println("							 다시 시도하세요.");
				continue;
			}
			break;
		}

	}

	public int updateQPA() {
		int result = 0;

		try {
			//DB 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL 문장 실행을 위한 준비
			//SQL 문장 작성
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE STUDENT ");
			sb.append("       SET QPA = ?");
			sb.append(" WHERE SNO = ?");
			//SQL 문장을 전달해서 실행 준비
			pstmt = conn.prepareStatement(sb.toString());

			//SQL문의 ?에 값 매칭 작업
			pstmt.setString(1, QPA);
			pstmt.setInt(2, SNO);


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
}