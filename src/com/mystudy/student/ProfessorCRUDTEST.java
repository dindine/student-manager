package com.mystudy.student;

import java.util.ArrayList;

public class ProfessorCRUDTEST {
	public static void main(String[] args) {

		ProfessorCRUD crud = new ProfessorCRUD();
		crud.dispData(1111);

		ArrayList<ProfessorVO> list = crud.selectAll();
		System.out.println("--- ��ü����Ÿ ��ȸ ��� ---");
		for (ProfessorVO pro : list) {
			crud.dispData(pro.getPNO());
			
		}
		
		
		System.out.println("----- selectOne(id) -----");
		ProfessorVO pvo = crud.selectOne(1111);
		System.out.println(pvo.toString());
		System.out.println(pvo);
		
//		System.out.println("----- updateQpa(VO) -----");
//		StudentVO STUDENT = new StudentVO(20182224, null, null, 2, 0, null, 0, null, 0);
//		int cnt = crud.updateQpa(STUDENT);
//		System.out.println("�����Ǽ�: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
		
//		System.out.println("----- id, password üũ1 -----");
//		boolean loginOk = crud.checkIdPassword(1111, "PW123");
//		if (loginOk) {
//			System.out.println(">>> �α��� ����! ȸ���� �ݰ����ϴ�.");
//		} else {
//			System.out.println(">>> �α��� ����! ���̵�/��ȣ Ȯ���ϼ���.");
//		}
//		
//		loginOk = crud.checkIdPassword2(1111, "PW123");
//		if (loginOk) {
//			System.out.println(">>>2 �α��� ����! ȸ���� �ݰ����ϴ�.");
//		} else {
//			System.out.println(">>>2 �α��� ����! ���̵�/��ȣ Ȯ���ϼ���.");
//		}
	}
}