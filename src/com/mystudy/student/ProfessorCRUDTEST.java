package com.mystudy.student;

import java.util.ArrayList;

public class ProfessorCRUDTEST {
	public static void main(String[] args) {

		ProfessorCRUD crud = new ProfessorCRUD();
		crud.dispData(1111);

		ArrayList<ProfessorVO> list = crud.selectAll();
		System.out.println("--- 전체데이타 조회 결과 ---");
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
//		System.out.println("수정건수: " + cnt);
//		System.out.println(crud.selectOne(STUDENT));
		
//		System.out.println("----- id, password 체크1 -----");
//		boolean loginOk = crud.checkIdPassword(1111, "PW123");
//		if (loginOk) {
//			System.out.println(">>> 로그인 성공! 회원님 반갑습니다.");
//		} else {
//			System.out.println(">>> 로그인 실패! 아이디/암호 확인하세요.");
//		}
//		
//		loginOk = crud.checkIdPassword2(1111, "PW123");
//		if (loginOk) {
//			System.out.println(">>>2 로그인 성공! 회원님 반갑습니다.");
//		} else {
//			System.out.println(">>>2 로그인 실패! 아이디/암호 확인하세요.");
//		}
	}
}