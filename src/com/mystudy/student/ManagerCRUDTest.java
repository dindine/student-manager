package com.mystudy.student;

public class ManagerCRUDTest {

	public static void main(String[] args) {
		ManagerCRUD crud = new ManagerCRUD();
		System.out.println("----- id, password üũ1 -----");
		boolean loginOk = crud.checkIdPassword(9999, "pw123");
		if (loginOk) {
			System.out.println(">>> �α��� ����! ȸ���� �ݰ����ϴ�.");
		} else {
			System.out.println(">>> �α��� ����! ���̵�/��ȣ Ȯ���ϼ���.");
		}
		
		loginOk = crud.checkIdPassword2(9999, "pw123");
		if (loginOk) {
			System.out.println(">>>2 �α��� ����! ȸ���� �ݰ����ϴ�.");
		} else {
			System.out.println(">>>2 �α��� ����! ���̵�/��ȣ Ȯ���ϼ���.");
		}
		

	}

}
