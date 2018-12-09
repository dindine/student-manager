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

	static {// static �ʱ�ȭ ����
		try {
			Class.forName(DRIVER);
			//System.out.println(">> ����Ŭ JDBC ����̹� �ε� ����!!");
		} catch (ClassNotFoundException e) {
			System.out.println("[���ܹ߻�] ����̹� �ε� ����!!!");
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
			System.out.println("					�������������������������������������������������������������");
			System.out.println("					��			�л� ���� �ý���		   ��");
			while (true) {
				if (studentManager() == false) {
					break;
				}
			}
//			System.out.println("								�۾��� �����մϴ�.");
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
			System.out.println("�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~3) ���ڸ� �����ϼ���");
			return jobContinue;
		}
		if (select == 1) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	�л� �α��� 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			studentLogin();
		} else if (select == 2) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	���� �α��� 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			professorLogin();
		} else if (select == 3) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	������ �α��� 		            *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			adminLogin();
		} else if (select == 4) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	�л� ȸ�� ���� 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			joinstu();
		} else if (select == 5) {
			System.out.println();
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			System.out.println("					*	   	       	���� ȸ�� ���� 			    *");
			System.out.println("					* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
			projoin();
		} else if (select == 0) {
			System.out.println("					��	   	       	�����մϴ�. 		          ��");
			jobContinue = false; // �۾������� ��� false ����
		} else {
			System.out.println("�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~4) ���ڸ� �����ϼ���");
		}
		return jobContinue;
	}
	private void showMenu() {
		System.out.println("					�������������������������������������������������������������");
		System.out.println("					��		       �޴��� �����ϼ��� 		   ��");
		System.out.println("					�������������������������������������������������������������");
		System.out.println("					��	�޴� : 						   ��");
		System.out.println("					��			1. [�л��α���]			   ��");
		System.out.println("					��			2. [�����α���]			   ��");
		System.out.println("					��			3. [�����ڷα���]		   ��");
		System.out.println("					��			4. [�л� ȸ������]		   ��");
		System.out.println("					��			5. [���� ȸ������]		   ��");
		System.out.println("					��			0. [����]		           ��");
		System.out.println("					�������������������������������������������������������������");
		System.out.println();
		System.out.println("					�������������������������������������������������������������");
		System.out.print("						 	���� �޴� : 	");
	}
	
	private boolean studentLogin1() {
		boolean jobContinue = true;
		stushowMenu();
		int select = 99;
		try {
			System.out.println();
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("					�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~3) ���ڸ� �����ϼ���");
			return jobContinue;
		}
		if (select == 1) {
			stuselect();
			studentLogin1();
		} else if (select == 2) {
			stuUpdate();
			studentLogin1();

		} else if (select == 0) {
			System.out.println("					��	   	       	�����մϴ�. 		           ��");
			studentManager();
			jobContinue = false; // �۾������� ��� false ����
		} else {
			System.out.println("�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~2) ���ڸ� �����ϼ���");
		}
		return jobContinue;
	}
	private void stushowMenu() {
		System.out.println();
		System.out.println("					��������������������������������������������������������������");
		System.out.println("					��		       �޴��� �����ϼ��� 		    ��");
		System.out.println("					��������������������������������������������������������������");
		System.out.println("					��			1. [���� ���� �˻�]		    ��");
		System.out.println("					��			2. [���� ���� ����]		    ��");
		System.out.println("					��			0. [����]		            ��");
		System.out.println("					��������������������������������������������������������������");
		System.out.println();
		System.out.println("					��������������������������������������������������������������");
		System.out.print("						 	���� �޴� : 	");
	}
	private boolean professorManager() {
		boolean jobContinue = true;
		proshowmenu();
		int select = 99;
		try {
			select = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("					�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~4) ���ڸ� �����ϼ���");
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
			System.out.println("						�����մϴ�.			");
			studentManager();
			jobContinue = false; // �۾������� ��� false ����
		} else {
			System.out.println("�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~4) ���ڸ� �����ϼ���");
		}
		return jobContinue;
	}
	private void proshowmenu() {
		System.out.println();
		System.out.println("					��������������������������������������������������������������");
		System.out.println("					��		       �޴��� �����ϼ��� 		    ��");
		System.out.println("					��������������������������������������������������������������");
		System.out.println("					��			1. [���� ���� �˻�]		    ��");
		System.out.println("					��			2. [���� ���� ����]		    ��");
		System.out.println("					��			3. [��� �л� ���� �˻�]	    ��");
		System.out.println("					��			4. [��� �л� ���� ����]	    ��");
		System.out.println("					��			0. [����]		            ��");
		System.out.println("					��������������������������������������������������������������");
		System.out.println();
		System.out.println("					��������������������������������������������������������������");
		System.out.print("						 	���� �޴� : 	");
	}
	private void studentLogin() {
		while (true) {
			try {
				System.out.print("							 �й��� �Է��ϼ���  :  ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					    		 ��й�ȣ�� �Է��ϼ���  :  ");
				PW = sc.nextLine();

				StudentCRUD crud = new StudentCRUD();
				if (crud.checkIdPassword(SNO, PW)) {
					System.out.println("						 	 �α��� ����!! ȯ���մϴ�. ");
					studentLogin1();
				} else {
					System.out.println("				             �й�/��й�ȣ Ȯ���ϼ���!");
				}

			} catch (NumberFormatException e) {
				System.out.println("					             �й� Ȥ�� ��й�ȣ�� �߸��Ǿ����ϴ�." + " �ٽ� �õ��ϼ���.");
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
					System.out.println("					�й� ��й�ȣ Ȯ���ϼ���!");
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
				System.out.print("							 ������ȣ :  ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("							 ��й�ȣ :  ");
				PW = sc.nextLine();
				ProfessorCRUD crud = new ProfessorCRUD();
				if (crud.checkIdPassword(PNO, PW)) {
					System.out.println();
					System.out.println("							 �α��� ���� ! !			");
					professorManager();
				} else {
					System.out.println("					��			�й� ��й�ȣ Ȯ���ϼ���!  				    ��");
				}
				//professorManager();
			} catch (NumberFormatException e) {
				System.out.println("					�й� Ȥ�� ��й�ȣ�� �߸��Ǿ����ϴ�." + " �ٽ� �õ��ϼ���.");
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
					System.out.println("					�й� ��й�ȣ Ȯ���ϼ���!");
				}

			}catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	private void stuUpdate() {
		while (true) {
			try{
				System.out.print("							 �����Ͻ� ������ �Է����ּ���.");
				System.out.println();

				System.out.print("							 �ڵ�����ȣ�� �Է��ϼ��� : " );
				PHONE = sc.nextLine();
				System.out.println();

				System.out.print("							 �ּҸ� �Է��ϼ��� : " );
				ADDRESS = sc.nextLine();
				System.out.println();

				if (ADDRESS == null) {
					System.out.println("							 ��ĭ�� �ֽ��ϴ�. �ٽ� Ȯ�����ּ���! ");
				}
				stuInfoUpdate();
				studentLogin1();

				System.out.println("							 �л� ���� ������ �Ϸ��Ͽ����ϴ�.");

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
				System.out.println("							 ������ ������ �Է����ּ���. ");
				System.out.println();
				System.out.print("							 �ڵ�����ȣ�� �Է��ϼ��� : " );
				PPHONE = sc.nextLine();

				System.out.print("							 ��й�ȣ�� �Է��ϼ��� : " );
				PW = sc.nextLine();

				if (PW == null) {
					System.out.println("							 ��ĭ�� �ֽ��ϴ�. �ٽ� Ȯ�����ּ���! ");
				}
				proInfoUpdate();
				professorManager();

				System.out.println("							 ���� ���� ������ �Ϸ��Ͽ����ϴ�.");

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
	private void prostuSelect() {//������ �л� ���� �˻�
		ProfessorCRUD pcrud = new ProfessorCRUD();
		ProfessorVO pvo = pcrud.selectOne(PNO);
		
		ArrayList<StudentVO> list = crud.selectSubnoList(pvo.getSUBNO());
		System.out.println(list);

	}
	//===============
	private void joinstu() {

		while (true) {
			System.out.println("							 ȸ�������� �����մϴ�.");

			try {

				System.out.print("							 �л���ȣ�� �Է��ϼ��� : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("							 ��й�ȣ�� �Է��ϼ��� : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("							 �̸��� �Է��ϼ���");
				NAME = sc.nextLine();
				System.out.println();
				while (true) {
					System.out.print("							 �ֹι�ȣ�� �Է��ϼ���. ex)000000-0000000 : ");
					JUMIN = sc.nextLine();
					System.out.println();
					if (PW == null || NAME == null || JUMIN == null) {
						System.out.println("							 ��й�ȣ Ȥ�� �̸� Ȥ�� �ֹε�Ϲ�ȣ�� ���� �Ǿ����ϴ�.");
					}
					if (JUMIN.length() == 14) {
					} else {
						System.out.println("							 000000-0000000�������� �Է����ּ���.");
					}
					int month = Integer.parseInt(JUMIN.substring(2, 4));
					if (month >= 1 && month <= 12) {
						//System.out.println("[����] ������ 1~12�ǰ����� �����Է�");
					} else {
						System.out.println("							 ������ 1~12���� �ƴմϴ�.");
						System.out.println("							 �ֹε�Ϲ�ȣ�� �ٽ� �Է��ϼ���.");
					}
					int dd = Integer.parseInt(JUMIN.substring(4, 6));// substring(4,6)
					if (dd >= 1 && dd <= 31) {
						break;
					} else {
						System.out.println("							 [���ܹ߻�] ������ 1~31���� �ƴմϴ�.");
						System.out.println("							 �ֹε�Ϲ�ȣ�� �ٽ� �Է��ϼ���.");
						System.out.println();
						continue;
					}
				}
				stujoin();
				System.out.println(NAME+"							 ��, ȸ�������� �Ϸ� �Ǿ����ϴ�.");
			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}
	}
	public int stujoin() {
		int result = 0;

		try {
			// DB ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL ���� ������ ���� �غ�
			// SQL ���� �ۼ�
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO STUDENT ");
			sb.append("       ( SNO, PW, NAME, JUMIN ) ");
			sb.append("VALUES (?, ?, ?, ? ) ");
			// SQL ������ �����ؼ� ���� �غ�
			pstmt = conn.prepareStatement(sb.toString());
			
			// SQL���� ?�� �� ��Ī �۾�
			pstmt.setInt(1, SNO);
			pstmt.setString(2, PW);
			pstmt.setString(3, NAME);
			pstmt.setString(4, JUMIN);

			// SQL�� ����
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ����� �ڿ� �ݱ�
			JDBC_Close.closeConnStmt(conn, pstmt);
		}

		return result;
	}
	
	private void projoin() {

		while (true) {
			System.out.println("					ȸ�������� �����մϴ�.");

			try {

				System.out.print("					 ������ȣ(����4�ڸ�)�� �Է��ϼ��� : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 ��й�ȣ�� �Է��ϼ��� : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("					 �̸��� �Է��ϼ���");
				PNAME = sc.nextLine();
				System.out.println();
				while (true) {

					System.out.print("					 �ֹι�ȣ�� �Է��ϼ���(ex)000000-0000000) : ");
					JUMIN = sc.nextLine();
					System.out.println();

					if (PW == null || PNAME == null || JUMIN == null) {
						System.out.println("					��й�ȣ Ȥ�� �̸� Ȥ�� �ֹε�Ϲ�ȣ�� ���� �Ǿ����ϴ�.");
					}

					if (JUMIN.length() == 14) {
					} else {
						System.out.println("					000000-0000000�������� �Է����ּ���.");
						System.out.println("					�ֹε�Ϲ�ȣ�� �ٽ� �Է��ϼ���.");
						continue;
					}
					int month = Integer.parseInt(JUMIN.substring(2, 4));
					if (month >= 1 && month <= 12) {
					} else {
						System.out.println("					������ 1~12���� �ƴմϴ�.");
						System.out.println("					�ֹε�Ϲ�ȣ�� �ٽ� �Է��ϼ���.");
						continue;
					}

					int dd = Integer.parseInt(JUMIN.substring(4, 6));
					if (dd >= 1 && dd <= 31) {
						break;
					} else {
						System.out.println("					[���ܹ߻�] ������ 1~31���� �ƴմϴ�.");
						System.out.println("					�ֹε�Ϲ�ȣ�� �ٽ� �Է��ϼ���.");
						System.out.println();
						continue;
					}
				}
				joinpro();
				System.out.println("					"+ PNAME + " ������ ȸ�������� �Ϸ� �Ǿ����ϴ�. ");
			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}
	}
	public int joinpro() {
		int result = 0;

		try {
			// DB ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL ���� ������ ���� �غ�
			// SQL ���� �ۼ�
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO PROFESSOR ");
			sb.append("       ( PNO, PW, PNAME, JUMIN ) ");
			sb.append("VALUES (?, ?, ?, ? ) ");
			// SQL ������ �����ؼ� ���� �غ�
			pstmt = conn.prepareStatement(sb.toString());

			// SQL���� ?�� �� ��Ī �۾�
			pstmt.setInt(1, PNO);
			pstmt.setString(2, PW);
			pstmt.setString(3, PNAME);
			pstmt.setString(4, JUMIN);

			// SQL�� ����
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ����� �ڿ� �ݱ�
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}
	
	
	private void adminLogin() {

		while (true) {
			try {
				System.out.print("							 �����ڹ�ȣ(ID) : ");
				MNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("							 ��й�ȣ : ");
				PW = sc.nextLine();
				ManagerCRUD crud = new ManagerCRUD();
				if (crud.checkIdPassword(MNO, PW)) {
					System.out.println();
					System.out.println("							 �α��� ���� !!");
					manager();

				} else {
					System.out.println("							 ������ ��й�ȣ Ȯ���ϼ���!");
				}
			} catch (NumberFormatException e) {
				System.out.println("							 �����ڹ�ȣ Ȥ�� ��й�ȣ�� �߸��Ǿ����ϴ�." + " �ٽ� �õ��ϼ���.");
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
			System.out.println("					�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~8) ���ڸ� �����ϼ���");
			return jobContinue;
		}
		if (select == 1) {
			System.out.println("					[�л� ���]");
			studentSelect();
			manager();
		} else if (select == 2) {
			System.out.println("					[���� ���]");
			professorSelect();
			manager();
		} else if (select == 3) {
			System.out.println("					[�л���������]");
			studentUpdate();
			manager();
		} else if (select == 4) {
			System.out.println("					[������������]");
			professorUpdate();
			manager();
		}else if (select == 5) {
			System.out.println("					[�л� �߰�]");
			studentInsert();
			manager();
		} else if (select == 6) {
			System.out.println("					[���� �߰�]");
			professorInsert();
			manager();
		} else if (select == 7) {
			studentDelete();
			manager();
		} else if (select == 8) {
			System.out.println("							[������������]");

			professorDelete();
			manager();
		} else if (select == 0) {
			System.out.println("							�����մϴ�.");
			jobContinue = false; // �۾������� ��� false ����
		} else {
			System.out.println("					�߸��� ���� �ԷµǾ����ϴ�." + " �޴�(0~8) ���ڸ� �����ϼ���");
		}
		return jobContinue;
	}

	private void mshowMenu() {
		System.out.println();
		System.out.println("					��������������������������������������������������������������");
		System.out.println("					��		       �޴��� �����ϼ��� 		    ��");
		System.out.println("					��������������������������������������������������������������");
		System.out.println("					��			1. [�л� ���]	 	   	    ��");
		System.out.println("					��			2. [���� ���]	 	   	    ��");
		System.out.println("					��			3. [�л� ���� ����]		    ��");
		System.out.println("					��			4. [���� ���� ����]		    ��");
		System.out.println("					��			5. [�л� �߰�]		   	    ��");
		System.out.println("					��			6. [���� �߰�]		   	    ��");
		System.out.println("					��			7. [�л� ���� ����]		    ��");
		System.out.println("					��			8. [���� ���� ����]		    ��");
		System.out.println("					��			0. [����]		            ��");
		System.out.println("					��������������������������������������������������������������");
		System.out.println();
		System.out.println("					��������������������������������������������������������������");
		System.out.print("						 	���� �޴� : 	");
	}

	private void professorSelect() {

		// select = Integer.parseInt(sc.nextLine());
		ProfessorCRUD pcrud = new ProfessorCRUD();
		ArrayList<ProfessorVO> list = pcrud.selectAll();
		//System.out.println("--- ��ü����Ÿ ��ȸ ��� ---");
		System.out.print("					--------------------------------------------------------------\n");
		System.out.println("					������ȣ �̸� �а���ȣ �ڵ�����ȣ    ��й�ȣ �����ȣ �ֹε�Ϲ�ȣ");
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
		System.out.println("					   �й�         ��й�ȣ �̸� �а���ȣ ���� 	�ֹι�ȣ 	��ȭ��ȣ	 �ּ�		�����ȣ");
		System.out.println();
		for (StudentVO stu : slist) {
			System.out.print("					" + "- ");
			scrud.dispData(stu.getSNO());
		}
		System.out.print("					-----------------------------------------------------------------------------------------------------\n");
	}

	private void studentUpdate() {

		while (true) {
			System.out.println("							 �л� ������ �����մϴ�.");

			try {

				System.out.print("							 �й��� �Է��ϼ��� : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("							 �̸��� �Է��ϼ��� : ");
				NAME = sc.nextLine();
				System.out.println();

				System.out.print("							 �а��� �Է��ϼ��� : " +"ex) ��ǻ�Ͱ��а�:1 , �濵�а�:2, ����ȸ���:3");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("							 ������ �Է��ϼ��� : " );
				QPA = sc.nextLine();
				System.out.println();

				System.out.print("							 �ֹε�Ϲ�ȣ�� �Է��ϼ��� : " );
				JUMIN = sc.nextLine();
				System.out.println();

				System.out.print("							 �ڵ�����ȣ�� �Է��ϼ��� : " );
				PHONE = sc.nextLine();
				System.out.println();

				System.out.print("							 �ּҸ� �Է��ϼ��� : " );
				ADDRESS = sc.nextLine();
				System.out.println();

				if (NAME == null ||JUMIN== null||ADDRESS == null) {
					System.out.println("							 ��ĭ�� �ֽ��ϴ�. �ٽ� Ȯ�����ּ���! ");
				}

				updateOne();
				System.out.println("							 �л� ���� ������ �Ϸ��Ͽ����ϴ�.");


			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}

	}

	private void professorUpdate() {

		while (true) {
			System.out.println("						���� ���� ������ �����մϴ�.");

			try {

				System.out.print("						 ������ȣ�� �Է��ϼ��� : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("						 �̸��� �Է��ϼ��� : ");
				PNAME = sc.nextLine();
				System.out.println();

				System.out.print("						 �а���ȣ�� �Է��ϼ���.  ��ǻ�Ͱ��а�:1 , �濵�а�:2, ����ȸ���:3 ");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();

				System.out.print("						�ڵ�����ȣ�� �Է��ϼ��� : ");
				PPHONE =sc.nextLine();
				System.out.println();

				System.out.print("						 ��й�ȣ�� �Է��ϼ��� : ");
				PW = sc.nextLine();
				System.out.println();

				if (PW == null || PNAME == null ||  SUB == null) {
					System.out.println("						��й�ȣ Ȥ�� �̸� Ȥ�� �ֹε�Ϲ�ȣ Ȥ�� �ּҰ� ���� �Ǿ����ϴ�.");
				}
				updateData();
				System.out.println("						���� ���� ������ �Ϸ��Ͽ����ϴ�. ");

			} catch (NumberFormatException e) {
				continue;
			}
			break;
		}

	}


	private void studentDelete() {
		while (true) {

			System.out.println("					�л������� �����մϴ�.");
			try {
				System.out.print("					������ �л��� �й��� �Է��ϼ��� : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				studeleteOne();
				System.out.println("					������ �Ϸ��Ͽ����ϴ�.");
			}catch(NumberFormatException e){
				continue;
			}	break;
		}
	}
	public int studeleteOne() {
		int result = 0;

		try {
			//DB ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL ���� ������ ���� �غ�
			//SQL ���� �ۼ�
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM STUDENT WHERE SNO = ?");

			//SQL ������ �����ؼ� ���� �غ�
			pstmt = conn.prepareStatement(sb.toString());

			//SQL���� ?�� �� ��Ī �۾�
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
			System.out.println("					���� ������ �����մϴ�.");
			try {
				System.out.print("					������ ������ ������ȣ�� �Է��ϼ��� : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				prodeleteOne();
				System.out.println("					������ �Ϸ��Ͽ����ϴ�.");
			}catch(NumberFormatException e){
				continue;
			}	break;
		}
	}
	public int prodeleteOne() {
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
			System.out.println("					�л� �߰��� �����մϴ�.");
			System.out.println();
			try {

				System.out.print("					 �й��� �Է��ϼ��� : ");
				SNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 ��й�ȣ�� �Է��ϼ��� : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("					 �̸��� �Է��ϼ��� : ");
				NAME = sc.nextLine();
				System.out.println();
				System.out.print("					 �а���ȣ�� �Է��ϼ���.  ��ǻ�Ͱ��а�:1 , �濵�а�:2, ����ȸ���:3 		");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 ������ �Է��ϼ��� : ");
				QPA = sc.nextLine();
				System.out.println();
				System.out.print("					 �ֹι�ȣ�� �Է��ϼ���( - ���� 14��) : ");
				JUMIN = sc.nextLine();
				System.out.println();
				System.out.print("					 ��ȭ��ȣ�� �Է��ϼ��� : ");
				PHONE = sc.nextLine();
				System.out.println();
				System.out.print("					 �ּҸ� �Է��ϼ��� : ");
				ADDRESS = sc.nextLine();
				System.out.println();


				if (PW == null || NAME == null || JUMIN == null || ADDRESS == null) {
					System.out.println("					��й�ȣ Ȥ�� �̸� Ȥ�� �ֹε�Ϲ�ȣ Ȥ�� �ּҰ� ���� �Ǿ����ϴ�.");
				}
				insertOne();
				System.out.println("					�л� �߰��� �Ϸ��Ͽ����ϴ�.");
			} catch (NumberFormatException e) {
				continue;
			}break;
		}
	}
	private void professorInsert() {
		while (true) {
			System.out.println("					���� �߰��� �����մϴ�.");
			try {
				System.out.println();
				System.out.print("					 ������ȣ�� �Է��ϼ��� : ");
				PNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 ��й�ȣ�� �Է��ϼ��� : ");
				PW = sc.nextLine();
				System.out.println();
				System.out.print("					 �̸��� �Է��ϼ��� : ");
				PNAME = sc.nextLine();
				System.out.println();
				System.out.print("					 �ֹι�ȣ�� �Է��ϼ��� : ");
				JUMIN = sc.nextLine();
				System.out.println();
				System.out.print("					 �а���ȣ�� �Է��ϼ���.  ��ǻ�Ͱ��а�:1 , �濵�а�:2, ����ȸ���:3 ");
				DEPTNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					 ���� ��ȣ�� �Է��ϼ��� : ");
				SUBNO = Integer.parseInt(sc.nextLine());
				System.out.println();
				System.out.print("					�ڵ�����ȣ�� �Է��ϼ��� : ");
				PPHONE = sc.nextLine();
				System.out.println();
//				if (PW == null || NAME == null ||  JUMIN == null) {
//					System.out.println("					��й�ȣ Ȥ�� �̸� Ȥ�� �ֹε�Ϲ�ȣ�� ���� �Ǿ����ϴ�.");
//				}
				insertOneP();
				System.out.println("					���� �߰��� �Ϸ��Ͽ����ϴ�.");
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
			//DB ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL ���� ������ ���� �غ�
			//SQL ���� �ۼ�
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO PROFESSOR ");
			sb.append("       (PNO, PNAME, DEPTNO, PPHONE, JUMIN, PW, SUBNO ) ");
			sb.append("VALUES (?, ?, ?, ?, ?, ?, ?) ");
			//SQL ������ �����ؼ� ���� �غ�
			pstmt = conn.prepareStatement(sb.toString());

			//SQL���� ?�� �� ��Ī �۾�
			pstmt.setInt(1, PNO);
			pstmt.setString(2, PNAME);
			pstmt.setInt(3, DEPTNO);
			pstmt.setString(4, PPHONE);
			pstmt.setString(5, JUMIN);
			pstmt.setString(6, PW);
			pstmt.setInt(7, SUBNO);

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

	public int updateOne() {
		int result = 0;

		try {
			// DB ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL ���� ������ ���� �غ�
			// SQL ���� �ۼ�
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
			// SQL ������ �����ؼ� ���� �غ�
			pstmt = conn.prepareStatement(sb.toString());

			// SQL���� ?�� �� ��Ī �۾�
			pstmt.setString(1, NAME);
			pstmt.setInt(2, DEPTNO);
			pstmt.setString(3, QPA);
			pstmt.setString(4, JUMIN);
			pstmt.setString(5, PHONE);
			pstmt.setString(6, ADDRESS);
			pstmt.setString(7, PW);
			pstmt.setInt(8, SUBNO);
			pstmt.setInt(9, SNO);
			// SQL�� ����
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ����� �ڿ� �ݱ�
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
			//SQL ������ �����ؼ� ���� �غ�

			pstmt = conn.prepareStatement(sb.toString());

			//SQL���� ?�� �� ��Ī �۾�

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
			System.out.println("							 ���� ������ �����մϴ�.");

			try {

				System.out.print("							 �л���ȣ�� �Է��ϼ��� : ");
				SNO = Integer.parseInt(sc.nextLine());

				System.out.print("							 ������ �Է��ϼ��� :  ");
				QPA = sc.nextLine();

				updateQPA();
				System.out.println("							 ���� ������ �Ϸ�Ǿ����ϴ�.");
				professorManager();


			} catch (NumberFormatException e) {
				System.out.println("							 �ٽ� �õ��ϼ���.");
				continue;
			}
			break;
		}

	}

	public int updateQPA() {
		int result = 0;

		try {
			//DB ����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			//SQL ���� ������ ���� �غ�
			//SQL ���� �ۼ�
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE STUDENT ");
			sb.append("       SET QPA = ?");
			sb.append(" WHERE SNO = ?");
			//SQL ������ �����ؼ� ���� �غ�
			pstmt = conn.prepareStatement(sb.toString());

			//SQL���� ?�� �� ��Ī �۾�
			pstmt.setString(1, QPA);
			pstmt.setInt(2, SNO);


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